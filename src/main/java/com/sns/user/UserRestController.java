package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;

	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		
		// DB 확인
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		if (user != null) {
			result.put("is_duplicated_id", true);
		} else {
			result.put("is_duplicated_id", false);
		}
		return result;
	}
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		// password md5 알고리즘 => hashing (project 때는 다른 알고리즘 찾아보기)
		// aaaa => 74b8733745420d4d33f80c4663dc5e5
		String hashedPassword = EncryptUtils.md5(password);
		
		// DB insert
		UserEntity user = userBO.addUser(loginId, hashedPassword, name, email);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "회원가입에 실패했습니다.");
		}
		return result;
	}
	
	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session) {
		// password hashing
		String hashedPassword = EncryptUtils.md5(password);
		
		// DB  조회 - loginId, 해싱되 비밀번호 => UserEntity
		UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);
		
		// 로그인 처리 및 응답값
		Map<String, Object> result = new HashMap<>();
			if (user != null) { // 성공
				// 세션에 사용자 정보를 담는다.(사용자 각각 마다)
				session.setAttribute("userId", user.getId());
				session.setAttribute("userLoginId", user.getLoginId());
				session.setAttribute("userName", user.getName());
					
				result.put("code", 200);
				result.put("result", "성공");
			} else {
				result.put("code", 403);
				result.put("error_message", "존재하지 않는 사용자입니다.");
			}
		
		return result;
	}
}

