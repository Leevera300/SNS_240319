package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline-view")
	public String timelineView(Model model) {
		// 글 조회
		// List<PostEntity> postList = postBO.getPostEntityList();
		//List<Comment> commentList = commentBO.getCommentListByPostId(postId);
		List<CardView> cardViewList = timelineBO.generateCardViewList();
		
		// model 담기
		// model.addAttribute("postList", postList);
		model.addAttribute("cardViewList", cardViewList);
		
		return "timeline/timeline";
	}
}
