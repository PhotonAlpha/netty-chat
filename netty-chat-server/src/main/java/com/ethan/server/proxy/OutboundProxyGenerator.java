/*
 * System Name         : GEBNexGen
 * Program Id          : netty-chat
 * Author              : tmpil9
 * Date                : 31/01/2019
 * Copyright (c) United Overseas Bank Limited Co.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * United Overseas Bank Limited Co. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * United Overseas Bank Limited Co.
 */

package com.ethan.server.proxy;

import io.netty.channel.ChannelOutboundHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author tmpil9
 * @version 1.0
 * @date 31/01/2019
 */
public class OutboundProxyGenerator {
    public static <T>T newInstance(T target) throws Exception {
        Class<?> proxyClazz = Proxy.getProxyClass(target.getClass().getClassLoader(), ChannelOutboundHandler.class);
        Constructor<?> constructor = proxyClazz.getConstructor(InvocationHandler.class);
        return (T)constructor.newInstance(new DynamicProxyHandler(target));
        // Enhancer e = new Enhancer();
        // e.setSuperclass(target.getClass());
        // e.setCallback(new DynamicProxy());
        // return (T)e.create();
    }

}
