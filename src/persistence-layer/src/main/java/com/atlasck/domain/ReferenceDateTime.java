package com.atlasck.domain;

import java.util.Date;

/**
 * Functional Datetime fields implemented by 
 * certain domain objects.
 * 
 * @author G
 * 
 */
public interface ReferenceDateTime {

	void setCreatedAt(Date createdAt);

	/**
	 * Returns creation date.
	 * 
	 * @return
	 */
	Date getCreatedAt();

	void setUpdatedAt(Date createdAt);

	/**
	 * Returns latest modification date.
	 * 
	 * @return
	 */
	Date getUpdatedAt();
}
