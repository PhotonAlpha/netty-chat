package com.learning.server;

import org.junit.Test;

import java.util.UUID;

/**
 * @version 1.0
 * @date 19/02/2019
 */
public class RandomTets {
    @Test
    public void randomStringTets() {
        System.out.println(UUID.randomUUID().toString());
    }
}
