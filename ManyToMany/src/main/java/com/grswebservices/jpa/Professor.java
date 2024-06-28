package com.grswebservices.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int professorId;
	
	@Column(name="name")
	private String professorName;
	
	@ManyToMany(mappedBy = "professors", fetch = FetchType.LAZY)
	private List<ResearchProject> projects;
	
	public Professor() {
		this.projects = new ArrayList<>();
	}
	
	public Professor(String professorName) {
		this();
		this.professorName = professorName;
	}
	
	public void assignProjectToProfessor(ResearchProject project) {
		this.projects.add(project);
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public List<ResearchProject> getProjects() {
		return projects;
	}

	public void setProjects(List<ResearchProject> projects) {
		this.projects = projects;
	}
	
	
}
