package com.ethan.server.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author tmpil9
 * @version 1.0
 * @date 31/01/2019
 */
public class DynamicProxyHandler implements InvocationHandler {
    private static List<String> methods = Arrays.asList("channelRead", "write");
    private Object target;

    public DynamicProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "-start->" + System.currentTimeMillis());
        Object result = method.invoke(target, args);
        System.out.println(method.getName() + "-end->" + System.currentTimeMillis());
        return result;
    }
}
