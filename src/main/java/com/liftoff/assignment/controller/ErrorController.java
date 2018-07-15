package com.liftoff.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liftoff.assignment.constant.AppConstants;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String handleError() {
		return AppConstants.ERROR_PAGE;
	}

}
