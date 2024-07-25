package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	// input: postId, loginId
	// output: X
	public void likeToggle(int postId, int userId) {
		// 조회
		int count = likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
		// 여부 => 삭제 or 추가
		if (count == 0) {
			likeMapper.addLike(postId, userId);
		} else if (count > 0) {
			likeMapper.deleteLike(postId, userId);
		}
		
	}
	// input: postId
	// output: int(좋아요 개수)
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	// 좋아요 채울지 여부
	// input: postId(필수), userId(로그인/비로그인)
	// output:boolean (채울지 여부)
	public boolean filledLikeByPostIdUserId(int postId, Integer userId) {
		// 비로그인이면 DB 조회 없어 하트 채우지 않음
		if (userId == null) {
			return false;
		}
		// 로그인이면 1. 행이있으면(1) true	2. 없으면(0) false
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) == 1 ? true : false;  
	}
	
	//input: postId
	// output: X
	public void deleteLikeByPostId(int postId) {
		likeMapper.deleteLikeByPostId(postId);
	}
}
