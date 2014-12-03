package pl.mkorwel.app.manager.application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@SuppressWarnings("unused")
public class ApplicationHistory {

	@Id
	@GeneratedValue
	private Long id;
	
	private Long applicationId;

	private String name;

	private String content;

	private ApplicationState state;

	private String cancelReason;
	
	private ApplicationHistory(){
	}
	
	public ApplicationHistory(Application application){
		applicationId = application.getId();
		name = application.getName();
		content = application.getContent();
		state = application.getState();
		cancelReason = application.getCancelReason();
	}
}
