package com.liftoff.assignment.API;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import com.liftoff.assignment.constant.AppConstants;


@Component
public class TwitterAPIConnectionManager {
	
	public Twitter establishConnection(Twitter twitter){
		twitter = new TwitterTemplate(AppConstants.CONSUMER_KEY, AppConstants.CONSUMER_SECRET
				,AppConstants.ACCESS_KEY,AppConstants.ACCESS_SECRET);
		return twitter;
	}
}
