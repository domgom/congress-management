package es.us.gescon.web.menu;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("menuBuilder")
public class MenuBuilder implements IMenuBuilder {

	final Logger logger = LoggerFactory.getLogger(MenuBuilder.class);
	

	public MenuBuilder() {

	}

	@Override
	public MenuModel buildMenu(String role, Locale locale) {
		Document doc = null;
		try {
			Builder parser = new Builder();
			doc = parser.build(getClass().getResourceAsStream(
					"/menu/" + role + ".xml"));

		} catch (ParsingException ex) {
			logger.error("Malformed document", ex);
		} catch (IOException ex) {
			logger.error("Could not found file", ex);
		}

		ResourceBundle menuBundle = ResourceBundle.getBundle("i18n.menu",locale);
		MenuModel model = new DefaultMenuModel();
		Elements children = doc.getRootElement().getChildElements();
		String root = doc.getRootElement().getAttributeValue("name");
		for (int i = 0; i < children.size(); i++) {
			addSection(children.get(i), model, menuBundle, root);
		}

		return model;
	}

	private void addSection(Element section, MenuModel model,
			ResourceBundle menuBundle, String role) {
		
		Submenu submenu = new Submenu();
		String sectionKey = role + "." + section.getAttributeValue("name");
		
		logger.debug("section: " + sectionKey);
		submenu.setLabel(menuBundle.getString(sectionKey));
		model.addSubmenu(submenu);
		
		Elements items = section.getChildElements();
		for (int i = 0; i < items.size(); i++) {
			addItem(items.get(i), model, menuBundle, role, sectionKey,submenu);
		}

	}

	private void addItem(Element element, MenuModel model,
			ResourceBundle menuBundle, String role, String sectionKey, Submenu submenu) {
		MenuItem item = new MenuItem();  
		String itemKey = sectionKey +"." + element.getAttributeValue("name");
		item.setValue(menuBundle.getString(itemKey));  
		item.setUrl("/"+itemKey.replaceAll("\\.", "/")+".jsf"); 
		
		submenu.getChildren().add(item);
		
		logger.debug("item: " + itemKey);

	}

}
