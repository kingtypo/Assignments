package com.liftoff.assignment.API;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import com.liftoff.assignment.constant.Constants;


@Component
public class TwitterAPIConnectionManager {
	
	public Twitter establishConnection(Twitter twitter){
		twitter = new TwitterTemplate(Constants.consumer_key, Constants.consumer_secret);
		return twitter;
	}
}
