package es.us.gescon.domain;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findExpositionsByCommentsLikeOrDescriptionLikeOrTitleLikeOrFilenameLike" })
public class Exposition {

    private String title;

    private String description;

    private String comments;
    
    private String filename;
    
    @NotNull
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fileContent;
 
    private String fileContentType;
 
    private Long fileSize;
}
