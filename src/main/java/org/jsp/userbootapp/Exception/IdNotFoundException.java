package org.jsp.userbootapp.Exception;

public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Id";
	}

}
