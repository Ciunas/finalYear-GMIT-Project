package finalProxy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.SAXException;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;

/**
 * @author ciunas
 *
 */

public class Proxy_BoilerPipe {
	
	public Proxy_BoilerPipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean proxy_BoilerPipe( String url) throws BoilerpipeProcessingException, MalformedURLException, IOException {
		
		if(!url.contains("http")){
			url = "https://" + url;
		}
		
		final HTMLDocument htmlDoc = HTMLFetcher.fetch(new URL(url));
        TextDocument doc = null;
		try {
			doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
		} catch (BoilerpipeProcessingException | SAXException e) {
			e.printStackTrace();
		}
	
		String content = CommonExtractors.ARTICLE_EXTRACTOR.getText(doc);
		System.out.println("Content boilerpipe" + content);
		
		cosineDatabox.Requests rq = new cosineDatabox.Requests();
		
		if(rq.creteRequest(content)){			
			return true;
		} 
				
		return false;
	}



}
