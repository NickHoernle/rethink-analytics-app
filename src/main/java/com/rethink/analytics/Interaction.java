package com.rethink.analytics;

public class Interaction {
	private String id;
	protected long completionDateTime;
	
	public Interaction() {
		this.id = "";
		this.completionDateTime = 0;
	}
	
	public Interaction( String id, long completionDateTime) {
		this.id = id;
		this.completionDateTime = completionDateTime;
	}
	
	public String getId() {
		return this.id;
	}
	
	public long getCompletionDateTime() {
		return this.completionDateTime;
	}
	
	@Override
	public boolean equals( Object obj ) {
		boolean same = false;
		if (obj != null && obj instanceof Interaction )
		{
			Interaction interact = (Interaction) obj;
			same = ( interact.getId().equals(this.id));
		}
		return same;
	}
}
