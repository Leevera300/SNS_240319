package com.sns.comment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.comment.domain.Comment;

@Mapper
public interface CommentMapper {

	public List<Map<String, Object>> selectCommentListTest();
	
	public int insertComment(
			@Param("postId") int postId, 
			@Param("userId") int userId, 
			@Param("comment") String comment);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public void deleteCommentById(int id);
	
	public void deleteCommentByPostId(int postId);
}
