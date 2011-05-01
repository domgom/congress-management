/**
 * 
 */
package es.us.gescon.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.ParsingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author domingo
 * 
 */
@Component
public class GeoResolverService {

	final Logger logger = LoggerFactory.getLogger(GeoResolverService.class);

	public double[] resolveCoordinates(String city, Locale language) throws Exception {
		double[] result = new double[] {};
		if ("" != city && city != null) {
			result = extractCoordsFromXml(connect(city,language));

		}
		return result;
	}
	

	public List<String> resolveCityProposals(String city, Locale language) throws IOException {
		List<String> proposals = new ArrayList<String>();

		if ("" != city && city != null) {
			proposals = extractNamesFromXml(connect(city,language));
		}

		return proposals;
	}

	private InputStream connect(String city, Locale language) throws IOException {
		city = URLEncoder.encode(city,"UTF-8");
		URL hp = new URL("http://maps.google.com/maps/api/geocode/xml?address="
				+ city + "&sensor=false&language="+language);
		URLConnection hpCon = hp.openConnection();
		logger.debug("Date: " + new Date(hpCon.getDate()));
		logger.debug("Content-Type: " + hpCon.getContentType());
		logger.debug("Expires: " + hpCon.getExpiration());
		logger.debug("Last-Modified: "
				+ new Date(hpCon.getLastModified()));
		int len = hpCon.getContentLength();
		logger.debug("Content-Length: " + len);
		if (len <= 0) {
			throw new IOException("Content not available");
		}

		return hpCon.getInputStream();
	}


	private List<String> extractNamesFromXml(InputStream is) {
		Document doc = null;
		List<String> names = new ArrayList<String>();
		try {
			Builder parser = new Builder();
			doc = parser.build(is);
		} catch (ParsingException ex) {
			logger.error("Malformed document", ex);
		} catch (IOException ex) {
			logger.error("Could not found file", ex);
		}

		Nodes nodes = doc.query("/GeocodeResponse/result/formatted_address");
		for(int i = 0; i<nodes.size();i++){
			Node node = nodes.get(i);
			names.add(node.getValue());
		}
		
		return names;
	}

	private double[] extractCoordsFromXml(InputStream is) {
		Document doc = null;
		try {
			Builder parser = new Builder();
			doc = parser.build(is);
		} catch (ParsingException ex) {
			logger.error("Malformed document", ex);
		} catch (IOException ex) {
			logger.error("Could not found file", ex);
		}

		double latitude = Double.parseDouble(doc
				.query("/GeocodeResponse/result/geometry/location/lat").get(0)
				.getValue());
		double longitude = Double.parseDouble(doc
				.query("/GeocodeResponse/result/geometry/location/lng").get(0)
				.getValue());

		return new double[] { latitude, longitude };
	}
}
