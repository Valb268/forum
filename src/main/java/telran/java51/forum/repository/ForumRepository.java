package telran.java51.forum.repository;

import org.springframework.data.repository.CrudRepository;

import telran.java51.forum.model.Post;

public interface ForumRepository extends CrudRepository<Post, String>{
	
	

}
