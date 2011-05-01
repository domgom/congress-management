/**
 * 
 */
package es.us.gescon.services;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

/**
 * @author domingo
 * 
 */
@Component
public class FacesService {

	public FacesService() {

	}

	public void sendMessage(String bundleName, String error, String summary,
			FacesMessage.Severity severity) {
		sendMessage(bundleName, error, summary, severity, "");
	}

	public void sendMessage(String bundleName, String error, String summary,
			FacesMessage.Severity severity, String valueShowedFirst) {
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName,
				FacesContext.getCurrentInstance().getViewRoot().getLocale());
		String errorText = valueShowedFirst + " " + bundle.getString(error);
		String errorSummary = bundle.getString(summary);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, errorSummary, errorText));
	}

	public FacesMessage constructMessage(String bundleName, String error,
			String summary, FacesMessage.Severity severity,
			String valueShowedFirst) {
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName,
				FacesContext.getCurrentInstance().getViewRoot().getLocale());
		String errorText = valueShowedFirst + " " + bundle.getString(error);
		String errorSummary = bundle.getString(summary);

		return new FacesMessage(severity, errorSummary, errorText);

	}
}
