package es.us.gescon.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findCongressesByOpeningBetweenAndUrlLikeAndMatterLikeAndLikeAndLocationLikeAndNameLikeAndImportance", "findCongressesByOpeningBetweenOrUrlLikeOrMatterLikeOrLocationLikeOrNameLikeOrImportance","findCongressesByNameLikeOrUrlLikeOrMatterLikeOrLocationLikeOrEmailLike", "findCongressesByOpeningBetween" })
public class Congress {

    static final Logger logger = LoggerFactory.getLogger(Congress.class);

    private String name;

    private String location;

    private String matter;

    private String url;

    private String comments;

    @Enumerated
    private Act act;

    @Enumerated
    private Importance importance;

    @Enumerated
    private Recurrence recurrence;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date opening;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date closure;

    private double latitude;

    private double longitude;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date callForPapers;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date callForParticipation;

    private String email;

    public static List<Congress> findCongressEntriesSortedFiltered(int firstResult, int maxResults, String sortingField, boolean orderAsc, Map<String, String> filters) {
        if (sortingField == null) throw new IllegalArgumentException("The sortingField argument is required");
        String queryText = buildQueryText(filters, sortingField, orderAsc);
        TypedQuery<Congress> query = entityManager().createQuery(queryText, Congress.class);
        for (String key : filters.keySet()) {
            query.setParameter(key, "%" + filters.get(key).toLowerCase() + "%");
        }
        logger.debug(queryText + " with parameters " + filters);
        return query.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    private static String buildQueryText(Map<String, String> filters, String sortingField, boolean orderAsc) {
        StringBuilder queryText = new StringBuilder();
        queryText.append("SELECT o FROM Congress o ");
        for (String key : filters.keySet()) {
            queryText.append(" AND LOWER(o.");
            queryText.append(key);
            queryText.append(") LIKE :");
            queryText.append(key);
            queryText.append(" ");
        }
        queryText.append(" ORDER BY o.");
        queryText.append(sortingField);
        queryText.append(" ");
        queryText.append((orderAsc ? "ASC" : "DESC"));
        return queryText.toString().replaceFirst("AND", "WHERE");
    }

    public boolean isParticipable() {
        if (callForParticipation != null) {
            return todayIsBefore(callForParticipation) && todayIsBefore(opening) && todayIsBefore(closure);
        } else {
            return todayIsBefore(opening) && todayIsBefore(closure);
        }
    }

    public boolean isSendable() {
        if (callForPapers != null) {
            return todayIsBefore(callForPapers) && todayIsBefore(opening) && todayIsBefore(closure);
        } else {
            return todayIsBefore(opening) && todayIsBefore(closure);
        }
    }

    private boolean isBefore(Date first, Date last) {
        return first.getTime() <= last.getTime();
    }

    private boolean todayIsBefore(Date date) {
        return isBefore(new Date(), date);
    }
}
