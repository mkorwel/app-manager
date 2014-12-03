package pl.mkorwel.app.manager.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import pl.mkorwel.app.manager.application.domain.Application;
import pl.mkorwel.app.manager.application.domain.ApplicationState;
import pl.mkorwel.app.manager.application.domain.error.ApplicationErrorCode;
import pl.mkorwel.app.manager.application.repository.ApplicationHistoryRepository;
import pl.mkorwel.app.manager.application.repository.ApplicationRepository;
import pl.mkorwel.app.manager.application.usecase.model.request.ApplicationUpdateModel;
import pl.mkorwel.app.manager.application.usecase.model.request.DeleteApplicationModel;
import pl.mkorwel.app.manager.base.usecase.model.response.Result;

public class CRUDApplicationUseCaseTest {

	@Test
	public void createWithSuccess(){
		ApplicationRepository repository = mock(ApplicationRepository.class);
		when(repository.save(any(Application.class))).thenReturn(new Application("", ""));
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
		
		ApplicationUpdateModel request = new ApplicationUpdateModel();
		request.setName("name");
		request.setContent("content");
		
		Result<Long> result = new CRUDApplication(repository, historyRepository).create(request);

		assertThat(result.isError()).isEqualTo(false);
	}
	
	@Test
	public void createWithError(){
		ApplicationRepository repository = mock(ApplicationRepository.class);
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
		
		ApplicationUpdateModel request = new ApplicationUpdateModel();
		request.setName(null);
		request.setContent("content");
		
		Result<Long> result = new CRUDApplication(repository, historyRepository).create(request);

		assertThat(result.isError()).isEqualTo(true);
		assertThat(result.getErrors()).containsOnly(ApplicationErrorCode.NAME_IS_REQUIRED);
	}
	
	@Test
	public void updateWithSuccess(){
		ApplicationRepository repository = mock(ApplicationRepository.class);
		Application application = new Application("", "");
		when(repository.findOne(anyLong())).thenReturn(application);
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
		
		ApplicationUpdateModel request = new ApplicationUpdateModel();
		String newName = "name";
		String newContent = "content";
		request.setName(newName);
		request.setContent(newContent);
		
		Result<Void> result = new CRUDApplication(repository, historyRepository).update(request);

		assertThat(result.isError()).isEqualTo(false);
		assertThat(application.getName()).isEqualTo(newName);
		assertThat(application.getContent()).isEqualTo(newContent);
	}
	
	@Test
	public void updateWithError(){
		ApplicationRepository repository = mock(ApplicationRepository.class);
		Application application = new Application("", "");
		when(repository.findOne(anyLong())).thenReturn(application);
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
		
		ApplicationUpdateModel request = new ApplicationUpdateModel();
		String newContent = "content";
		request.setName(null);
		request.setContent(newContent);
		
		Result<Void> result = new CRUDApplication(repository, historyRepository).update(request);

		assertThat(result.isError()).isEqualTo(true);
		assertThat(result.getErrors()).containsOnly(ApplicationErrorCode.NAME_IS_REQUIRED);
	}
	
	@Test
	public void deleteWithSuccess(){
		ApplicationRepository repository = mock(ApplicationRepository.class);
		Application application = new Application("", "");
		when(repository.findOne(anyLong())).thenReturn(application);
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
		String reason = "reason";
	
		Result<Void> result = new CRUDApplication(repository, historyRepository).delete(new DeleteApplicationModel(1L, reason));

		assertThat(result.isError()).isEqualTo(false);
		assertThat(application.getCancelReason()).isEqualTo(reason);
		assertThat(application.getState()).isEqualTo(ApplicationState.DELETED);
	}
	
	@Test
	public void deleteWithError(){
		ApplicationRepository repository = mock(ApplicationRepository.class);
		Application application = new Application("", "");
		when(repository.findOne(anyLong())).thenReturn(application);
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
	
		Result<Void> result = new CRUDApplication(repository, historyRepository).delete(new DeleteApplicationModel(1L, null));

		assertThat(result.isError()).isEqualTo(true);
		assertThat(result.getErrors()).containsOnly(ApplicationErrorCode.REASON_IS_REQUIRED);
	}
}
