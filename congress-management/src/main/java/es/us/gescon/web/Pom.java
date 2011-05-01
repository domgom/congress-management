/**
 * 
 */
package es.us.gescon.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author domingo
 * 
 */
@Component
@Scope("application")
public class Pom {

	private String version;

	public Pom() {

		try {
			InputStream in = Pom.class
					.getResourceAsStream("/META-INF/maven/es.us.gescon/gescon/pom.properties");
			Properties mProps = new Properties();
			mProps.load(in);
			version = (String) mProps.get("version");
		} catch (Exception e) {
			//If version could not be recovered nothing is shown.
		}

	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
