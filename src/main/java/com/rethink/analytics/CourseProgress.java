package com.rethink.analytics;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document(collection = "courseProgress")
@JsonInclude(Include.NON_NULL)
public class CourseProgress {
	private String id;
	private String userId;
	private String courseId;
	private List<Interaction> interactionsCompleted;
	private List<InteractionResponseRoscoe> interactionResponses;
	
	public CourseProgress() {
		this.id = "";
		this.userId = "";
		this.courseId = "";
		this.interactionResponses = null;
		this.interactionsCompleted = null;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public String getCourseId() {
		return this.courseId;
	}
	
	public List<Interaction> getInteractionsCompleted() {
		return this.interactionsCompleted;
	}
	
	public List<InteractionResponseRoscoe> getInteractionResponses() {
		return this.interactionResponses;
	}
	
	public InteractionResponse getResponseIfExists( Interaction interactionToSearchFor ) {
		InteractionResponseRoscoe intRos = new InteractionResponseRoscoe(interactionToSearchFor.getId(), interactionToSearchFor.getCompletionDateTime(), "");
		if ( interactionResponses.contains( intRos ))
			return new InteractionResponse(interactionToSearchFor.getId(),interactionToSearchFor.getCompletionDateTime()
					, interactionResponses.get( interactionResponses.indexOf( intRos )).getResponse() );
		return null;
	}
}
