package telran.java51.forum.service;

import java.util.List;

import telran.java51.forum.dto.NewCommentDto;
import telran.java51.forum.dto.NewPostDto;
import telran.java51.forum.dto.PeriodDto;
import telran.java51.forum.dto.PostDto;

public interface ForumService {

	PostDto addPost(String author, NewPostDto pushPostDto);
	
	PostDto findPostById(String id);
	
	boolean addLike(String id);
	
	Iterable<PostDto> findPostsByAuthor(String author);
	
	PostDto addComment(String id, String user, NewCommentDto newCommentDto);
	
	PostDto removePost(String id);
	
	Iterable<PostDto> findPostsByTags(List<String> tags);
	
	PostDto updatePost(String id, NewPostDto updateDto);
	
	Iterable<PostDto> findPostsByPeriod(PeriodDto period);
	
	
	
	
}
