package telran.java51.security;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.forum.model.Post;
import telran.java51.forum.repository.ForumRepository;

@Service
@RequiredArgsConstructor
public class CustomWebSecurity {
	final ForumRepository forumRepository;
	
	public boolean checkPostAuthor(String postId, String userName) {
		Post post = forumRepository.findById(postId).orElse(null);
		return post != null && userName.equals(post.getAuthor());
	}
	
}
