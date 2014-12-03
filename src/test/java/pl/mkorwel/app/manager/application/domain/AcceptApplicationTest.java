package pl.mkorwel.app.manager.application.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Assert;
import org.junit.Test;

import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;

public class AcceptApplicationTest {

	private Application createdApplication = new Application("testName", "testContent");

	private Application verifiedApplication = new Application("testName", "testContent"){{state = ApplicationState.VERIFIED;}};

	private Application acceptedApplication = new Application("testName", "testContent"){{state = ApplicationState.ACCEPTED;}};

	private Application publishedApplication = new Application("testName", "testContent"){{state = ApplicationState.PUBLISHED;}};

	private Application deletedApplication = new Application("testName", "testContent"){{state = ApplicationState.DELETED;}};

	private Application rejectedApplication = new Application("testName", "testContent"){{state = ApplicationState.REJECTED;}};

	@Test
	public void verifiedApplicationAcceptWithSuccess(){
		verifiedApplication.accept();
		assertThat(verifiedApplication.getState()).isEqualTo(ApplicationState.ACCEPTED);
	}
	
	@Test
	public void createdApplicationAcceptThrowException(){
		try {
			createdApplication.accept();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void acceptedApplicationAcceptThrowException(){
		try {
			acceptedApplication.accept();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void publishedApplicationAcceptThrowException(){
		try {
			publishedApplication.accept();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void deletedApplicationAcceptThrowException(){
		try {
			deletedApplication.accept();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void rejectedApplicationAcceptThrowException(){
		try {
			rejectedApplication.accept();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
}
