package com.ethan.protocol.command;

import lombok.Data;

/**
 * @author tmpil9
 * @version 1.0
 * @date 22/01/2019
 */
@Data
public abstract class Packet {
    /**
     * @description: protocol version
     */
    private Byte version = 1;

    /**
     * @description: command
     */
    public abstract Byte getCommand();
}
