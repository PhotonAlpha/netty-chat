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
