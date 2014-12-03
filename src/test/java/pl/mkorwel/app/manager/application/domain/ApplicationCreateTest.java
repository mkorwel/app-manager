package pl.mkorwel.app.manager.application.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Assert;
import org.junit.Test;

public class ApplicationCreateTest {

	@Test
	public void createApplicationWithSuccess(){
		String testName = "testName";
		String testContent = "testContent";
		Application application = new Application(testName, testContent);
		assertThat(application.getName()).isEqualTo(testName);
		assertThat(application.getContent()).isEqualTo(testContent);
		assertThat(application.getState()).isEqualTo(ApplicationState.CREATED);
	}
	
	@Test
	public void createApplicationWithoutNameThrowError(){
		String testContent = "testContent";
		try {
			new Application(null, testContent);
			fail("NullPointerException excepted becouse 'name' is null");
		} catch (NullPointerException e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void createApplicationWithoutContentThrowError(){
		String testName = "testName";
		try {
			new Application(testName, null);
			fail("NullPointerException excepted becouse 'content' is null");
		} catch (NullPointerException e){
			Assert.assertTrue(true);
		}
	}
	
}
