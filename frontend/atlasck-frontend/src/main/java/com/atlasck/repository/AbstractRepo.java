package com.atlasck.repository;

import java.util.List;

public interface AbstractRepo<E> {

	E get(Integer id);

	List<?> getAll();

	void add(E e);
}
