package com.liftoff.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.RateLimitExceededException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liftoff.assignment.constant.AppConstants;
import com.liftoff.assignment.constant.ExceptionConstants;
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
		return AppConstants.LANDING_PAGE;
	}

	@RequestMapping(method=RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute Followers followers) {
		
		followers.setErrormessage(null);
		String userA = followers.getHandle1();
		String userB = followers.getHandle2();
		try{
			boolean aFound = twitterOperations.findUser(userA);
			
			if(aFound){
				boolean bFound = twitterOperations.findUser(userB);
				if(bFound){
					followers.setMutualFollowersData(twitterOperations.getMutualFollowers(followers.getHandle1(), followers.getHandle2()));
					return AppConstants.RESULT_PAGE;
				}
				else{
					String emessage = userB + " " + ExceptionConstants.DOES_NOT_EXIST;
					followers.setErrormessage(emessage);
					return AppConstants.LANDING_PAGE;
				}
			}
			else{
				String emessage = userA + " " + ExceptionConstants.DOES_NOT_EXIST;
				followers.setErrormessage(emessage);
				return AppConstants.LANDING_PAGE;
			}
		}
		catch(RateLimitExceededException e){
			followers.setErrormessage(ExceptionConstants.RATE_LIMIT_EXCEEDED);
			return AppConstants.ERROR_PAGE;
		}
		catch(Exception e){
			return AppConstants.ERROR_PAGE;
		}
		
	}
}
