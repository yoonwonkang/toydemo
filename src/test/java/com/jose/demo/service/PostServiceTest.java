package com.jose.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo.BuilderConfiguration;

import com.jose.demo.domain.post.Posts;
import com.jose.demo.domain.post.PostsRepository;
import com.jose.demo.dto.PostsMainResponseDto;
import com.jose.demo.dto.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	private PostsService postService;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void dto_save_to_post() {
		//given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
				.author("yoonwon.kang@gmail.com")
				.content("테스")
				.title("타이")
				.build();
		
		
		//when
		postService.save(dto);
		
		List<Posts> posts_list = postsRepository.findAll();
		Posts posts = posts_list.get(0);
//		assertTrue(posts_list.size() == 1);
		assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());

		assertThat(posts.getContent()).isEqualTo(dto.getContent());
		assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
		
	}
	
//	@Test
//	public void dto_all_list() {
//		//when
//		List<PostsMainResponseDto> posts_list = postService.findAllDesc();
//		
//		PostsMainResponseDto dto = posts_list.get(0);
//		assertThat(dto.getAuthor(), is("test2@gmail.com"));			
//
//	}
}
