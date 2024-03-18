package org.jsp.userbootapp.Exception;

import org.jsp.userbootapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserBootExcpetionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>>  handleINFE(IdNotFoundException e)
	{
		
		ResponseStructure<String> structure= new ResponseStructure<>();
		structure.setData(e.getMessage());
		structure.setMessage("user not found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new  ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND) ;
	}
	
	@ExceptionHandler(CredentialsNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleCNFE(CredentialsNotFoundException e)
	{
		ResponseStructure<String> structure= new ResponseStructure<>();
		structure.setData(e.getMessage());
		structure.setMessage("email or password or phone are incorrect");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new  ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND) ;
	}
}
