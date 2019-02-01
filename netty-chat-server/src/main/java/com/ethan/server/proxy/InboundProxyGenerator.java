package com.ethan.server.proxy;

import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelOutboundHandler;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @version 1.0
 * @date 31/01/2019
 */
public class InboundProxyGenerator {
    public static <T>T newInstance(T target) throws Exception {
        Class<?> proxyClazz = Proxy.getProxyClass(target.getClass().getClassLoader(), ChannelInboundHandler.class);
        Constructor<?> constructor = proxyClazz.getConstructor(InvocationHandler.class);
        return (T)constructor.newInstance(new DynamicProxyHandler(target));
        // Enhancer e = new Enhancer();
        // e.setSuperclass(target.getClass());
        // e.setCallback(new DynamicProxy());
        // return (T)e.create();
    }

}
