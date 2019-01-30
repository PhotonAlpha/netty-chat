package com.ethan.serializer;


import com.ethan.serializer.impl.JsonSerializer;

/**
 * @author tmpil9
 * @version 1.0
 * @date 22/01/2019
 */
public interface Serializer {
    /**
     * JSON serializer
     *
     * @author tmpil9
     * @date 22/01/2019 11:53 AM
     */
    byte JSON_SERIALIZER = 1;
    Serializer DEFAULT = new JsonSerializer();

    /**
     *  Serializer Algorithm
     * @return {byte}
     * @author tmpil9
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
