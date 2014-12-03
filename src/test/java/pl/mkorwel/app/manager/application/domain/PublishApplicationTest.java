package pl.mkorwel.app.manager.application.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Assert;
import org.junit.Test;

import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;

public class PublishApplicationTest {

	private Application createdApplication = new Application("testName", "testContent");

	private Application verifiedApplication = new Application("testName", "testContent"){{state = ApplicationState.VERIFIED;}};

	private Application acceptedApplication = new Application("testName", "testContent"){{state = ApplicationState.ACCEPTED;}};

	private Application publishedApplication = new Application("testName", "testContent"){{state = ApplicationState.PUBLISHED;}};

	private Application deletedApplication = new Application("testName", "testContent"){{state = ApplicationState.DELETED;}};

	private Application rejectedApplication = new Application("testName", "testContent"){{state = ApplicationState.REJECTED;}};

	@Test
	public void acceptedApplicationPublishWithSuccess(){
		acceptedApplication.publish();
		assertThat(acceptedApplication.getState()).isEqualTo(ApplicationState.PUBLISHED);
	}
	
	@Test
	public void verifiedApplicationPublishThrowException(){
		try {
			verifiedApplication.publish();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void publishedApplicationPublishThrowException(){
		try {
			publishedApplication.publish();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void deletedApplicationPublishThrowException(){
		try {
			deletedApplication.publish();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void rejectedApplicationPublishThrowException(){
		try {
			rejectedApplication.publish();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void createdApplicationPublishThrowException(){
		try {
			createdApplication.publish();
			fail("WrongStateApplicationException excepted becouse state is invalid");
		} catch (WrongStateApplicationException e) {
			Assert.assertTrue(true);
		}
	}
}
