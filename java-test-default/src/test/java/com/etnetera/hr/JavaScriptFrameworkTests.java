package com.etnetera.hr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.etnetera.hr.data.HypeLevel;
import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;

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
	private JavaScriptFrameworkRepository repository;
	
	@Test
	public void getAllFrameworksTest() {
		List<JavaScriptFramework> result = new ArrayList<>();
		Iterable<JavaScriptFramework> response = repository.findAll();
		response.forEach(item -> { result.add(item); });
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.get(0).getId(), Long.valueOf(1));
		Assert.assertEquals(result.get(0).getName(), "React");
		Assert.assertEquals(result.get(0).getVersion(), "1.2.3");
		Assert.assertEquals(result.get(0).getDeprecationDate(), LocalDate.of(2020, 12, 31));
		Assert.assertEquals(result.get(0).getHypeLevel(), HypeLevel.LOW);
	}

	@Test
	public void saveNewFrameworkTest() {
		var framework = new JavaScriptFramework("Angular JS", "2.3.3", LocalDate.of(2022, 12, 31), HypeLevel.HIGH);
		JavaScriptFramework result = repository.save(framework);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getId(), Long.valueOf(5));
	}
	
	@Test
	public void editFrameworkTest() {
		var framework = new JavaScriptFramework("Angular JS framework", "2.3.3", LocalDate.of(2022, 12, 31), HypeLevel.HIGH);
		framework.setId(4L);
		JavaScriptFramework result = repository.save(framework);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result.getName(), "Angular JS framework");
	}
	
	@Test
	public void deleteFrameworkTest() {
		repository.deleteById(4L);
		Optional<JavaScriptFramework> result = repository.findById(4L);
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isEmpty());
	}
}
