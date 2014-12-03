package pl.mkorwel.app.manager.application.usecase.model.request;

import pl.mkorwel.app.manager.application.domain.Application;
import pl.mkorwel.app.manager.application.domain.ApplicationState;

public class ApplicationReadModel {

	private Long id;

	private String name;

	private String content;

	private ApplicationState state;

	private String cancelReason;

	public ApplicationReadModel(Application application) {
		this.id = application.getId();
		this.name = application.getName();
		this.content = application.getContent();
		this.state = application.getState();
		this.cancelReason = application.getCancelReason();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ApplicationState getState() {
		return state;
	}

	public void setState(ApplicationState state) {
		this.state = state;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

}