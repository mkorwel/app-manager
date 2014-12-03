package pl.mkorwel.app.manager.application.usecase;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import pl.mkorwel.app.manager.application.domain.Application;
import pl.mkorwel.app.manager.application.domain.ApplicationState;
import pl.mkorwel.app.manager.application.domain.error.ApplicationErrorCode;
import pl.mkorwel.app.manager.application.repository.ApplicationRepository;
import pl.mkorwel.app.manager.base.usecase.model.response.Result;

public class VerifyApplicationUserCaseTest {

	@Test
	public void verifyWithSuccess() {
		
		Application application = new Application("testName", "testContent") {{	state = ApplicationState.CREATED; }};
		ApplicationRepository repository = mock(ApplicationRepository.class);
		when(repository.findOne(anyLong())).thenReturn(application);
		
		Result<Void> result = new VerifyApplication(repository).execute(1L);

		assertThat(application.getState()).isEqualTo(ApplicationState.VERIFIED);
		assertThat(result).isEqualTo(Result.EMPTY_RESULT);
	}
	
	@Test
	public void verifyWithError() {
		Application application = new Application("testName", "testContent") {{	state = ApplicationState.DELETED; }};
		ApplicationRepository repository = mock(ApplicationRepository.class);
		when(repository.findOne(anyLong())).thenReturn(application);
		
		Result<Void> result = new VerifyApplication(repository).execute(1L);
		
		assertThat(application.getState()).isEqualTo(ApplicationState.DELETED);
		assertThat(result.isError()).isEqualTo(true);
		assertThat(result.getErrors()).containsOnly(ApplicationErrorCode.WRONG_STATE);
	}
}
