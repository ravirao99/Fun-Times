package com.henry.grocery.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.henry.grocery.model.Product;

/**
 * ProductCatalogue manages the products listed in the catalog. The
 * representation of the catalog is a dictionary defined by the Product id and
 * Product as the key value pair.
 */
public class ProductCatalog {

	Map<Long, Product> products;

	public ProductCatalog() {
		products = new HashMap<Long, Product>();
	}

	public Map<Long, Product> getProducts() {
		return products;
	}

	public boolean existsInProductCatalogue(long productId) {
		return products.containsKey(productId);
	}

	public int getSize() {
		return products.size();
	}

	/**
	 * Parsing the product-data.csv data file
	 * 
	 * @throws FileNotFoundException in case of a problem with the csv file
	 */
	public void loadProducts() throws FileNotFoundException {
		products = ProductCsvLoader.loadProducts();
	}

	public Product getProduct(long productId) {
		return products.get(productId);
	}
}
