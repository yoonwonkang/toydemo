package com.jose.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jose.demo.domain.post.PostsRepository;
import com.jose.demo.dto.PostsSaveRequestDto;
import com.jose.demo.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class HelloController {
	
	private PostsService postsService;

	@GetMapping("/hello")
	public String hello(@RequestParam String name) {
		return "Hello " + name;
	}

	@PostMapping("/posts")
	public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
		return postsService.save(dto);
	}
}
