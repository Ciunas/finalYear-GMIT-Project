package apiDatabox;

import java.io.BufferedReader;
import java.io.FileNotFoundException; 
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
		System.out.println(cs.cosineSimilarity("uploadwikimediaorg443")); 
		
	}
	

    /**
     * @param URL that is to be tested, allow/block.
     * @return cosine similarity of text1 and text2
     */
    public  double cosineSimilarity(String url) {
    	
    	double highestDotProduct = 0.0;
        double dotProduct = 0.0;
        double	magURL = 0; 
        double	magRULE = 0;       
        url = url.replaceFirst("^(http://www\\.|http://|www\\.|https://www\\.|https://|www\\.)","");
        url = url.replace(".", "").replace("/", "");
        System.out.println(url);
        Map<String, Integer> URL = termFrequencyToMap(url.split("(?!^)"));	//create a  vector from data    		
        HashSet<String> intersection;	
         
        InputStream in = getClass().getResourceAsStream("/resources/RULE.txt");
        
        Map<String, Integer> RULE = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in)) ) {
			String temp = null;

			while ((temp = br.readLine()) != null) {
				
				System.out.println(temp);
				
				
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
				 
				if (dotProduct > highestDotProduct){
					highestDotProduct = dotProduct;
				}	
				System.out.println(dotProduct);
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
		
        return highestDotProduct;
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
