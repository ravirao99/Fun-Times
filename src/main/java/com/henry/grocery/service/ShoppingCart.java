package com.henry.grocery.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.henry.grocery.model.Product;
import com.henry.grocery.model.Promotion;
import com.henry.grocery.utils.DateUtils;

/**
 * The CartService class contains the content of the cart (the basket)
 */
public class ShoppingCart {

	private Map<Product, Integer> content;
	private Map<PromotionCatalog, Integer> promo;
	private float totalPrice;
	private Date date;
	private PromotionCatalog promoCatalog;

	public Map<PromotionCatalog, Integer> getPromo() {
		return promo;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPromo(Map<PromotionCatalog, Integer> promo) {
		this.promo = promo;
	}

	public PromotionCatalog getPromoCatalog() {
		return promoCatalog;
	}

	public void setPromoCatalog(PromotionCatalog promoCatalog) {
		this.promoCatalog = promoCatalog;
	}

	public ShoppingCart() {
		promoCatalog = new PromotionCatalog();
		content = new HashMap<Product, Integer>();
		promo = new HashMap<PromotionCatalog, Integer>();
		totalPrice = 0;
	}

	public Map<Product, Integer> getContent() {
		return content;
	}

	/**
	 * computes the total price value for the cart.
	 * 
	 * @return the amount of all the products found in the cart.
	 */
	public float getTotalPrice() {
		totalPrice = 0;
		for (Map.Entry<Product, Integer> entry : content.entrySet()) {
			Product p = entry.getKey();
			float productTotalPrice = computePriceForProductByQuantity(p);
			totalPrice += productTotalPrice;
		}
		return round(totalPrice, 2);
	}

	/**
	 * round a float
	 * 
	 * @param d
	 * @param decimalPlace
	 * @return the float number rounded by the decimalPlace number
	 */
	@SuppressWarnings("deprecation")
	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		if (content.size() > 0) {
			for (Map.Entry<Product, Integer> entry : content.entrySet()) {
				Product p = entry.getKey();
				float productTotalPrice = computePriceForProductByQuantity(p);
				sb.append("{\"product\":").append(p.toString()).append(",\"quantity\": ").append(entry.getValue())
						.append(",\"productTotalPrice\":").append(productTotalPrice).append("},\n");

			}
			// sb.deleteCharAt(sb.length() -1);//remove last ,
		} else {
			sb.append("[]");
		}
		return sb.toString();
	}

	/**
	 * compute the price of a product by its quantity
	 * 
	 * @param p the Product p
	 * @return the total price of a product by its quantity
	 */
	private float computePriceForProductByQuantity(Product p) {

		Promotion pr = promoCatalog.getPromotion(p.getId());
		if ((pr != null) && (DateUtils.isValidDate(pr.getStartDate(), pr.getEndDate(), date))) {

			float totalPrice = 0.0f;
			for (Map.Entry<Product, Integer> entry : content.entrySet()) {
				Product p1 = entry.getKey();
				if (p1.getId() == pr.getDiscountId()) {
					int qty = content.get(p1);
					int result = qty / pr.getEligibleCount();
					if (content.get(p) <= result) {
						totalPrice = content.get(p) * (pr.getPrice() * p.getPrice());
					} else {
						totalPrice = result * (pr.getPrice() * p.getPrice());
						totalPrice += (content.get(p) - result) * p.getPrice();

					}
					return totalPrice;
				} else {
					totalPrice = ((p.getPrice() * content.get(p) ) - (pr.getPrice() * content.get(p) ));
					return totalPrice;
				}
			}
		}
		return p.getPrice() * content.get(p);
	}

	/**
	 * Add the product to the basket with its quantity
	 * 
	 * @param p        the product
	 * @param quantity the quantity required of the product p
	 */
	public void addProduct(Product p, int quantity) {
		content.put(p, quantity);
	}

}
