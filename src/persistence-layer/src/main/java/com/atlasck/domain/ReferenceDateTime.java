package com.atlasck.domain;

import java.util.Calendar;
import java.util.Date;

/**
 * Functional Datetime fields implemented by certain domain objects.
 * 
 * @author G
 * 
 */
public interface ReferenceDateTime {

	void setCreatedAt(Calendar createdAt);

	/**
	 * Returns creation date.
	 * 
	 * @return
	 */
	Calendar getCreatedAt();

	void setUpdatedAt(Calendar createdAt);

	/**
	 * Returns latest modification date.
	 * 
	 * @return
	 */
	Calendar getUpdatedAt();
}
