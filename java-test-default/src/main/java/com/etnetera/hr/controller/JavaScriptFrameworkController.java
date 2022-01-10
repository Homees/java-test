package com.etnetera.hr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.service.JavaScriptFrameworkService;

/**
 * Simple REST controller for accessing application logic.
 * 
 * @author Etnetera
 *
 */
@RestController
public class JavaScriptFrameworkController {
	
	private static final Logger logger = LoggerFactory.getLogger(JavaScriptFrameworkController.class);
	
	@Autowired
	private JavaScriptFrameworkService service;

	@GetMapping("/frameworks")
	public Iterable<JavaScriptFramework> getFrameworks() {
		try {
			Iterable<JavaScriptFramework> response = service.getFrameworks();
			return response;
		} catch (Exception e) {
			logger.error("Could not find any frameworks.");
			throw new RuntimeException("Could not find any frameworks.");
		}
		
	}
	
	@GetMapping("/frameworks/get")
	public JavaScriptFramework getFrameworkById(@RequestParam("id") Long id) {
		try {
			JavaScriptFramework response = service.getFrameworkById(id);
			return response;
		} catch (Exception e) {
			logger.error("Could not find searched framework.");
			throw new RuntimeException("Could not find searched framework.");
		}
		
	}

	@PostMapping("/frameworks/save")
	public JavaScriptFramework saveFramework(JavaScriptFramework framework) {
		try {
			JavaScriptFramework response = service.saveFramework(framework);
			return response;
		} catch (Exception e) {
			logger.error("Could not save given framework.");
			throw new RuntimeException("Could not save given framework.");
		}
	}


	@DeleteMapping("/frameworks/delete")
	public Long deleteFrameworkById(Long id) {
		try {
			Long response = service.deleteFrameworkById(id);
			return response;
		} catch (Exception e) {
			logger.error("Could not delete given framework.");
			throw new RuntimeException("Could not delete given framework.");
		}
	}
}
