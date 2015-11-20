package com.szar.szarhf_umlweb.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {

	private String projectName = null;
	private List<Model> models = null;

	public Project() {

	}

	public Project(String projectName) {
		this.projectName = projectName;
		models = new ArrayList<Model>();
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void addModel(Model m) {
		models.add(m);
	}

	public Model getModel(int i) {
		return models.get(i);
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

}
