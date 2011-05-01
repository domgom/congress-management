/**
 * 
 */
package es.us.gescon.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.us.gescon.domain.Act;
import es.us.gescon.domain.Congress;
import es.us.gescon.domain.Importance;
import es.us.gescon.domain.Recurrence;
import es.us.gescon.services.FacesService;
import es.us.gescon.services.GeoResolverService;

/**
 * @author domingo
 * 
 */
@Component("newCongressBean")
@Scope("request")
public class NewCongressBean {
	Congress congress = new Congress();

	@Autowired
	FacesService facesService;

	@Autowired
	GeoResolverService geoResolver;

	public NewCongressBean() {

	}

	public void save(ActionEvent actionEvent) {
		double[] coords;
		String savedText = "savedText";
		try {
			coords = geoResolver
					.resolveCoordinates(congress.getLocation(), FacesContext
							.getCurrentInstance().getViewRoot().getLocale());
			congress.setLatitude(coords[0]);
			congress.setLongitude(coords[1]);
		} catch (Exception e) {
			savedText = "savedTextNoCoords";
		}

		if (congress.getOpening().getTime() > congress.getClosure().getTime()) {
			facesService.sendMessage("i18n.msgs", "openingAfterClosureText",
					"openingAfterClosure", FacesMessage.SEVERITY_ERROR);
		} else {
			congress.persist();
			congress = new Congress();
			facesService.sendMessage("i18n.msgs", savedText, "saved",
					FacesMessage.SEVERITY_INFO);
		}

	}

	public List<String> newLocation(String input) {
		List<String> options = new ArrayList<String>();
		try {
			options = geoResolver.resolveCityProposals(input, FacesContext
					.getCurrentInstance().getViewRoot().getLocale());
		} catch (Exception e) {
			facesService.sendMessage("i18n.msgs", "notFoundText", "notFound",
					FacesMessage.SEVERITY_WARN, input);
		}
		facesService.sendMessage("i18n.msgs", "foundText", "found",
				FacesMessage.SEVERITY_INFO, input);

		return options;
	}

	public void validateUrl(FacesContext context,
			UIComponent componentToValidate, Object value)
			throws ValidatorException {
		String url = (String) value;
		if (!url.startsWith("http://")) {

			throw new ValidatorException(facesService.constructMessage(
					"i18n.msgs", "malformedUrlText", "malformedUrlSummary",
					FacesMessage.SEVERITY_ERROR, url));
		}
	}

	public List<Act> getActs() {
		return Arrays.asList(Act.values());
	}

	public List<Importance> getImportances(){
		return Arrays.asList(Importance.values());
	}
	
	public List<Recurrence> getRecurrences(){
		return Arrays.asList(Recurrence.values());
	}
	public Congress getCongress() {
		return congress;
	}

	public void setCongress(Congress congress) {
		this.congress = congress;
	}

}
