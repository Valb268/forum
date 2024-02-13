package telran.java51.forum.service;

import java.util.List;

import telran.java51.forum.dto.MessageInCommentDto;
import telran.java51.forum.dto.PushPostDto;
import telran.java51.forum.model.Post;

public interface ForumService {

	Post addPost(String author, PushPostDto pushPostDto);
	
	Post findPostById(String id);
	
	void addLike(String id);
	
	List<Post> findPostsByAuthor(String author);
	
	Post addComment(String id, String user, MessageInCommentDto messageInCommentDto);
	
	void deletePost(String id);
	
	List<Post> findPostsByTags(String[] tags);
	
	
	
}
