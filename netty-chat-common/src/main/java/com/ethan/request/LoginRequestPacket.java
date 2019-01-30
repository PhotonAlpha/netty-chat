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

package com.ethan.request;

import com.ethan.protocol.command.Command;
import com.ethan.protocol.command.Packet;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author tmpil9
 * @version 1.0
 * @date 22/01/2019
 */
@Data
@Accessors(chain = true)
@ToString
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
