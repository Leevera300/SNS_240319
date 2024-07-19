package com.sns.comment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

	public List<Map<String, Object>> selectCommentListTest();
	
	public int insertComment(
			@Param("postId") int postId, 
			@Param("userId") int userId, 
			@Param("comment") String comment);
}
