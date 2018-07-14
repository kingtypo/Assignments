package com.liftoff.assignment.logic;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Component;

import com.liftoff.assignment.DAO.UserDAO;

@Component
public class TwitterOperations {
	
	@Autowired
	UserDAO userDAO;
	
	public List<TwitterProfile> getMutualFollowers(String handle_A, String handle_B){
		
		List<TwitterProfile> mutualFollowersData = null;

		CursoredList<Long> followers_A = userDAO.getUsersFollowers(handle_A);
		CursoredList<Long> followers_B = userDAO.getUsersFollowers(handle_B);

		List<Long> mutual = (List<Long>) followers_A.stream().parallel().filter(followers_B::contains).collect(toList());

//		System.out.println(mutual.size());
//		System.out.println(mutual);

		mutualFollowersData = userDAO.getUsersData(mutual);
		
//		mutualFollowersData.forEach(i -> System.out.println(i.getName() + "--> " + i.getScreenName()));
		
		return mutualFollowersData;
		

		
	}

}
