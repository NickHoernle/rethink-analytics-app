package com.rethink.analytics;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

public class Session {
	@Id
	private final String id;
	
	private DateTime startDateTime;
	private DateTime endDateTime;
	private String userId;
	private String courseId;
	private String chapterId;
	private List<Interaction> userInteractions;

	public Session() {
		this.id = "";
		this.startDateTime = null;
		this.endDateTime = null;
		this.userId = "";
		this.courseId = "";
		this.chapterId = "";
		this.userInteractions = null;
	}
	
	public Session( String id, String userId, String courseId, String chapterId, List<Interaction> userInteractions ) {
		this.id = id;
		this.userId = userId;
		this.courseId = courseId;
		this.chapterId = chapterId;
		this.userInteractions = userInteractions;
	}
	
	public int compare( Session s, Session s1 ) {
		if ( s.endDateTime.getMillis() - s1.endDateTime.getMillis() > 0 )
			return 1;
		else 
			return -1;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public String getChapterId() {
		return this.chapterId;
	}
	
	public String getCourseId() {
		return this.courseId;
	}
	
	public List<Interaction> getUserInteractions() {
		return this.userInteractions;
	}
	
	public long getStartDateTime() {
		return this.startDateTime.getMillis();
	}
	
	public long getEndDateTime() {
		return this.endDateTime.getMillis();
	}

	public void insertInteraction(Interaction interaction) {
		userInteractions.add( interaction );
		updateStartAndEndTime( interaction );
	}

	private void updateStartAndEndTime(Interaction interaction ) {
		if ( startDateTime == null || startDateTime.isAfter( interaction.getCompletionDateTime() ) )
			startDateTime = new DateTime( interaction.getCompletionDateTime() );
		if ( endDateTime == null || endDateTime.isBefore( interaction.getCompletionDateTime() ) )
			endDateTime = new DateTime( interaction.getCompletionDateTime() );
	}
}
