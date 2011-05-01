/**
 * 
 */
package es.us.gescon.web;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.us.gescon.domain.Exposition;
import es.us.gescon.domain.UserRole;
import es.us.gescon.services.FacesService;

/**
 * @author domingo
 * 
 */
@Component
@Scope("session")
public class NewExpositionBean {
	Exposition exposition = new Exposition();

	@Autowired
	FacesService facesService;
	@Autowired LoginBean loginBean;
	
	public NewExpositionBean() {

	}

	public void save(ActionEvent actionEvent) {		
		//exposition.persist();
		UserRole user = loginBean.recoverUser();
		user.getExpositions().add(exposition);
		user.persist();
		facesService.sendMessage("i18n.msgs", "savedText", "saved",
				FacesMessage.SEVERITY_INFO);
		exposition = new Exposition();

	}

	public void handleFileUpload(FileUploadEvent event) {

		exposition.setFilename(event.getFile().getFileName());
		exposition.setFileSize(event.getFile().getSize());
		exposition.setFileContentType(event.getFile().getContentType());
		exposition.setFileContent(event.getFile().getContents());

		facesService.sendMessage("i18n.msgs", "uploaded", "success",
				FacesMessage.SEVERITY_INFO, "'"+event.getFile().getFileName()+"'");

	}

	public Exposition getExposition() {
		return exposition;
	}

	public void setExposition(Exposition exposition) {
		this.exposition = exposition;
	}

	
}
