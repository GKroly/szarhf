package com.szar.szarhf_umlweb.shared;

import java.io.Serializable;

public abstract class Model implements Serializable {

	String modelName = null;

	public Model() {

	}

	public Model(String modelName) {
		this.modelName = modelName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}