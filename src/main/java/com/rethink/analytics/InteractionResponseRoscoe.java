package com.rethink.analytics;

public class InteractionResponseRoscoe extends Interaction {
	private String response;
	private long responseTime;
	
	public InteractionResponseRoscoe() {
		super();
		this.response = "";
		this.responseTime = 0;
	}
	
	public InteractionResponseRoscoe( String id, long completionDateTime, String response) {
		super(id, completionDateTime);
		this.response = response;
		this.responseTime = completionDateTime;
	}
	
	public String getResponse() {
		return this.response;
	}
	
	public long getResponseTime() {
		return responseTime;
	}
}
