package com.ethan.response;

import com.ethan.protocol.command.Packet;
import lombok.Data;

import java.util.List;

import static com.ethan.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * @version 1.0
 * @date 19/02/2019
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override

    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
