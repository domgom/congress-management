/**
 * 
 */
package es.us.gescon.web;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.us.gescon.domain.Congress;
import es.us.gescon.domain.Exposition;
import es.us.gescon.domain.Status;
import es.us.gescon.services.FacesService;
import es.us.gescon.services.ParticipationService;

/**
 * @author domingo
 * 
 */
@Component
@Scope("view")
public class ExpositionBean implements Serializable {

	private static final Logger logger = Logger.getLogger(ExpositionBean.class);
	private static final long serialVersionUID = 1354824087922386712L;

	transient List<Exposition> expositions = new ArrayList<Exposition>();
	transient Exposition exposition;
	transient Congress congress;
	transient List<Status> statuses;
	transient StreamedContent file;

	@Autowired
	LoginBean loginBean;

	@Autowired
	FacesService facesService;

	@Autowired
	ParticipationService participationService;

	public ExpositionBean() {

	}

	@PostConstruct
	private void populateModel() {
		expositions = new ArrayList<Exposition>(loginBean.recoverUser()
				.getExpositions());
	}

	public void fetchFile(ActionEvent event) {
		if (exposition != null) {
			file = new DefaultStreamedContent(new ByteArrayInputStream(
					exposition.getFileContent()),
					exposition.getFileContentType(), exposition.getFilename());
		}
	}

	public List<Congress> completeCongress(String query) {
		List<Congress> result = new ArrayList<Congress>();
		result = Congress
				.findCongressesByNameLikeOrUrlLikeOrMatterLikeOrLocationLikeOrEmailLike(
						query, query, query, query, query).setMaxResults(30)
				.getResultList();
		return result;

	}

	public void selectCongress(SelectEvent select) {
		if (congress != null) {
			logger.info("congress selected: " + congress);
		}
	}

	public void sendExposition(ActionEvent event) {
		participationService.sendExposition(exposition, congress);

	}

	public void fetchStatus(SelectEvent event) {
		if (exposition != null) {
			statuses = Status.findStatusesByExposition(exposition)
					.getResultList();
		}
	}

	public List<Exposition> getExpositions() {
		return expositions;
	}

	public void setExpositions(List<Exposition> expositions) {
		this.expositions = expositions;
	}

	public Exposition getExposition() {
		return exposition;
	}

	public void setExposition(Exposition exposition) {
		this.exposition = exposition;
	}

	public StreamedContent getFile() {
		return file;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Congress getCongress() {
		return congress;
	}

	public void setCongress(Congress congress) {
		this.congress = congress;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public ParticipationService getParticipationService() {
		return participationService;
	}

	public void setParticipationService(
			ParticipationService participationService) {
		this.participationService = participationService;
	}

}
