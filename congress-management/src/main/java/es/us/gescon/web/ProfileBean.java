/**
 * 
 */
package es.us.gescon.web;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.us.gescon.domain.UserRole;
import es.us.gescon.services.FacesService;

/**
 * @author domingo
 *
 */
@Component("profileBean")
@Scope("request")
public class ProfileBean {
	UserRole user;
	
	@Autowired
	FacesService facesService;
	@Autowired
	LoginBean loginBean;
	
	public ProfileBean(){

	}
	@PostConstruct
	private void recoverUser(){
		user = loginBean.recoverUser();
	}
	
	public void save(ActionEvent actionEvent){
		
		user.persist();
		facesService.sendMessage("i18n.msgs", "savedText", "saved",
				FacesMessage.SEVERITY_INFO);
	}

	public UserRole getUser() {
		return user;
	}

	public void setUser(UserRole user) {
		this.user = user;
	}

}
