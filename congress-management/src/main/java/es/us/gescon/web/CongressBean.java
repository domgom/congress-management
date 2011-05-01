/**
 * 
 */
package es.us.gescon.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.us.gescon.domain.Congress;
import es.us.gescon.domain.Exposition;
import es.us.gescon.domain.Status;
import es.us.gescon.domain.UserRole;
import es.us.gescon.services.FacesService;
import es.us.gescon.services.ParticipationService;

/**
 * @author domingo
 * 
 */
@Component
@Scope("view")
public class CongressBean implements Serializable {

	private static final long serialVersionUID = 906476786;
	private static final Logger logger = Logger.getLogger(CongressBean.class);
	transient LazyDataModel<Congress> lazyModelData;
	transient ScheduleModel lazyModelSchedule;

	transient Congress congress;
	transient Exposition exposition;
	transient List<Status> statuses;
	boolean participable;

	@Autowired
	transient FacesService facesService;

	@Autowired
	transient LoginBean loginBean;

	@Autowired
	ParticipationService participationService;
	
	public CongressBean() {
		populateModels();
	}

	private void populateModels() {

		lazyModelData = new LazyDataModel<Congress>() {

			private static final long serialVersionUID = 758769908;

			@Override
			public List<Congress> load(int first, int pageSize,
					String sortField, boolean sortOrder,
					Map<String, String> filter) {

				List<Congress> congresses = new ArrayList<Congress>();
				if (sortField == null) {
					sortField = "name";
				}
				congresses = Congress.findCongressEntriesSortedFiltered(first,
						pageSize, sortField, sortOrder, filter);

				return congresses;
			}
		};

		lazyModelData.setRowCount(Integer.parseInt(Congress.countCongresses()
				+ ""));

		lazyModelSchedule = new LazyScheduleModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public void loadEvents(Date start, Date end) {
				TypedQuery<Congress> result = Congress
						.findCongressesByOpeningBetween(start, end);
				int colourIndex = 0;
				for (Congress c : result.getResultList()) {
					DefaultScheduleEvent event = new DefaultScheduleEvent(
							c.getName(), c.getOpening(), c.getClosure(), c);
					event.setStyleClass(getColour(colourIndex));
					addEvent(event);
					colourIndex++;
				}
			}

			private String getColour(int colourIndex) {
				return "fc-event-colour-" + (colourIndex % 10);
			}
		};
	}

	public void selectCongress(SelectEvent event) {
		participable = true;
		if (congress != null) {
			UserRole user = loginBean.recoverUser();
			congress = Congress.findCongress(congress.getId());
			if (user.getCongresses().contains(congress)) {
				participable = false;
			}
		}
		if (congress != null) {
			statuses = Status.findStatusesByCongress(congress)
					.getResultList();
		}
		logger.info("congress selected: " + congress);
	}
	
	public void participate(ActionEvent actionEvent) {
		participationService.participate(congress);
	}

	public void sendExposition(ActionEvent actionEvent) {
		participationService.sendExposition(exposition, congress);
	}
	
	public List<Exposition> completeExposition(String query) {
		List<Exposition> result = new ArrayList<Exposition>();

		result = Exposition.findExpositionsByCommentsLikeOrDescriptionLikeOrTitleLikeOrFilenameLike(query, query, query, query).setMaxResults(30).getResultList();
		return result;

	}

	public void selectExposition(SelectEvent select) {
		if (exposition != null) {
			logger.info("exposition selected: " + exposition);
		}
	}
	
	public boolean isParticipable() {
		return participable;
	}

	public void setParticipable(boolean participable) {
		this.participable = participable;
	}

	public Congress getCongress() {
		return congress;
	}

	public void setCongress(Congress congress) {
		this.congress = congress;
	}

	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
		this.congress = (Congress) selectEvent.getScheduleEvent().getData();
	}

	public LazyDataModel<Congress> getLazyModelData() {
		return lazyModelData;
	}

	public void setLazyModelData(LazyDataModel<Congress> lazyModelData) {
		this.lazyModelData = lazyModelData;
	}

	public ScheduleModel getLazyModelSchedule() {
		return lazyModelSchedule;
	}

	public void setLazyModelSchedule(ScheduleModel lazyModelSchedule) {
		this.lazyModelSchedule = lazyModelSchedule;
	}

	public FacesService getFacesService() {
		return facesService;
	}

	public void setFacesService(FacesService facesService) {
		this.facesService = facesService;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public ParticipationService getParticipationService() {
		return participationService;
	}

	public void setParticipationService(ParticipationService participationService) {
		this.participationService = participationService;
	}

	public Exposition getExposition() {
		return exposition;
	}

	public void setExposition(Exposition exposition) {
		this.exposition = exposition;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}
	
}
