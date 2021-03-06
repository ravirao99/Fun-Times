/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.henry.grocery.unit;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.henry.grocery.shop.ShoppingStore;
import com.henry.grocery.utils.DateUtils;

public class LibraryTest {
	/*
	 * @Test public void testSomeLibraryMethod() { Library classUnderTest = new
	 * Library(); assertTrue("someLibraryMethod should return 'true'",
	 * classUnderTest.someLibraryMethod()); }
	 */

	/*
	 * Price a basket containing: 3 tins of soup and 2 loaves of bread, bought
	 * today, Expected total cost = 3.15;
	 */
	@Test
	public void test1() {
		
		ShoppingStore shop = new ShoppingStore();
		shop.setDate(DateUtils.getToday(0));
		shop.loadProducts();
		shop.loadPromotions();
		System.out.println("*************Test1 Start****************");
		shop.addProductToBasket(1);
		shop.addProductToBasket(1);
		shop.addProductToBasket(1);
		shop.addProductToBasket(2);
		shop.addProductToBasket(2);

		assertTrue(3.15f == shop.getShoppingCart().getTotalPrice());
		System.out.println("TotalPrice::"+shop.getShoppingCart().getTotalPrice());
		System.out.println("*************Test1 End******************");
	}

	/*
	 * Price a basket containing: 6 apples and a bottle of milk, bought today,
	 * Expected total cost = 1.90;
	 */
	@Test
	public void test2() {

		ShoppingStore shop = new ShoppingStore();
		shop.setDate(DateUtils.getToday(0));
		shop.loadProducts();
		shop.loadPromotions();
		System.out.println("*************Test2 Start****************");
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(3);

		assertTrue(1.90f == shop.getShoppingCart().getTotalPrice());
		System.out.println("TotalPrice::"+shop.getShoppingCart().getTotalPrice());
		System.out.println("*************Test2 End******************");
	}

	/*
	 * Price a basket containing: 6 apples and a bottle of milk, bought in 5 days
	 * time, Expected total cost = 1.84;
	 */
	@Test
	public void test3() {

		ShoppingStore shop = new ShoppingStore();
		shop.setDate(DateUtils.getToday(5));
		shop.loadProducts();
		shop.loadPromotions();
		System.out.println("*************Test3 Start****************");
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(3);

		assertTrue(1.84f == shop.getShoppingCart().getTotalPrice());
		System.out.println("TotalPrice::"+shop.getShoppingCart().getTotalPrice());
		System.out.println("*************Test3 End******************");
	}

	/*
	 * Price a basket containing: 3 apples, 2 tins of soup and a loaf of bread,
	 * bought in 5 days time, Expected total cost = 1.97.
	 */
	@Test
	public void test4() {

		ShoppingStore shop = new ShoppingStore();
		shop.setDate(DateUtils.getToday(5));
		shop.loadProducts();
		shop.loadPromotions();
		System.out.println("*************Test4 Start****************");
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(4);
		shop.addProductToBasket(1);
		shop.addProductToBasket(1);
		shop.addProductToBasket(2);

		assertTrue(1.97f == shop.getShoppingCart().getTotalPrice());
		System.out.println("TotalPrice::"+shop.getShoppingCart().getTotalPrice());
		System.out.println("*************Test4 End******************");
	}
}
