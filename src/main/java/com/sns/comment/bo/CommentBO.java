package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	// input: postId, userId, comment
	//output: void
	public void addComment(int postId, int userId, String comment) {
		commentMapper.insertComment(postId, userId, comment);
	}
	
	// input: postId
	// output: List<CommentView>
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 댓글들 가져옴
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		// 반복문 순회 => Comment => CoommentView => list에 담기
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글
			commentView.setComment(comment);
			
			// 글쓰니
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		
		return commentViewList;
	}
	
	// input: commentId
	// output: X
	public void deleteCommentById(int id) {
		commentMapper.deleteCommentById(id);
	}
	
	// input: postId
	// output: X
	public void deleteCommentByPostId(int postId) {
		commentMapper.deleteCommentByPostId(postId);
	}
}
