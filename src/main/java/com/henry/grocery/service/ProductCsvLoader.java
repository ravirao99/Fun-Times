package com.henry.grocery.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.henry.grocery.model.Product;

public class ProductCsvLoader {

    private final static String filename = "/product-data.csv";
    
    public static Map<Long, Product> loadProducts() throws FileNotFoundException {
        Map<Long, Product> products = new HashMap<Long, Product>();

        InputStream in = ProductCsvLoader.class.getResourceAsStream(filename);
        if (in != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
                String line;
                br.readLine(); // skip csv header
                while ((line = br.readLine()) != null) {
                    try {
                        Product p = new FromCsv(line).create();
                        products.put(p.getId(), p); 
                    } catch (NumberFormatException exc) {
                        System.err.println("Error the csv line: " + line + " cannot be parsed.");
                    }
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                
            } 
        } else {
            throw new FileNotFoundException("The file /resources/product-data.csv cannot be found.");
        }
        return products;
    }
    
    /**
     * Builder class to construct the Product from csv.
     */
    public static class FromCsv {
        
        private String[] tokens;
        
        public FromCsv(String csvData) {
            tokens = csvData.split(",");
            for (int i = 0; i < tokens.length; i ++) {
                tokens[i] = tokens[i].trim();
            }
        }
        
        /**
         * create the Product from csv data
         * @return  the new Product 
         * @throws NumberFormatException in case there is a problem with the csv data
         */
        public Product create() throws NumberFormatException {
            int id;
            String name;
            float price;
            
            id = Integer.parseInt(tokens[0].trim());
            name = tokens[1];
            price = Float.parseFloat(tokens[2].trim().substring(1, tokens[2].length()));
            
            return new Product(id, name, price);
        }
    }
}
