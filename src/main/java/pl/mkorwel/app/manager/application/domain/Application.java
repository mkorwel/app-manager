package pl.mkorwel.app.manager.application.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;

@Entity
public class Application {

	@Id
	@GeneratedValue
	private Long id;

	protected String name;

	protected String content;

	protected ApplicationState state;

	protected String cancelReason;

	@SuppressWarnings("unused")
	private Application() {
	}

	public Application(String name, String content) {
		this.name = Objects.requireNonNull(name, "Name is required");
		this.content = Objects.requireNonNull(content, "Content is required");
		this.state = ApplicationState.CREATED;
	}

	public void changeName(String name) {
		validateChangeData();
		this.name = Objects.requireNonNull(name, "Name is required");
	}

	public void changeContent(String content) {
		validateChangeData();
		this.content = Objects.requireNonNull(content, "Content is required");
	}
	
	private void validateChangeData(){
		if (!(state == ApplicationState.CREATED || state == ApplicationState.VERIFIED)) {
			throw new WrongStateApplicationException();
		}
	}

	public void accept() {
		if (state != ApplicationState.VERIFIED) {
			throw new WrongStateApplicationException();
		}
		state = ApplicationState.ACCEPTED;
	}

	public void publish() {
		if (state != ApplicationState.ACCEPTED) {
			throw new WrongStateApplicationException();
		}
		state = ApplicationState.PUBLISHED;
	}

	public void verify() {
		if (state != ApplicationState.CREATED) {
			throw new WrongStateApplicationException();
		}
		state = ApplicationState.VERIFIED;
	}

	public void reject(String reason) {
		if (!(state == ApplicationState.VERIFIED || state == ApplicationState.ACCEPTED)) {
			throw new WrongStateApplicationException();
		}
		state = ApplicationState.REJECTED;
		cancelReason = Objects.requireNonNull(reason, "Reason is required");
	}

	public void delete(String reason) {
		if (state != ApplicationState.CREATED) {
			throw new WrongStateApplicationException();
		}
		state = ApplicationState.DELETED;
		cancelReason = Objects.requireNonNull(reason, "Reason is required");
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

	public ApplicationState getState() {
		return state;
	}

	public String getCancelReason() {
		return cancelReason;
	}

}
