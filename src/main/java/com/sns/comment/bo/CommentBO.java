package com.sns.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.mapper.CommentMapper;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	// input: postId, userId, comment
	//output: void
	public void addComment(int postId, int userId, String comment) {
		commentMapper.insertComment(postId, userId, comment);
	}
}
