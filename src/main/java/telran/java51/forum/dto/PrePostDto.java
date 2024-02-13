package telran.java51.forum.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PrePostDto extends PushPostDto {
	String author;

	public PrePostDto(String author, String title, List<String> tags, String content) {
		super(title, tags, content);
		this.author = author;
	}
	
	
}
