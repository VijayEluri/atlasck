package com.atlasck.repository;

import com.atlasck.domain.Visitor;

/**
 * 
 * @author Georgi Lambov
 *
 */
public interface VisitorRepo extends AbstractRepo<Visitor> {

	/**
	 * Retrieves user's object by email
	 * @param email
	 * @return
	 */
	Visitor getVisitorByEmail(String email);

	/**
	 * Upedates user's object
	 * @param visitor
	 */
	void update(Visitor visitor);
}
