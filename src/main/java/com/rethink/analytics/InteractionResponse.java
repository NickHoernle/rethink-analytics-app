package com.rethink.analytics;

public class InteractionResponse extends Interaction {
	private String response;
	
	public InteractionResponse() {
		super();
		this.response = "";
	}
	
	public InteractionResponse( String id, long completionDateTime, String response) {
		super(id, completionDateTime);
		this.response = response;
	}
	
	public String getResponse() {
		return this.response;
	}
}