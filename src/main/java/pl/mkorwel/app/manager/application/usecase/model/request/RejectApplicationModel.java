package pl.mkorwel.app.manager.application.usecase.model.request;

public class RejectApplicationModel {

	private final Long id;

	private final String reason;

	public RejectApplicationModel(Long id, String reason) {
		super();
		this.id = id;
		this.reason = reason;
	}

	public Long getId() {
		return id;
	}

	public String getReason() {
		return reason;
	}

}
