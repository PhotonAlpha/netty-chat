/*
 * System Name         : GEBNexGen
 * Program Id          : netty-chat
 * Author              : tmpil9
 * Date                : 22/01/2019
 * Copyright (c) United Overseas Bank Limited Co.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * United Overseas Bank Limited Co. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * United Overseas Bank Limited Co.
 */

package com.ethan.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.ethan.serializer.Serializer;
import com.ethan.serializer.SerializerAlgorithm;

/**
 * @author tmpil9
 * @version 1.0
 * @date 22/01/2019
 */
public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object o) {
        return JSON.toJSONBytes(o);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
