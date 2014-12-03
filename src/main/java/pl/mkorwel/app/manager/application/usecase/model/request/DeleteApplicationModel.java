package pl.mkorwel.app.manager.application.usecase.model.request;

public class DeleteApplicationModel {

	private final Long id;

	private final String reason;

	public DeleteApplicationModel(Long id, String reason) {
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
