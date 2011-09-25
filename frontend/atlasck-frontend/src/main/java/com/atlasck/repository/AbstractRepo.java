package com.atlasck.repository;

import java.util.List;

/**
 * Abstract repo interfaces that with all basic methods
 * needed in the implementation of Repo
 *
 * @author Georgi Lambov
 *
 * @param <E>
 */
public interface AbstractRepo<E> {

	E get(Integer id);

	List<?> getAll();

	void add(E e);
}
