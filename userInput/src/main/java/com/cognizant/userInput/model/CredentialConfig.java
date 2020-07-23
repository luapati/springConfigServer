package com.cognizant.userInput.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan("com.cognizant.userInputCrud.properties")
public class CredentialConfig {
	private String cruduser;
	private String crudpwd;
	public String getCruduser() {
		return cruduser;
	}
	public void setCruduser(@Value("${user}")String cruduser) {
		this.cruduser = cruduser;
	}
	public String getCrudpwd() {
		return crudpwd;
	}
	public void setCrudpwd(@Value("${pwd}") String crudpwd) {
		this.crudpwd = crudpwd;
	}
	@Override
	public String toString() {
		return "CredentialConfig [cruduser=" + cruduser + ", crudpwd=" + crudpwd + "]";
	}
	

}
