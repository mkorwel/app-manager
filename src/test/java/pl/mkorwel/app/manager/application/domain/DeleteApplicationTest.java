package pl.mkorwel.app.manager.application.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Assert;
import org.junit.Test;

import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;

public class DeleteApplicationTest {

	private Application createdApplication = new Application("testName", "testContent");

	private Application verifiedApplication = new Application("testName", "testContent"){{state = ApplicationState.VERIFIED;}};

	private Application acceptedApplication = new Application("testName", "testContent"){{state = ApplicationState.ACCEPTED;}};

	private Application publishedApplication = new Application("testName", "testContent"){{state = ApplicationState.PUBLISHED;}};

	private Application deletedApplication = new Application("testName", "testContent"){{state = ApplicationState.DELETED;}};

	private Application rejectedApplication = new Application("testName", "testContent"){{state = ApplicationState.REJECTED;}};

	@Test
	public void createdApplicationDeleteWithSuccess(){
		String reason = "reason";
		createdApplication.delete(reason);
		assertThat(createdApplication.getState()).isEqualTo(ApplicationState.DELETED);
		assertThat(createdApplication.getCancelReason()).isEqualTo(reason);
	}
	
	@Test
	public void createdApplicationDeleteWithoutReasonThrowException(){
		try {
			createdApplication.delete(null);
			fail("NullPointerException excepted becouse 'reason' is null");
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void verifiedApplicationDeleteThrowException(){
		try {
			verifiedApplication.delete("reason");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void publishedApplicationDeleteThrowException(){
		try {
			publishedApplication.delete("reason");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void deletedApplicationDeleteThrowException(){
		try {
			deletedApplication.delete("reason");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void rejectedApplicationDeleteThrowException(){
		try {
			rejectedApplication.delete("reason");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void acceptedApplicationDeleteThrowException(){
		try {
			acceptedApplication.delete("reason");
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
}
