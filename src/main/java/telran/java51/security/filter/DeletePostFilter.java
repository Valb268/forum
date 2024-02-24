package telran.java51.security.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import telran.java51.accounting.model.User;
import telran.java51.accounting.repository.AccountRepository;
import telran.java51.forum.configuration.UserRole;
import telran.java51.forum.exceptions.PostNotFoundException;
import telran.java51.forum.model.Post;
import telran.java51.forum.repository.ForumRepository;

@Component
@RequiredArgsConstructor
@Order(80)
public class DeletePostFilter implements Filter {

	final AccountRepository accountRepository;
	final ForumRepository forumRepository;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (checkEndPoint(request.getMethod(), request.getServletPath())) {

			User user = accountRepository
					.findById(request.getUserPrincipal().getName()).get();
			
			String[] path = request.getServletPath().split("/");
			String postId = path[path.length - 1];
			try {
				Post post = forumRepository.findById(postId).orElseThrow(PostNotFoundException::new);
				if (!(user.getLogin().equals(post.getAuthor()) 
						|| user.getRoles().contains(UserRole.MODERATOR.toString()))) {
					response.sendError(403, "Permission denied");
					return;
				}
			} catch (PostNotFoundException e) {
				response.sendError(404, "Post not found");
				return;
			}
		}
		chain.doFilter(request, response);
	}
	
	private boolean checkEndPoint(String method, String path) {
		return (HttpMethod.DELETE.matches(method) && path.matches("/forum/post/\\w+"));
	}

}
