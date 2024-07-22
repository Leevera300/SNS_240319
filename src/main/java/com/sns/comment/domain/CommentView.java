package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
// 댓글 하나를 가지는 class
public class CommentView {
	// 댓글 1개
	private Comment comment;
	
	// 댓글쓴이
	private UserEntity user;
}
