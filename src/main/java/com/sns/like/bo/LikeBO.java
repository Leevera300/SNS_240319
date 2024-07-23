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
		int count = likeMapper.selectLikeCountByPostIdUserId(postId, userId);
		// 여부 => 삭제 or 추가
		if (count == 0) {
			likeMapper.addLike(postId, userId);
		} else if (count > 0) {
			likeMapper.deleteLike(postId, userId);
		}
		
	}
	
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostId(postId);
	}
	
	public boolean getLikeByPostIdUserId(int postId, Integer userId) {
		if (userId == null) {
			return false;
		}
		return likeMapper.selectLikeByPostIdUserId(postId, userId);
	}
}
