package pl.mkorwel.app.manager.application.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Assert;
import org.junit.Test;

import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;

public class VerifyApplicationTest {

	private Application createdApplication = new Application("testName", "testContent");

	private Application verifiedApplication = new Application("testName", "testContent"){{state = ApplicationState.VERIFIED;}};

	private Application acceptedApplication = new Application("testName", "testContent"){{state = ApplicationState.ACCEPTED;}};

	private Application publishedApplication = new Application("testName", "testContent"){{state = ApplicationState.PUBLISHED;}};

	private Application deletedApplication = new Application("testName", "testContent"){{state = ApplicationState.DELETED;}};

	private Application rejectedApplication = new Application("testName", "testContent"){{state = ApplicationState.REJECTED;}};

	@Test
	public void createdApplicationVerifyWithSuccess(){
		createdApplication.verify();
		assertThat(createdApplication.getState()).isEqualTo(ApplicationState.VERIFIED);
	}
	
	@Test
	public void verifiedApplicationVerifyThrowException(){
		try {
			verifiedApplication.verify();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void publishedApplicationVerifyThrowException(){
		try {
			publishedApplication.verify();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void deletedApplicationVerifyThrowException(){
		try {
			deletedApplication.verify();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void rejectedApplicationVerifyThrowException(){
		try {
			rejectedApplication.verify();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void acceptedApplicationVerifyThrowException(){
		try {
			acceptedApplication.verify();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
}
