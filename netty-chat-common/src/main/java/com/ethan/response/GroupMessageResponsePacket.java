package com.ethan.response;

import com.ethan.protocol.command.Packet;
import com.ethan.session.Session;
import lombok.Data;

import static com.ethan.protocol.command.Command.GROUP_MESSAGE_RESPONSE;
import static com.ethan.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @version 1.0
 * @date 23/01/2019
 */
@Data
public class GroupMessageResponsePacket extends Packet {
    private String fromGroupId;
    private Session fromUser;
    private String message;
    private boolean receivable;
    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
