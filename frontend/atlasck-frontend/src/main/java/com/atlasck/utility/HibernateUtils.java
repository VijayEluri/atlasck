package com.atlasck.utility;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;

/**
 * Hibernate helper methods
 * @author georgi
 *
 */
public class HibernateUtils {
	
	/**
	 * Supresses hibernate casting warning for List type results
	 * @param <T>
	 * @param q
	 * @return
	 */
	public static <T> List<T> listAndCast(Query q) {
		@SuppressWarnings("unchecked")
		List<T> list = q.list();
		return list;
	}
	
	/**
	 * Supresses hibernate casting warning for List type results
	 * @param <T>
	 * @param q
	 * @return
	 */
	public static <T> List<T> listAndCast(Criteria c) {
		@SuppressWarnings("unchecked")
		List<T> list = c.list();
		return list;
	}
}
