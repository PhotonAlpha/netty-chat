/*
 * System Name         : GEBNexGen
 * Program Id          : netty-chat
 * Author              : tmpil9
 * Date                : 23/01/2019
 * Copyright (c) United Overseas Bank Limited Co.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * United Overseas Bank Limited Co. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * United Overseas Bank Limited Co.
 */

package com.ethan.response;

import com.ethan.protocol.command.Packet;
import lombok.Data;

import static com.ethan.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author tmpil9
 * @version 1.0
 * @date 23/01/2019
 */
@Data
public class MessageResponsePacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
