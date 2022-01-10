package com.etnetera.hr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.etnetera.hr.data.HypeLevel;
import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.service.JavaScriptFrameworkService;

/**
 * Class used for Spring Boot/MVC based tests.
 * 
 * @author Etnetera
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkTests {

	@Autowired
	private JavaScriptFrameworkService service;

	@Test
	public void getAllFrameworksTest() throws DataFormatException {
		List<JavaScriptFramework> result = new ArrayList<>();
		Iterable<JavaScriptFramework> response = service.getFrameworks();
		response.forEach(item -> {
			result.add(item);
		});

		Assert.assertNotNull(result);
		Assert.assertEquals(result.get(0).getId(), Long.valueOf(1));
		Assert.assertEquals(result.get(0).getName(), "React");
		Assert.assertTrue(result.get(0).getVersion().contains("1.2.3"));
		Assert.assertEquals(result.get(0).getDeprecationDate(), LocalDate.of(2020, 12, 31));
		Assert.assertEquals(result.get(0).getHypeLevel(), HypeLevel.LOW);
	}

	@Test
	public void getAllFrameworkByIdTest() throws DataFormatException {
		JavaScriptFramework response = service.getFrameworkById(1L);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getId(), Long.valueOf(1));
		Assert.assertEquals(response.getName(), "React");
		Assert.assertTrue(response.getVersion().contains("1.2.3"));
		Assert.assertEquals(response.getDeprecationDate(), LocalDate.of(2020, 12, 31));
		Assert.assertEquals(response.getHypeLevel(), HypeLevel.LOW);
	}

	@Test
	public void saveNewFrameworkTest() throws DataFormatException {
		var framework = new JavaScriptFramework("React", "2.7.1", LocalDate.of(2022, 12, 31), HypeLevel.HIGH);
		JavaScriptFramework result = service.saveFramework(framework);

		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), Long.valueOf(1));
		Assert.assertEquals(result.getName(), "React");
		Assert.assertEquals(result.getDeprecationDate(), LocalDate.of(2022, 12, 31));
		Assert.assertTrue(result.getVersion().contains("2.7.1"));
		Assert.assertEquals(result.getHypeLevel(), HypeLevel.HIGH);
	}

	@Test
	public void editFrameworkTest() throws DataFormatException {
		var framework = new JavaScriptFramework(2L, "Angular JS framework", "2.3.3", LocalDate.of(2022, 12, 31), HypeLevel.HIGH);
		JavaScriptFramework result = service.saveFramework(framework);

		Assert.assertNotNull(result);
		Assert.assertEquals(result.getName(), "Angular JS framework");
		Assert.assertEquals(result.getId(), Long.valueOf(2));
		Assert.assertEquals(result.getDeprecationDate(), LocalDate.of(2022, 12, 31));
		Assert.assertTrue(result.getVersion().contains("2.3.3"));
		Assert.assertEquals(result.getHypeLevel(), HypeLevel.HIGH);
	}

	@Test
	public void deleteFrameworkTest() throws DataFormatException {
		Long result = service.deleteFrameworkById(3L);

		Assert.assertNotNull(result);
		Assert.assertEquals(result, Long.valueOf(3));
	}
}
