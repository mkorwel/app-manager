package pl.mkorwel.app.manager.application.usecase;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import pl.mkorwel.app.manager.application.domain.Application;
import pl.mkorwel.app.manager.application.domain.ApplicationState;
import pl.mkorwel.app.manager.application.domain.error.ApplicationErrorCode;
import pl.mkorwel.app.manager.application.repository.ApplicationHistoryRepository;
import pl.mkorwel.app.manager.application.repository.ApplicationRepository;
import pl.mkorwel.app.manager.base.usecase.model.response.Result;

public class PublishApplicationUserCaseTest {

	@Test
	public void publishWithSuccess() {
		
		Application application = new Application("testName", "testContent") {{	state = ApplicationState.ACCEPTED; }};
		ApplicationRepository repository = mock(ApplicationRepository.class);
		when(repository.findOne(anyLong())).thenReturn(application);
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
		
		Result<Void> result = new PublishApplication(repository, historyRepository).execute(1L);

		assertThat(application.getState()).isEqualTo(ApplicationState.PUBLISHED);
		assertThat(result).isEqualTo(Result.EMPTY_RESULT);
	}
	
	@Test
	public void publishWithError() {
		Application application = new Application("testName", "testContent") {{	state = ApplicationState.CREATED; }};
		ApplicationRepository repository = mock(ApplicationRepository.class);
		when(repository.findOne(anyLong())).thenReturn(application);
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
		
		Result<Void> result = new PublishApplication(repository, historyRepository).execute(1L);
		
		assertThat(application.getState()).isEqualTo(ApplicationState.CREATED);
		assertThat(result.isError()).isEqualTo(true);
		assertThat(result.getErrors()).containsOnly(ApplicationErrorCode.WRONG_STATE);
	}
}
