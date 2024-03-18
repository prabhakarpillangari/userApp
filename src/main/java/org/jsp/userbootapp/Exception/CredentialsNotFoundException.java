package org.jsp.userbootapp.Exception;

public class CredentialsNotFoundException  extends RuntimeException{

	@Override
	public String getMessage() {
		return "Email or password or phone are not found";
	}
}
