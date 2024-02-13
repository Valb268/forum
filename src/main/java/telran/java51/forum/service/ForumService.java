package telran.java51.forum.service;

import java.util.List;

import telran.java51.forum.dto.MessageInCommentDto;
import telran.java51.forum.dto.PostDto;
import telran.java51.forum.dto.PushPostDto;

public interface ForumService {

	PostDto addPost(String author, PushPostDto pushPostDto);
	
	PostDto findPostById(String id);
	
	void addLike(String id);
	
	List<PostDto> findPostsByAuthor(String author);
	
	PostDto addComment(String id, String user, MessageInCommentDto messageInCommentDto);
	
	void deletePost(String id);
	
	List<PostDto> findPostsByTags(String[] tags);
	
	
	
}
