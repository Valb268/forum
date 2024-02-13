package telran.java51.forum.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.forum.dto.MessageInCommentDto;
import telran.java51.forum.dto.PostDto;
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
	public PostDto addPost(String author, PushPostDto pushPostDto) {
		PostDto postDto = new PostDto(author, pushPostDto.getTitle(),
				pushPostDto.getTags(), pushPostDto.getContent());
		Post post = forumRepository.save(modelMapper.map(postDto, Post.class));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String id) {
		
		Post post = forumRepository.findById(id).orElseThrow(PostNotFoundException::new);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> findPostsByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto addComment(String id, String user,
			MessageInCommentDto messageInCommentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> findPostsByTags(String[] tags) {
		// TODO Auto-generated method stub
		return null;
	}

}
