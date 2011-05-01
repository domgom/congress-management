/**
 * 
 */
package es.us.gescon.web.menu;

import java.util.Locale;

import org.primefaces.model.MenuModel;

/**
 * @author domingo
 *
 */
public interface IMenuBuilder {

	//Returns the menu for the given role considering locale issues
	MenuModel buildMenu(String role, Locale locale);
}
