package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface LikeMapper {

	public int selectLikeCountByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void addLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void deleteLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public int selectLikeCountByPostId(int postId);
	
	public boolean selectLikeByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
}
