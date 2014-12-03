package pl.mkorwel.app.manager.application.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Assert;
import org.junit.Test;

import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;

public class RejectApplicationTest {

	private Application createdApplication = new Application("testName", "testContent");

	private Application verifiedApplication = new Application("testName", "testContent"){{state = ApplicationState.VERIFIED;}};

	private Application acceptedApplication = new Application("testName", "testContent"){{state = ApplicationState.ACCEPTED;}};

	private Application publishedApplication = new Application("testName", "testContent"){{state = ApplicationState.PUBLISHED;}};

	private Application rejectdApplication = new Application("testName", "testContent"){{state = ApplicationState.REJECTED;}};

	private Application rejectedApplication = new Application("testName", "testContent"){{state = ApplicationState.REJECTED;}};

	@Test
	public void verifiedApplicationWithSuccess(){
		String reason = "reason";
		verifiedApplication.reject(reason);
		assertThat(verifiedApplication.getState()).isEqualTo(ApplicationState.REJECTED);
		assertThat(verifiedApplication.getCancelReason()).isEqualTo(reason);
	}
	
	@Test
	public void acceptedApplicationWithSuccess(){
		String reason = "reason";
		acceptedApplication.reject(reason);
		assertThat(acceptedApplication.getState()).isEqualTo(ApplicationState.REJECTED);
		assertThat(acceptedApplication.getCancelReason()).isEqualTo(reason);
	}
	
	@Test
	public void verifiedApplicationWithoutReasonThrowException(){
		try {
			verifiedApplication.reject(null);
			fail("NullPointerException excepted becouse 'reason' is null");
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void acceptedApplicationWithoutReasonThrowException(){
		try {
			acceptedApplication.reject(null);
			fail("NullPointerException excepted becouse 'reason' is null");
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void createdApplicationRejectThrowException(){
		try {
			createdApplication.reject("reason");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void publishedApplicationRejectThrowException(){
		try {
			publishedApplication.reject("reason");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void rejectdApplicationRejectThrowException(){
		try {
			rejectdApplication.reject("reason");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void rejectedApplicationRejectThrowException(){
		try {
			rejectedApplication.reject("reason");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
}
