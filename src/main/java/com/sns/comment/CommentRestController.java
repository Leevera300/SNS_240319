package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create") 
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String comment,
			HttpSession session) {
		
		// 로그인 여부
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if(userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인 해주세요.");
			return result;
 		}
		
		// DB insert
		commentBO.addComment(postId, userId, comment);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
				
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(@RequestParam("commentId") int commentId,
			HttpSession session) {
		// 로그인 여부
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if(userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인 해주세요.");
			return result;
		 }
				
		// DB delete
		commentBO.deleteCommentById(commentId);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
						
		return result;
	}
}
