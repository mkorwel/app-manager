package pl.mkorwel.app.manager.application.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Assert;
import org.junit.Test;

import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;

public class ApplicationUpdateTest {

	private Application createdApplication = new Application("testName", "testContent");

	private Application verifiedApplication = new Application("testName", "testContent"){{state = ApplicationState.VERIFIED;}};

	private Application acceptedApplication = new Application("testName", "testContent"){{state = ApplicationState.ACCEPTED;}};

	private Application publishedApplication = new Application("testName", "testContent"){{state = ApplicationState.PUBLISHED;}};

	private Application deletedApplication = new Application("testName", "testContent"){{state = ApplicationState.DELETED;}};

	private Application rejectedApplication = new Application("testName", "testContent"){{state = ApplicationState.REJECTED;}};

	@Test
	public void changeCreatedApplicationNameWithSuccess() {
		changeName(createdApplication);
	}

	@Test
	public void changeCreatedApplicationContentWithSuccess() {
		changeContent(createdApplication);
	}

	@Test
	public void changeCreatedApplicationNameForNullThrowException() {
		changeNameWithNullPonter(createdApplication);
	}

	@Test
	public void changeCreatedApplicationContentForNullThrowException() {
		changeContentWithNullPointer(createdApplication);
	}
	
	@Test
	public void changeVerifiedApplicationNameWithSuccess() {
		changeName(verifiedApplication);
	}

	@Test
	public void changeVerifiedApplicationContentWithSuccess() {
		changeContent(verifiedApplication);
	}

	@Test
	public void changeVerifiedApplicationNameForNullThrowException() {
		changeNameWithNullPonter(verifiedApplication);
	}

	@Test
	public void changeVerifiedApplicationContentForNullThrowException() {
		changeContentWithNullPointer(verifiedApplication);
	}
	
	@Test
	public void changeAcceptedApplicationNameThrowException() {
		changeNameWithWrongStateApplicationException(acceptedApplication);
	}

	@Test
	public void changeAcceptedApplicationContentThrowException() {
		changeContentWithWrongStateApplicationException(acceptedApplication);
	}
	
	@Test
	public void changePublishedApplicationNameThrowException() {
		changeNameWithWrongStateApplicationException(publishedApplication);
	}

	@Test
	public void changePublishedApplicationContentThrowException() {
		changeContentWithWrongStateApplicationException(publishedApplication);
	}
	
	@Test
	public void changeDeletedApplicationNameThrowException() {
		changeNameWithWrongStateApplicationException(deletedApplication);
	}

	@Test
	public void changeRejectedApplicationContentThrowException() {
		changeContentWithWrongStateApplicationException(rejectedApplication);
	}
	
	@Test
	public void changeRejectedApplicationNameThrowException() {
		changeNameWithWrongStateApplicationException(rejectedApplication);
	}

	@Test
	public void changeDeletedApplicationContentThrowException() {
		changeContentWithWrongStateApplicationException(deletedApplication);
	}
	
	private void changeName(Application application) {
		String otherName = "otherName";
		application.changeName(otherName);
		assertThat(application.getName()).isEqualTo(otherName);
	}
	
	private void changeContent(Application application) {
		String otherContent = "otherContent";
		application.changeContent(otherContent);
		assertThat(application.getContent()).isEqualTo(otherContent);
	}

	private void changeNameWithNullPonter(Application application) {
		try {
			application.changeName(null);
			fail("NullPointerException excepted becouse 'name' is null");
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
	}
	
	private void changeContentWithNullPointer(Application application) {
		try {
			application.changeContent(null);
			fail("NullPointerException excepted becouse 'content' is null");
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
	}
	
	private void changeNameWithWrongStateApplicationException(Application application) {
		try {
			application.changeName("");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	private void changeContentWithWrongStateApplicationException(Application application) {
		try {
			application.changeContent("");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
}
