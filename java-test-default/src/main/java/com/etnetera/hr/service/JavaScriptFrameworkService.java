package com.etnetera.hr.service;

import java.util.zip.DataFormatException;

import com.etnetera.hr.data.JavaScriptFramework;

public interface JavaScriptFrameworkService {

	public Iterable<JavaScriptFramework> getFrameworks() throws DataFormatException;
	
	public JavaScriptFramework getFrameworkById(Long id) throws DataFormatException;

	public JavaScriptFramework saveFramework(JavaScriptFramework framework) throws DataFormatException;

	public Long deleteFrameworkById(Long id) throws DataFormatException;
}
