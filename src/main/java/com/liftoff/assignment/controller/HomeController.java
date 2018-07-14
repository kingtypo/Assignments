package com.liftoff.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liftoff.assignment.logic.TwitterOperations;
import com.liftoff.assignment.model.Followers;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	TwitterOperations twitterOperations;

	@RequestMapping(method=RequestMethod.GET)
	public String greetingForm(Model model) {
		model.addAttribute("followers", new Followers());
		return "greeting";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute Followers followers) {
		
		followers.setMutualFollowersData(twitterOperations.getMutualFollowers(followers.getHandle1(), followers.getHandle2()));
		
		return "result";
	}

}
