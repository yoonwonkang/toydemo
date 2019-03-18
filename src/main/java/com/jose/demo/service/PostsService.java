package com.jose.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.demo.domain.post.PostsRepository;
import com.jose.demo.dto.PostsMainResponseDto;
import com.jose.demo.dto.PostsSaveRequestDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {
	private PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto dto) {
		return postsRepository.save(dto.toEntity()).getId();
	}
	
	@Transactional(readOnly = true)
	public List<PostsMainResponseDto> findAllDesc() {
		return postsRepository.findAllDesc()
				//.map(posts -> new PostsMainResponseDto(posts))
				.map(PostsMainResponseDto::new)
				.collect(Collectors.toList());
	}
}
