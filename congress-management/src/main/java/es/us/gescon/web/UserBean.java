/**
 * 
 */
package es.us.gescon.web;

import javax.faces.application.FacesMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import es.us.gescon.domain.RoleType;
import es.us.gescon.domain.UserRole;
import es.us.gescon.services.AuthService;
import es.us.gescon.services.FacesService;

/**
 * @author domingo
 *
 */
@Component("userBean")
@Scope("request")
public class UserBean {

	final Logger logger = LoggerFactory.getLogger(UserBean.class);
	
	UserRole user = new UserRole();
	
	@Autowired
	FacesService messageManager;

	@Autowired
	AuthService authService;
	
	public UserBean(){
		
	}
	
	public void doRegister() {
		try{
			authService.registerUser(user,RoleType.ROLE_USER);	
			messageManager.sendMessage("i18n.msgs", "savedText", "saved",
					FacesMessage.SEVERITY_INFO);
		}catch(DuplicateKeyException e){
			logger.info("Duplicate user",e);
			messageManager.sendMessage("i18n.msgs", "userDuplicatedText", "userDuplicated",
					FacesMessage.SEVERITY_ERROR);
		}	
	}
	
	public UserRole getUser() {
		return user;
	}

	public void setUser(UserRole user) {
		this.user = user;
	}
	
	public FacesService getMessageManager() {
		return messageManager;
	}

	public void setMessageManager(FacesService messageManager) {
		this.messageManager = messageManager;
	}
	
}
