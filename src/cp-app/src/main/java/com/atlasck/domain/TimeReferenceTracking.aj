package com.atlasck.domain;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 
 * @author G
 * 
 */
privileged aspect TimeReferenceTracking {

	declare parents: (Visitor || Answer || Question) implements ReferenceDateTime;
	declare @method: public void (com.atlasck.domain.*).updateRefDateTime() : @PreUpdate;
	declare @method: public void (com.atlasck.domain.*).updateRefDateTime() : @PrePersist;

	// TODO move to external class - not working from there due impossibility to
	// assign field value.
	public static void referenceDateTimeUpdater(ReferenceDateTime domainObject) {
		if (domainObject.getCreatedAt() == null) {
			domainObject.setCreatedAt(new Date());
		}
		domainObject.setUpdatedAt(new Date());
	}

	public void Visitor.updateRefDateTime() {
		referenceDateTimeUpdater(this);
	}

	public void Answer.updateRefDateTime() {
		referenceDateTimeUpdater(this);
	}

	public void Question.updateRefDateTime() {
		referenceDateTimeUpdater(this);
	}

}