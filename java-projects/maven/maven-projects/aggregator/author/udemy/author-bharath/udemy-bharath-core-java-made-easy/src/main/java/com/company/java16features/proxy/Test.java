package com.company.java16features.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[] { Worker.class },
				(prox, method, arguments) -> {
						return InvocationHandler.invokeDefault(prox, method, arguments);
				});
		Method method = proxy.getClass().getMethod("work");
		System.out.println(method.invoke(proxy));
	}

}