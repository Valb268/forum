package telran.java51.security.filter;

import java.io.IOException;
import java.security.Principal;

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

@Component
@Order(30)
public class UpdateByOwnerFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (checkEndPoint(request.getMethod(), request.getServletPath())) {

			Principal principal = request.getUserPrincipal();
			String[] path = request.getServletPath().split("/");
			String owner = path[path.length - 1];
			if (!principal.getName().equals(owner)) {
				response.sendError(403, "Permission denied");
				return;
			}

		}
		chain.doFilter(request, response);
	}

	private boolean checkEndPoint(String method, String path) {
		return (HttpMethod.PUT.matches(method)
				&& path.matches("/account/user/\\w+"))
				|| (HttpMethod.POST.matches(method)
						&& path.matches("/forum/post/\\w+"))
				|| (HttpMethod.PUT.matches(method)
						&& path.matches("/forum/post/\\w+/comment/\\w+"));
	}

}
