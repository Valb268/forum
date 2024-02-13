package telran.java51.forum.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.forum.dto.MessageInCommentDto;
import telran.java51.forum.dto.PrePostDto;
import telran.java51.forum.dto.PushPostDto;
import telran.java51.forum.exceptions.PostNotFoundException;
import telran.java51.forum.model.Post;
import telran.java51.forum.repository.ForumRepository;

@RequiredArgsConstructor
@Service
public class ForumServiceImpl implements ForumService {

	final ForumRepository forumRepository;
	final ModelMapper modelMapper;
	
	@Override
	public Post addPost(String author, PushPostDto pushPostDto) {
		PrePostDto prePostDto = new PrePostDto(author, pushPostDto.getTitle(), pushPostDto.getTags(), pushPostDto.getContent());
		return forumRepository.save(modelMapper.map(prePostDto, Post.class));
	}

	@Override
	public Post findPostById(String id) {
		return forumRepository.findById(id).orElseThrow(PostNotFoundException::new);
	}

	@Override
	public void addLike(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> findPostsByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post addComment(String id, String user,
			MessageInCommentDto messageInCommentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> findPostsByTags(String[] tags) {
		// TODO Auto-generated method stub
		return null;
	}

}
