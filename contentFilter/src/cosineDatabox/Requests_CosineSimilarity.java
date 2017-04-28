package cosineDatabox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author ciunas
 *
 */

public class Requests_CosineSimilarity {


	public static void main(String[] args){
			
		Requests_CosineSimilarity cs = new Requests_CosineSimilarity();
		System.out.println(cs.cosineSimilarity("http://www.adverts.ie/for-sale/cars-motorbikes-boats/cars/2/")); 
		
	}
	

    /**
     * @param URL that is to be tested, allow/block.
     * @return cosine similarity of text1 and text2
     */
    public  double cosineSimilarity(String url) {
    	
    	double dotProductHighest = 0.0;
    	double dotProductAdded = 0.0;
        double dotProduct = 0.0;
        double	magURL = 0; 
        double	magRULE = 0;       
        url = url.replaceFirst("^(http://www\\.|http://|www\\.|https://www\\.|https://|www\\.)","");
        url = url.replace(".ie", "").replace(".com", "").replace(".uk", "");
        url = url.replace(".", "").replace("/", "").replace("-", "");

        System.out.println(url);
        Map<String, Integer> URL = termFrequencyToMap(url.split("(?!^)"));	//create a  vector from data    		
        HashSet<String> intersection;	
          
        
        Map<String, Integer> RULE = null;
		try (BufferedReader br = new BufferedReader(new FileReader("/home/ciunas/RULES.txt")); ) {
			String temp = null;

			while ((temp = br.readLine()) != null) {
				
				
				if(url.contains(temp)){
					dotProductAdded += 0.2;
				}
				
				RULE = termFrequencyToMap(temp.split("(?!^)"));
				
				dotProduct = 0.0;
				intersection = new HashSet<>( URL.keySet() );
				intersection.retainAll(RULE.keySet()); 								// uniqe words related to each map

				for (String item : intersection) {
					dotProduct += URL.get(item) * RULE.get(item); 					// Calculate dot product
				}

				for (String k : URL.keySet()) { 									// Calculate magnitude for URL and RULE
					magURL += Math.pow(URL.get(k), 2);
				}
				
				for (String k : RULE.keySet()) {
					magRULE += Math.pow(RULE.get(k), 2);
				}
							
				dotProduct = dotProduct / Math.sqrt(magURL * magRULE);
				 
				if (dotProduct > dotProductHighest){
					dotProductHighest = dotProduct;
				}
				System.out.println("dot product: " + dotProduct);
				magURL = 0.0;
				magRULE = 0.0;
				intersection.clear();
				RULE.clear(); 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("dot product added: " + dotProductAdded);
		System.out.println("dot product added + Highest: " + dotProductHighest + dotProductAdded);
        return dotProductHighest + dotProductAdded;
    }
    
    
	/**
	 * @param String array containing URL data
	 * @return map containg breakdown of String[] passed to it
	 */
	public Map<String, Integer> termFrequencyToMap(String[] letters) {

		Map<String, Integer> mapped = new HashMap<>();

		for (String letter : letters) {
			Integer n = mapped.get(letter);
			n = (n == null) ? 1 : ++n;
			mapped.put(letter, n);
		}
		return mapped;
	}
}
