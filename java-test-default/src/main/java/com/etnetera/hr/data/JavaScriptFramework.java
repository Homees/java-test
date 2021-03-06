package com.etnetera.hr.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */
@Entity
@Table(name = "framework")
public class JavaScriptFramework implements Serializable {

	private static final long serialVersionUID = -5986872752188022824L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> version = new HashSet<>();

	@Column(nullable = false, columnDefinition = "DATE")
	private LocalDate deprecationDate;

	@Enumerated(EnumType.STRING)
	private HypeLevel hypeLevel;

	public JavaScriptFramework() {
	}

	public JavaScriptFramework(String name, String version, LocalDate deprecationDate, HypeLevel hypeLevel) {
		this.name = name;
		this.version.add(version);
		this.deprecationDate = deprecationDate;
		this.hypeLevel = hypeLevel;
	}

	public JavaScriptFramework(Long id, String name, String version, LocalDate deprecationDate, HypeLevel hypeLevel) {
		this.id = id;
		this.name = name;
		this.version.add(version);
		this.deprecationDate = deprecationDate;
		this.hypeLevel = hypeLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getVersion() {
		return version;
	}

	public void setVersion(Set<String> version) {
		this.version = version;
	}

	public LocalDate getDeprecationDate() {
		return deprecationDate;
	}

	public void setDeprecationDate(LocalDate deprecationDate) {
		this.deprecationDate = deprecationDate;
	}

	public HypeLevel getHypeLevel() {
		return hypeLevel;
	}

	public void setHypeLevel(HypeLevel hypeLevel) {
		this.hypeLevel = hypeLevel;
	}

	@Override
	public String toString() {
		return "JavaScriptFramework [id=" + id + ", name=" + name + ", versions=" + version.toString()
				+ ", deprecationDate=" + deprecationDate + ", hypeLevel=" + hypeLevel.name() + "]";
	}

}
