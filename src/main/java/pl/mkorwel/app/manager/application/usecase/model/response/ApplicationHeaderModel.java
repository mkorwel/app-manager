package pl.mkorwel.app.manager.application.usecase.model.response;

import pl.mkorwel.app.manager.application.domain.Application;
import pl.mkorwel.app.manager.application.domain.ApplicationState;

public class ApplicationHeaderModel {

	private final Long id;

	private final String name;

	private final String content;

	private final ApplicationState status;

	public ApplicationHeaderModel(Application application) {
		super();
		this.id = application.getId();
		this.name = application.getName();
		this.content = application.getContent();
		this.status = application.getState();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public ApplicationState getStatus() {
		return status;
	}

}
