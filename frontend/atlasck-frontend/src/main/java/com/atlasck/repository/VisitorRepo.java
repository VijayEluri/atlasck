package com.atlasck.repository;

import com.atlasck.domain.Visitor;

public interface VisitorRepo extends AbstractRepo<Visitor> {

	/**
	 * Retrieves user's object by email
	 * @param email
	 * @return
	 */
	Visitor getVisitorByEmail(String email);
}
