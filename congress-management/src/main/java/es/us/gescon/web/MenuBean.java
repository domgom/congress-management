package es.us.gescon.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import es.us.gescon.web.menu.IMenuBuilder;

@Component("menuBean")
@Scope("request")
public class MenuBean {  
  
    private static Map<Locale,MenuModel> userModelMap = new HashMap<Locale,MenuModel>();
    private static Map<Locale,MenuModel> adminModelMap = new HashMap<Locale,MenuModel>();
    private MenuModel model;
    public static final String ROLE_USER ="ROLE_USER";
    public static final String ROLE_ADMIN ="ROLE_ADMIN";
  
	IMenuBuilder menuBuilder;
	
	@Autowired
    public MenuBean(IMenuBuilder menuBuilder) {
		this.menuBuilder = menuBuilder;
		
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	if (principal instanceof UserDetails) {
    	  Collection<GrantedAuthority> authorities= ((UserDetails)principal).getAuthorities();
    	  if(isRole(authorities, ROLE_USER)){
    		  model = buildMenu(ROLE_USER, userModelMap);
    	  }else if(isRole(authorities, ROLE_ADMIN)){
    		  model = buildMenu(ROLE_ADMIN, adminModelMap);
    	  }
    	}  
    }

	private MenuModel buildMenu(String roleUser,Map<Locale,MenuModel> modelMap) {
		Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
//		if(!modelMap.containsKey(currentLocale)){
//			modelMap.put(currentLocale, menuBuilder.buildMenu(roleUser, currentLocale));
//		}
//		return modelMap.get(currentLocale);
	// Results into duplicate id when changing into different views
		
		return menuBuilder.buildMenu(roleUser, currentLocale);
		
	}

	private boolean isRole(Collection<GrantedAuthority> authorities, String role) {
		for(GrantedAuthority authority :authorities){
			if(role.equals(authority.getAuthority())){
				return true;
			}
		}
		return false;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public IMenuBuilder getMenuBuilder() {
		return menuBuilder;
	}

	public void setMenuBuilder(IMenuBuilder menuBuilder) {
		this.menuBuilder = menuBuilder;
	}

}  