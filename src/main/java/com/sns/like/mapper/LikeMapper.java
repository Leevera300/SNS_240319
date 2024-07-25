package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface LikeMapper {

	// 카운트 쿠리를 한로 합친다.
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId);

	public void addLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void deleteLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void deleteLikeByPostId(int postId);
}
