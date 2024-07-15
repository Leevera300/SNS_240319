package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/sign-up-view")
	public String signUpView() {
		return "user/signUp";
	}
	
	@GetMapping("/sign-in-view")
	public String signInView() {
		return "user/signIn";
	}
	
	/**
	 * 로그아웃 API
	 * @param session
	 * @return
	 */
	@RequestMapping("/sign-out")
	public String signOut(HttpSession session) {
		// session 비우기
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		// redirect
		return "redirect:/user/sign-in-view";
	}
}
