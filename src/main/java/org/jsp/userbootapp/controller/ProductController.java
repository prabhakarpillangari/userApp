package org.jsp.userbootapp.controller;

import java.util.List;

import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.service.ProductService;
import org.jsp.userbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> saveUser(@RequestBody Product p, @PathVariable(name ="id") int  user_id) {
		return service.saveProduct(p,user_id);
	}

	@PutMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> updateUser(@RequestBody Product p) {
		return service.updateProduct(p);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable int id) {
		return service.findByid(id);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Boolean>> deleteById(@PathVariable int id) {
		return service.deleteById(id);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAll_products() {
		return service.findAll();
	}
	
	@GetMapping("/products/find-by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable(name = "brand") String brand) {
		return service.findByBrand(brand);
	}
	
	@GetMapping("/products/by-category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable String category) {
		return service.findByCategory(category);
	}

}
