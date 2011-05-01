package es.us.gescon.web;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.us.gescon.services.FacesService;

@Component("errorLoginBean")
@Scope("request")
public class ErrorLoginBean {

	@Autowired
	FacesService facesService;
	
	public ErrorLoginBean() {

	}

	@PostConstruct
	public void onLoad() {
		
		
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		boolean error = "true".equals(request.getParameter("error"));
		if (error) {
			facesService.sendMessage("i18n.msgs", "loginFail", "error", FacesMessage.SEVERITY_ERROR);
		}
	}

	public FacesService getFacesService() {
		return facesService;
	}

	public void setFacesService(FacesService facesService) {
		this.facesService = facesService;
	}
	
}
