package com.ethan.response;

import com.ethan.protocol.command.Packet;
import lombok.Data;
import lombok.ToString;

import static com.ethan.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @version 1.0
 * @date 22/01/2019
 */
@Data
@ToString
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
