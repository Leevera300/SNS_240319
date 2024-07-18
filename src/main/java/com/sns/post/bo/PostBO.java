package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
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
}
