package com.ethan.request;

import com.ethan.protocol.command.Command;
import com.ethan.protocol.command.Packet;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
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
