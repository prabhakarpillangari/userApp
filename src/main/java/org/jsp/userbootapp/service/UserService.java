package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.CredentialNotFoundException;

import org.jsp.userbootapp.Exception.CredentialsNotFoundException;
import org.jsp.userbootapp.Exception.IdNotFoundException;
import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User u) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(userDao.saveUser(u));
		structure.setMessage("user saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User u) {
		Optional<User> recUser = userDao.findById(u.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setName(u.getName());
			dbUser.setEmail(u.getEmail());
			dbUser.setPhone(u.getPhone());
			dbUser.setPassword(u.getPassword());
			structure.setData(userDao.saveUser(dbUser));
			structure.setMessage("user updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> findByid(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.findById(id);
		if (dbUser.isPresent()) {
			structure.setData(dbUser.get());
			structure.setMessage("got the data");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteById(int id) {
		Optional<User> dbUser = userDao.findById(id);
		ResponseStructure<Boolean> structure = new ResponseStructure<>();
		if (dbUser.isPresent()) {
			userDao.deleteById(id);
			structure.setData(true);
			structure.setMessage("user  has been deleted");
			structure.setStatusCode(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Boolean>>(structure, HttpStatus.GONE);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setData(userDao.findAll());
		structure.setMessage("got all the users data from the User Database");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser( long phone, String password) {
		Optional<User> recUser = userDao.verifyUser(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("the entered credentinals are valid");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new CredentialsNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser( String email, String password) {
		Optional<User> recUser = userDao.verifyUser(email, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("the entered credentinals are valid");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new CredentialsNotFoundException();
	}

}
