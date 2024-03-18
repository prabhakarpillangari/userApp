package org.jsp.userbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product p) {
		return productRepository.save(p);
	}

	public Optional<Product> find_Product_ById(int id) {
		return productRepository.findById(id);
	}
	
	public boolean deleteById(int id) {
		Optional<Product> recUser = find_Product_ById(id);
		if(recUser.isPresent())
		{
			productRepository.delete(recUser.get());
			return true;
		}
		return false;
	}
	public List<Product> find_All_products()
	{
		
		return productRepository.findAll();
	}
	
	public List<Product> findByBrand(String brand) {
	    System.out.println("Searching for brand: " + brand);
	    return productRepository.findByBrand(brand);
	}

	public List<Product> findByCategory(String category) {
	    System.out.println("Searching for category: " + category);
	    return productRepository.findByCategory(category);
	}

	
	
}
