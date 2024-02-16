package telran.java51.forum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.forum.dto.NewCommentDto;
import telran.java51.forum.dto.NewPostDto;
import telran.java51.forum.dto.PeriodDto;
import telran.java51.forum.dto.PostDto;
import telran.java51.forum.service.ForumService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class ForumController {
	
	final ForumService forumService;
	
	@PostMapping("/post/{author}")
	PostDto addPost(@PathVariable String author, @RequestBody NewPostDto newPostDto) {
		return forumService.addPost(author, newPostDto);
	}
	
	@GetMapping("/post/{id}")
	PostDto findPostById(@PathVariable String id) {
		return forumService.findPostById(id);
	}
	
	@PutMapping("/post/{id}/like")
	void addLike(@PathVariable String id) {
		forumService.addLike(id);
	}
	
	@GetMapping("/posts/author/{author}")
	Iterable<PostDto> findPostsByAuthor(@PathVariable String author) {
		return forumService.findPostsByAuthor(author);
	}
	
	@PutMapping("/post/{id}/comment/{user}")
	PostDto addComment(@PathVariable String id, @PathVariable String user, @RequestBody NewCommentDto newCommentDto) {
		return forumService.addComment(id, user, newCommentDto);
	}
	
	@DeleteMapping("/post/{id}")
	PostDto removePost(@PathVariable String id) {
		return forumService.removePost(id);
	}
	
	@PostMapping("/posts/tags")
	Iterable<PostDto> findPostsByTags(@RequestBody List<String> tags) {
		return forumService.findPostsByTags(tags);
	}
	
	@PostMapping("/posts/period")
	Iterable<PostDto> findPostsByPeriod(@RequestBody PeriodDto period) {
		return forumService.findPostsByPeriod(period);
	}
	
	@PutMapping("/post/{id}")
	PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto updateDto) {
		return forumService.updatePost(id, updateDto);
	}
	
}
