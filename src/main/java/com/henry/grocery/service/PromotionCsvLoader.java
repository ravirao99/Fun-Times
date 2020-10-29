package com.henry.grocery.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.henry.grocery.model.Promotion;

public class PromotionCsvLoader {

    private final static String filename = "/promotion-data.csv";
    
    public static Map<Long, Promotion> loadPromotions() throws FileNotFoundException {
        Map<Long, Promotion> promotions = new HashMap<Long, Promotion>();

        InputStream in = PromotionCsvLoader.class.getResourceAsStream(filename);
        if (in != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
                String line;
                br.readLine(); // skip csv header
                while ((line = br.readLine()) != null) {
                    try {
                        Promotion p = new FromCsv(line).create();
                        promotions.put(p.getId(), p); 
                    } catch (NumberFormatException exc) {
                        System.err.println("Error the csv line: " + line + " cannot be parsed.");
                    }
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                
            } 
        } else {
            throw new FileNotFoundException("The file /resources/promotion-data.csv cannot be found.");
        }
        return promotions;
    }
    
    /**
     * Builder class to construct the Promotion from csv.
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
         * create the Promotion from csv data
         * @return  the new Promotion 
         * @throws NumberFormatException in case there is a problem with the csv data
         */
        public Promotion create() throws NumberFormatException {
            int id;
            String name;
            float price;
			String startDate;
			String endDate;
			long discountId;
			int eligibleCount;
            
            id = Integer.parseInt(tokens[0].trim());
            name = tokens[1];
            price = Float.parseFloat(tokens[2].trim().substring(1, tokens[2].length()));
            startDate =tokens[3];
			endDate =tokens[4];
			discountId =Long.parseLong(tokens[5]);
			eligibleCount=Integer.parseInt(tokens[6]);
					
            return new Promotion(id, name, price,startDate,endDate,discountId,eligibleCount);
        }
    }
}
