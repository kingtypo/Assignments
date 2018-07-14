package com.liftoff.assignment.model;

import java.util.List;

import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Component;

@Component
public class Followers {
	
	private String handle1;
	private String handle2;
	private List<TwitterProfile> mutualFollowersData;

	public String getHandle1() {
		return handle1;
	}

	public void setHandle1(String handle1) {
		this.handle1 = handle1;
	}

	public String getHandle2() {
		return handle2;
	}

	public void setHandle2(String handle2) {
		this.handle2 = handle2;
	}

	public List<TwitterProfile> getMutualFollowersData() {
		return mutualFollowersData;
	}

	public void setMutualFollowersData(List<TwitterProfile> mutualFollowersData) {
		this.mutualFollowersData = mutualFollowersData;
	}
	
	

}
