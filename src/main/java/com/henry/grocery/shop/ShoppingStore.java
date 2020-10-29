package com.henry.grocery.shop;

import java.io.FileNotFoundException;
import java.util.Date;

import com.henry.grocery.model.Product;
import com.henry.grocery.service.ProductCatalog;
import com.henry.grocery.service.PromotionCatalog;
import com.henry.grocery.service.ShoppingCart;

public class ShoppingStore {

	private ProductCatalog catalog;
	private PromotionCatalog promoCatalog;
	private ShoppingCart shoppingCart;


	public void setDate(Date date) {
		shoppingCart.setDate(date);
	}

	public void setPromoCatalog(PromotionCatalog promoCatalog) {
		this.promoCatalog = promoCatalog;
	}

	
	
	public ShoppingStore() {
		shoppingCart = new ShoppingCart();
		catalog = new ProductCatalog();
		promoCatalog = new PromotionCatalog();
	}
	
	public ProductCatalog getCatalog() {
		return catalog;
	}
	
		public PromotionCatalog getPromoCatalog() {
		return promoCatalog;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
	/**
	 * Checks the existence of Product p in the shopping cart
	 * @param p	 the product
	 * @return True if Product p is in the cart, False otherwise.
	 */
	public boolean existsInShoppingCart(Product p) {
		return shoppingCart.getContent().containsKey(p);
	}
	
	/**
	 * loads the product catalog from a local file
	 * @throws FileNotFoundException in case the file is missing
	 */
    public void loadProducts() {
    	try {
			catalog.loadProducts();
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
			System.exit(0);
		}
    }

		/**
	 * loads the promotion catalog from a local file
	 * @throws FileNotFoundException in case the file is missing
	 */
    public void loadPromotions() {
    	try {
			promoCatalog.loadPromotions();
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
			System.exit(0);
		}
    }
    /**
     * List the products of the catalog
     */
    public void listProducts() {
    	System.out.println("List of products");
        Long[] productIdArray = catalog.getProducts().keySet().toArray(new Long[catalog.getProducts().size()]);
        for (Long productId: productIdArray) {
        	System.out.println(catalog.getProducts().get(productId));
        }
    }

	    /**
     * List the promotions of the catalog
     */
    public void listPromotions() {
    	System.out.println("List of promotions");
        Long[] promotionIdArray = promoCatalog.getPromotions().keySet().toArray(new Long[promoCatalog.getPromotions().size()]);
        for (Long promotionId: promotionIdArray) {
        	System.out.println("Raavi::"+promoCatalog.getPromotions().get(promotionId));
        	
        }
    }
	
    /**
     * Add a product to the Basket
     * @param productId The id of the product
     * @throws IllegalArgumentException in case the product id is not in the product catalog
     */
    public void addProductToBasket(int productId) throws IllegalStateException {
        if (catalog.existsInProductCatalogue(productId)) {        	
        	Product p = catalog.getProduct(productId);
        	if (!shoppingCart.getContent().containsKey(p)) {
        		shoppingCart.addProduct(p, 1);       		
        	} else {
        		int quantity = shoppingCart.getContent().get(p);
        		shoppingCart.addProduct(p, quantity + 1);
        	}
        	if (promoCatalog.existsInPromotionCatalogue(productId))
        	{
        		shoppingCart.setPromoCatalog(promoCatalog);
        	}
        } else { 
        	throw new IllegalArgumentException("Product with id: " + productId + " is not in catalog. It cannot be added to the basket. Use List command to list the valid products.");
        }
        /*
        if (promoCatalog.existsInPromotionCatalogue(productId)){
            Promotion pr=promoCatalog.getPromotion(productId);
        	 System.out.println("Promotion is available for this product::"+pr.getName()+" Start Date:"+pr.getStartDate()+"End Date"+pr.getEndDate()+"Discount:"+pr.getPrice());
        	 shoppingCart.addPromotion(pr, quantity); 
        }
        
        */
        		
        Product p = catalog.getProduct(productId);
        
        System.out.println("The quantity of product with id: " + productId + " is: " + shoppingCart.getContent().get(p));
    }

    /**
     * Remove a product from the Basket
     * @param productId The id of the product
     * @throws IllegalArgumentException in case the product id is not in the product catalog
     */
    public void removeProductFromBasket(int productId) throws IllegalStateException {
        if (catalog.existsInProductCatalogue(productId)) {
        	Product p = catalog.getProduct(productId);
        	if (shoppingCart.getContent().containsKey(p)) {
        		int quantity = shoppingCart.getContent().get(p);
        		if (quantity > 1) {
        			shoppingCart.getContent().put(p, quantity - 1);
        		} else { // quantity == 1 
        			shoppingCart.getContent().remove(p);
        		}
        	} else {
        		throw new IllegalArgumentException("Product with id: " + productId + " is not in the shopping cart. It cannot be removed.");
        	}
        } else {
            throw new IllegalArgumentException("Product with id: " + productId + " is not in catalog. It cannot be removed from the basket. Use List command to list the valid products.");
        }
        Product p = catalog.getProduct(productId);
        if (shoppingCart.getContent().get(p) != null) {
            System.out.println("The quantity of product with id: " + productId + " is: " + shoppingCart.getContent().get(p));
        } else {
            System.out.println("The quantity of product with id: " + productId + " has reached 0. The product was removed from the shopping cart.");
        }
    }

    /**
     * Print the total value of the products in the basket
     * Feature to show the total value of Products in the basket
     */
    public void getTotal() {
        System.out.println("Shopping cart total price: " + shoppingCart.getTotalPrice() + " GBP");
        System.out.println("Shopping cart content list: \n" + shoppingCart.toString());
    }

}
