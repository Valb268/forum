package telran.java51.forum.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.forum.dto.NewCommentDto;
import telran.java51.forum.dto.NewPostDto;
import telran.java51.forum.dto.PeriodDto;
import telran.java51.forum.dto.PostDto;
import telran.java51.forum.exceptions.PostNotFoundException;
import telran.java51.forum.model.Post;
import telran.java51.forum.repository.ForumRepository;

@RequiredArgsConstructor
@Service
public class ForumServiceImpl implements ForumService {

	final ForumRepository forumRepository;
	final ModelMapper modelMapper;
	
	@Override
	public PostDto addPost(String author, NewPostDto pushPostDto) {
		PostDto postDto = modelMapper.map(pushPostDto, PostDto.class);
		postDto.setAuthor(author);
		Post post = forumRepository.save(modelMapper.map(postDto, Post.class));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String id) {
		
		Post post = forumRepository.findById(id).orElseThrow(PostNotFoundException::new);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public boolean addLike(String id) {
		Post post = forumRepository.findById(id).orElseThrow(PostNotFoundException::new);
		post.addLike();
		post = forumRepository.save(post);
		return post != null;
	}

	@Override
	public Iterable<PostDto> findPostsByAuthor(String author) {
		return forumRepository.findPostsByAuthorIgnoreCase(author)
					.map(p -> modelMapper.map(p, PostDto.class))
					.collect(Collectors.toList());
	}

	@Override
	public PostDto addComment(String id, String user,
			NewCommentDto newCommentDto) {
		Post post = forumRepository.findById(id).orElseThrow(PostNotFoundException::new);
		post.addComment(user, newCommentDto.getMessage());
		post = forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto removePost(String id) {
		Post post = forumRepository.findById(id).orElseThrow(PostNotFoundException::new);
		forumRepository.deleteById(id);
		return modelMapper.map(post, PostDto.class);
		
	}

	@Override
	public Iterable<PostDto> findPostsByTags(List<String> tags) {
		
		return forumRepository.findByTagsInIgnoreCase(tags)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PostDto updatePost(String id, NewPostDto updateDto) {
		Post post = forumRepository.findById(id).orElseThrow(PostNotFoundException::new);
		String content = updateDto.getContent();
		if (content != null) {
			post.setContent(updateDto.getContent());
		}
		String title = updateDto.getTitle();
		if (title != null) {
			post.setTitle(updateDto.getTitle());
		}
		Set<String> tags = updateDto.getTags();
		if (tags != null) {
			tags.addAll(updateDto.getTags());
		}
		forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public Iterable<PostDto> findPostsByPeriod(PeriodDto period) {
		return forumRepository.findByDateCreatedBetween(period.getDateFrom(), period.getDateTo())
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

}
