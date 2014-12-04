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
import pl.mkorwel.app.manager.application.usecase.model.request.RejectApplicationModel;
import pl.mkorwel.app.manager.base.usecase.model.response.Result;

public class RejectApplicationUserCaseTest {

	@Test
	public void rejectWithSuccess() {
		
		Application application = new Application("testName", "testContent") {{	state = ApplicationState.ACCEPTED; }};
		ApplicationRepository repository = mock(ApplicationRepository.class);
		when(repository.findOne(anyLong())).thenReturn(application);
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
		String reason = "reason";
		
		Result<Void> result = new RejectApplication(repository, historyRepository).execute(new RejectApplicationModel(1L, reason));

		assertThat(application.getState()).isEqualTo(ApplicationState.REJECTED);
		assertThat(application.getCancelReason()).isEqualTo(reason);
		assertThat(result).isEqualTo(Result.EMPTY_RESULT);
	}
	
	@Test
	public void rejectWithError() {
		Application application = new Application("testName", "testContent") {{	state = ApplicationState.CREATED; }};
		ApplicationRepository repository = mock(ApplicationRepository.class);
		when(repository.findOne(anyLong())).thenReturn(application);
		ApplicationHistoryRepository historyRepository = mock(ApplicationHistoryRepository.class);
		
		Result<Void> result = new RejectApplication(repository, historyRepository).execute(new RejectApplicationModel(1L, "reason"));
		
		assertThat(application.getState()).isEqualTo(ApplicationState.CREATED);
		assertThat(result.isError()).isEqualTo(true);
		assertThat(result.getErrors()).containsOnly(ApplicationErrorCode.WRONG_STATE);
	}
}
