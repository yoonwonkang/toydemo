package com.jose.demo.domain.posts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jose.demo.domain.post.Posts;
import com.jose.demo.domain.post.PostsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		/*
		 테스트 코드에 영향을 주지 않기 위해
		 테스트 메소드가 끝날때 마다 repo를 비우는 코드
		 */
		
		postsRepository.deleteAll();
	}
	
	@Test
	public void load_post() {
		//given
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("yoonwon.kang@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 본문"));
	}
	
	@Test
	public void BaseTimeEntity_regist() {
		//given
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder()
				.title("테스트 게시글")
				.content("본문")
				.author("yoonwon.kang@gmail.com")
				.build());
		//when
		List<Posts> postsList = postsRepository.findAll();
		
		//then
		Posts posts = postsList.get(0);
		assertTrue(posts.getCreatedDate().isAfter(now));
		assertTrue(posts.getModifiedDate().isAfter(now));
	}
	
////	stream 사용방법 ->그래서내부에서 처리해주는게 맞다
//	@Test
//	@Transactional(readOnly = true)
//	public void load_all_post() {
//		//when
//		try (Stream<Posts> stream = postsRepository.findAllDesc()) {
//			List<Posts> posts_list = stream.collect(Collectors.toList());
//			//then
//			assertTrue(posts_list.size() == 2);
//			Posts posts = posts_list.get(0);
//			assertThat(posts.getAuthor(), is("test2@gmail.com"));			
//		}
//
//	}
}
