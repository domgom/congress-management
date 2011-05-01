/**
 * 
 */
package es.us.gescon.services;

import java.util.Date;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.us.gescon.domain.Congress;
import es.us.gescon.domain.Exposition;
import es.us.gescon.domain.Status;
import es.us.gescon.domain.UserRole;
import es.us.gescon.web.LoginBean;

/**
 * @author domingo
 *
 */
@Component
@Scope("session")
public class ParticipationService {

	@Autowired
	LoginBean loginBean;
	@Autowired
	FacesService facesService;
	
	public ParticipationService() {
		super();
	}
	
	@Transactional
	public void sendExposition(Exposition exposition, Congress congress){
		if (congress != null && exposition != null) {
			if (Status
					.findStatusesByCongressAndExposition(congress, exposition)
					.getResultList().size() > 0) {
				facesService.sendMessage("i18n.msgs", "expositionSentAlready",
						"notSaved", FacesMessage.SEVERITY_WARN);
			} else {
				Status status = new Status();
				status.setAccepted(false);
				status.setCongress(congress);
				status.setExposition(exposition);
				status.setSendingDate(new Date());
				status.persist();
				sendExpositionMessage(loginBean.recoverUser(),exposition,congress);
				facesService.sendMessage("i18n.msgs", "expositionSentOk",
						"saved", FacesMessage.SEVERITY_INFO);
			}
		} else {
			facesService.sendMessage("i18n.msgs", "expositionSentFail",
					"error", FacesMessage.SEVERITY_ERROR);
		}
	}

	@Transactional
	public void participate(Congress congress){
		if (congress != null) {
			// Reatach entity
			congress = Congress.findCongress(congress.getId()); 
			UserRole user = loginBean.recoverUser();
			if (user.getCongresses().contains(congress)) {
				facesService
						.sendMessage("i18n.msgs", "alreadyParticipateText",
								"alreadyParticipateSummary",
								FacesMessage.SEVERITY_WARN);
			} else {
				user.getCongresses().add(congress);
				user.persist();
				participateMessage(user,congress);
				facesService.sendMessage("i18n.msgs", "participateText",
						"participateSummary", FacesMessage.SEVERITY_INFO);
			}
		}
	}
	
	private void sendExpositionMessage(UserRole recoverUser,
			Exposition exposition, Congress congress) {
		System.out.println("SENDING MAIL TO CONGRESS AND MESSAGE TO USER: EXPOSITION SENT");
		
	}
	
	private void participateMessage(UserRole user, Congress congress) {
		System.out.println("SENDING MAIL TO CONGRESS AND MESSAGE TO USER: PARTICIPATION REQUEST SENT");
		
	}
	
	public void acceptParticipate(Congress congress, UserRole user){
		//On mail received from congress
		System.out.println("SENDING MAIL TO USER AND MESSAGE TO USER: PARTICIPATION IN CONGRESS ACCEPTED");
	}
	
	public void acceptExposition(Exposition exposition, Congress congress, UserRole user){
		//On mail received from congress
		System.out.println("SENDING MAIL TO USER AND MESSAGE TO USER: EXPOSITION IN CONGRESS ACCEPTED");
	}
	
	public void rejectExposition(Exposition exposition, Congress congress, UserRole user){
		//On mail received from congress
		System.out.println("SENDING MAIL TO USER AND MESSAGE TO USER: EXPOSITION IN CONGRESS REJECTED");
	}
}
