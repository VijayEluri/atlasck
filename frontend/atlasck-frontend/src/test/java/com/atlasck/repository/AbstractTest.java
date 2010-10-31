package com.atlasck.repository;

import java.lang.reflect.Method;

import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;


public abstract class AbstractTest<T> extends AbstractTransactionalTestNGSpringContextTests{

	protected T object;

	// create class by type object
	protected void init(String param1, String param2) throws Exception {
		Class<?> object = (Class<T>) new Object();
		Method m1 = object.getMethod("set" + param1, String.class);
		Method m2 = object.getMethod("set" + param2, String.class);

		m1.invoke(object, param1);
		m2.invoke(object, param2);

		this.object = (T) object;
	}
}
