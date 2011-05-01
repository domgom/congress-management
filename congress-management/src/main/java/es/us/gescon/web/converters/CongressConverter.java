/**
 * 
 */
package es.us.gescon.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import es.us.gescon.domain.Congress;

/**
 * @author domingo
 * 
 */

public class CongressConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Congress result = null;
		if ("".equals(value) || value == null) {
			return null;
		}

		try {
			result = Congress.findCongress(Long.valueOf(value));
		} catch (NumberFormatException e) {
			// Because of the autocomplete sometimes comes a Long (when
			// autocomplete option is selected) and sometimes String (when no
			// option was selected but text introduced)
		}
		return result;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Congress congress = (Congress) value;
		return congress.getId() + "";
	}

}
