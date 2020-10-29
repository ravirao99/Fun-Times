package com.henry.grocery.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.henry.grocery.model.Promotion;

/**
 * PromotionCatalogue manages the Promotions listed in the catalog.
 * The representation of the catalog is a dictionary defined by 
 * the Product id and Product as the key value pair.
 */
public class PromotionCatalog {

	Map<Long, Promotion> promotions;

	public PromotionCatalog() {
		promotions = new HashMap<Long, Promotion>();
	}
	
	public Map<Long, Promotion> getPromotions() {
		return promotions;
	}
	
	public boolean existsInPromotionCatalogue(long promotionId) {
		return promotions.containsKey(promotionId);
	}
	
	public int getSize() {
		return promotions.size();
	}
	
	/**
	 * Parsing the Promotion-data.csv data file
	 * @throws FileNotFoundException in case of a problem with the csv file
	 */
	public void loadPromotions() throws FileNotFoundException {
		promotions = PromotionCsvLoader.loadPromotions();
	}

	public Promotion getPromotion(long promotionId) {
		return promotions.get(promotionId);
	}
}
