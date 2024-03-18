package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.Exception.IdNotFoundException;
import org.jsp.userbootapp.dao.ProductDao;
import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product p, int user_id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findById(user_id);
		if (recUser.isPresent()) {
			User u = recUser.get();
			u.getProducts().add(p);
			userDao.saveUser(u);
			p.setUser(u);
			structure.setData(productDao.saveProduct(p));
			structure.setMessage("product saved :");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}

		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product p) {
		Optional<Product> recProduct = productDao.find_Product_ById(p.getId());
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {

			Product dbProduct = recProduct.get();
			dbProduct.setName(p.getName());
			dbProduct.setBrand(p.getCategory());
			dbProduct.setCost(p.getCost());
			dbProduct.setDescprition(p.getDescprition());
			structure.setData(productDao.saveProduct(dbProduct));
			structure.setMessage("product updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> findByid(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> dbUser = productDao.find_Product_ById(id);
		if (dbUser.isPresent()) {
			structure.setData(dbUser.get());
			structure.setMessage("got the data");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteById(int id) {
		Optional<Product> dbUser = productDao.find_Product_ById(id);
		ResponseStructure<Boolean> structure = new ResponseStructure<>();
		if (dbUser.isPresent()) {
			productDao.deleteById(id);
			structure.setData(true);
			structure.setMessage("Product  has been deleted");
			structure.setStatusCode(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Boolean>>(structure, HttpStatus.GONE);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(productDao.find_All_products());
		structure.setMessage("got all the products data from the Product Database");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
 		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(productDao.findByBrand(brand));
		structure.setMessage("List of all products for " + brand);
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(productDao.findByCategory(category));
		structure.setMessage("List of all products for " + category);
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
}
