package com.ethan.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.ethan.serializer.Serializer;
import com.ethan.serializer.SerializerAlgorithm;

/**
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
