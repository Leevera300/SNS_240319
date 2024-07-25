package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// input: X
	// output: List<PostEntity>
	public List<PostEntity> getPostEntityList() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
	// input: userId, content, file
	// output: void
	public PostEntity addPost(int userId, String content, MultipartFile file) {
		
		String imagePath = null;
		if(file != null) {
			// 파일이 업로드 할 이미지가 있을 때에만 업로드
			imagePath = fileManagerService.uploadFile(file, userId);
		}
		
		PostEntity post = PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build();
		
		return postRepository.save(post);
	}
	
	// input: postId, userId
	// output: X
	// 관련 댓글 라이크 이미지 다 삭제하기!!!!
	@Transactional
	public void deletePostByPostIdUserId(int postId, int userId) {
		PostEntity post = postRepository.findByIdAndUserId(postId, userId);
		if (post == null) {
			log.warn("[포스트 삭제] post is null. postId:{}", postId);
			return;
		}
		
		// 포스트 삭제
		postRepository.deleteById(postId);
		
		// 댓글 삭제
		commentBO.deleteCommentByPostId(postId);
			
		// 라이크 삭제
		likeBO.deleteLikeByPostId(postId);
			
		// 이미지 삭제
		fileManagerService.deleteFile(post.getImagePath());
		
	}
}
