package com.atlasck.backend.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import com.sun.xml.internal.bind.v2.TODO;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooEntity(versionField = "", table = "questions", schema = "atlasck", finders = { "findQuestionsesByAnswerss" })
public class Questions {

    @Column(name = "question", columnDefinition = "TEXT")
    private String question;

    @PreUpdate
    @PrePersist
    public void updateTimestamps() {
        setUpdatedAt(new Date());
        if (getCreatedAt() == null) {
            setCreatedAt(new Date());
        }
    }

    /**
	 * List witht questions without answers
	 *
	 * {@link TODO needs to be implemented}
	 */
    public List<Questions> findQuestionsWithoutAnswer() {

    	CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        CriteriaQuery<Questions> criteriaQuery = criteriaBuilder.createQuery(Questions.class);
        Root<Questions> from = criteriaQuery.from(Questions.class);
        CriteriaQuery<Questions> select = criteriaQuery.select(from);
        TypedQuery<Questions> typedQuery = entityManager().createQuery(select);
        return typedQuery.getResultList();
    }
}
