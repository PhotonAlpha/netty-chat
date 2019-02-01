package com.ethan.attribute;

import io.netty.util.AttributeKey;

/**
 * @version 1.0
 * @date 23/01/2019
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
