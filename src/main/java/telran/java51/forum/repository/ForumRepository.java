package telran.java51.forum.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import telran.java51.forum.model.Post;

public interface ForumRepository extends CrudRepository<Post, String>{
	
	Stream<Post> findPostsByAuthorIgnoreCase(String author);
	
	Stream<Post> findByTagsInIgnoreCase(List<String> tags);
	
	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);

}
