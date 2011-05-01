package es.us.gescon.domain;

import java.util.Date;

import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findStatusesByCongressOrExposition", "findStatusesByCongressAndExposition", "findStatusesByExposition", "findStatusesByCongress" })
public class Status {

    private Boolean accepted;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date sendingDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date replyDate;

    @OneToOne
    private Exposition exposition;

    @OneToOne
    private Congress congress;
}
