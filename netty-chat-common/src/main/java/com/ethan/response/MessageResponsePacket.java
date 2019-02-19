package com.ethan.response;

import com.ethan.protocol.command.Packet;
import lombok.Data;

import static com.ethan.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @version 1.0
 * @date 23/01/2019
 */
@Data
public class MessageResponsePacket extends Packet {
    private String fromUserId;
    private String fromUserName;
    private String message;
    private boolean receivable;
    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
