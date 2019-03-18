package com.jose.demo.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.jose.demo.domain.post.Posts;

import lombok.Getter;

@Getter
public class PostsMainResponseDto {
	private Long id;
	private String title;
	private String author;
	private String modifiedDate;
	
	public PostsMainResponseDto(Posts entity) {
		id = entity.getId();
		title = entity.getTitle();
		author = entity.getAuthor();
		modifiedDate = toStringDateTime(entity.getModifiedDate());
	}
	
	/**
	 * for java version 8
	 * @param localDateTime
	 * @return string
	 */
	private String toStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return Optional.ofNullable(localDateTime)
				//format을 이용해 얻은 결과가 비어있는지 아닌지
				.map(formatter::format)
				//만약 비어있을경우 어떤값을 default로 반환할지 보내기 아닐경우 format을 리턴하여 사용
				.orElse("");
	}
	
	private String toStringDateTimeByJava7(LocalDateTime localDateTime) {
		if(localDateTime == null) {
			return "";
			
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(localDateTime);
	}
}
