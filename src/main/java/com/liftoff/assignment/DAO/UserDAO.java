package com.liftoff.assignment.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Component;

import com.liftoff.assignment.API.TwitterAPIConnectionManager;

@Component
public class UserDAO {
	
	TwitterAPIConnectionManager twitterConMan = new TwitterAPIConnectionManager();
	
	Twitter twitter;
	
	public UserDAO(){
		twitter=twitterConMan.establishConnection(twitter);
	}

	public CursoredList<Long> getUsersFollowers(String handle){

		CursoredList<Long> finalList = new CursoredList<Long>(5000,0,0);
		long cursorValue = -1;
		do{
			CursoredList<Long> temp = twitter.friendOperations().getFollowerIdsInCursor(handle,cursorValue);
			finalList.addAll(temp);
			cursorValue = temp.getNextCursor();
		}while(cursorValue!=0);

		return finalList;
	}

	public List<TwitterProfile> getUsersData(List<Long> cursoredList) {
		List<TwitterProfile> followers = new ArrayList<>();
		for(int i = 0; i< cursoredList.size(); i+=100){
			followers.addAll(getProfiles(cursoredList, i));
		}
		return followers;
	}

	private List<TwitterProfile> getProfiles(List<Long> cursoredList, int start){
		int end = Math.min(start+100, cursoredList.size());
		List<Long> temp = cursoredList.subList(start, end);
		long[] ids = temp.stream().mapToLong(l -> l).toArray();
		List<TwitterProfile> profiles = twitter.userOperations().getUsers(ids);
		return profiles;
	}

}
