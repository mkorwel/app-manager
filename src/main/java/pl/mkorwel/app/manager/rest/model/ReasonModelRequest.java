package pl.mkorwel.app.manager.rest.model;

import java.io.Serializable;

public class ReasonModelRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
