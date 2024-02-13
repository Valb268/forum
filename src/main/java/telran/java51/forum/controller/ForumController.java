package telran.java51.forum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.forum.dto.PushPostDto;
import telran.java51.forum.model.Post;
import telran.java51.forum.service.ForumService;

@RestController
@RequiredArgsConstructor
public class ForumController {
	
	final ForumService forumService;
	
	@PostMapping("/forum/post/{author}")
	Post addPost(@PathVariable String author, @RequestBody PushPostDto pushPostDto) {
		return forumService.addPost(author, pushPostDto);
	}
	
	@GetMapping("/forum/post/{id}")
	Post findPostById(@PathVariable String id) {
		return forumService.findPostById(id);
	}
	
	
	
	
}
