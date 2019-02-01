package com.ethan.serializer;


import com.ethan.serializer.impl.JsonSerializer;

public interface Serializer {
    /**
     * JSON serializer
     *
     * @date 22/01/2019 11:53 AM
     */
    byte JSON_SERIALIZER = 1;
    Serializer DEFAULT = new JsonSerializer();

    /**
     *  Serializer Algorithm
     * @return {byte}
     * @date 22/01/2019 3:42 PM
     */
    byte getSerializerAlgorithm();

    /**
     * serialize method
     * Object transfer to  binary data
     * @param o target object
     * @return byte[]
     */
    byte[] serialize(Object o);

    /**
     * fetch data by rule id
     *
     * @param clazz class type
     * @param bytes serializer data
     * @return T
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
