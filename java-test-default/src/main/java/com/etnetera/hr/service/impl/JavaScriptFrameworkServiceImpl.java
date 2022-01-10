package com.etnetera.hr.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.service.JavaScriptFrameworkService;

@Service
public class JavaScriptFrameworkServiceImpl implements JavaScriptFrameworkService {

	@Autowired
	private JavaScriptFrameworkRepository repository;

	@Override
	public Iterable<JavaScriptFramework> getFrameworks() throws DataFormatException {
		return repository.findAll();
	}
	
	@Override
	public JavaScriptFramework getFrameworkById(Long id) throws DataFormatException {
		Optional<JavaScriptFramework> result = repository.findById(id);
		
		if (result.isEmpty()) {
			throw new DataFormatException("Searched framework was not found.");
		}
		
		return result.get();
	}

	@Override
	public JavaScriptFramework saveFramework(JavaScriptFramework framework) throws DataFormatException {
		if (framework.getId() != null) {
			Optional<JavaScriptFramework> oldFrameworkOpt = repository.findById(framework.getId());

			if (oldFrameworkOpt.isEmpty()) {
				throw new DataFormatException("Framework for update was not found.");
			}
			
			JavaScriptFramework oldFramework = oldFrameworkOpt.get();
			oldFramework.getVersion().stream().forEach(item -> {
				if (!framework.getVersion().contains(item)) {
					framework.getVersion().add(item);
				}
			});
		} else {
			List<JavaScriptFramework> existingFrameworks = repository.findByName(framework.getName());
			existingFrameworks.forEach(item -> {
				framework.setId(item.getId());
				framework.getVersion().addAll(item.getVersion());
			});
		}
		
		JavaScriptFramework result = repository.save(framework);
		return result;
	}

	@Override
	public Long deleteFrameworkById(Long id) throws DataFormatException {
		Optional<JavaScriptFramework> entity = repository.findById(id);

		if (entity.isEmpty()) {
			throw new DataFormatException("Framework for deletion was not found.");
		}

		repository.delete(entity.get());
		return id;
	}

}
