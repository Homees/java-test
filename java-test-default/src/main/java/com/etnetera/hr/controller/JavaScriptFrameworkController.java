package com.etnetera.hr.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;

/**
 * Simple REST controller for accessing application logic.
 * 
 * @author Etnetera
 *
 */
@RestController
public class JavaScriptFrameworkController {

	@Autowired
	private JavaScriptFrameworkRepository repository;

//	@Autowired
//	public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository) {
//		this.repository = repository;
//	}

	@GetMapping("/frameworks")
	public Iterable<JavaScriptFramework> frameworks() {
		return repository.findAll();
	}

	@PostMapping("/frameworks/save")
	public JavaScriptFramework saveFramework(JavaScriptFramework framework) {
		return repository.save(framework);
	}

	@PostMapping("/frameworks/edit")
	public JavaScriptFramework editFramework(JavaScriptFramework framework) {
		Optional<JavaScriptFramework> response = repository.findById(framework.getId());

		if (!response.isEmpty()) {
			return repository.save(framework);
		} else {
			return null;
		}
	}

	@DeleteMapping("/frameworks/delete")
	public void deleteFrameworkById(Long id) {
		repository.deleteById(id);
	}
}
