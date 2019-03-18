package com.jose.demo.domain.post;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//xbatis에서 Dao로 불린 DB layer 접근자
public interface PostsRepository extends JpaRepository<Posts, Long> {
	
	@Query(value = "SELECT * " 
			+ "FROM Posts p "
			+ "ORDER BY p.id DESC",
			nativeQuery = true)
	Stream<Posts> findAllDesc();
}
