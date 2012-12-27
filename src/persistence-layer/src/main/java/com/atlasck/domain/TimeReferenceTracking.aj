/**
 * 
 */
package com.atlasck.domain;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author G
 *
 */
privileged aspect TimeReferenceTracking {

	//TODO implement common interface
	
	@PrePersist
	@PreUpdate
	public void Answer.updateRefDatetime() {
		if (getCreatedAt() == null) {
			setCreatedAt(new Date());
		}
		setUpdatedAt(new Date());
	}
	
	@PrePersist
	@PreUpdate
	public void Question.updateRefDatetime() {
		if (getCreatedAt() == null) {
			setCreatedAt(new Date());
		}
		setUpdatedAt(new Date());
	}
	
}