package com.ethan.request;

import com.ethan.protocol.command.Packet;
import lombok.Data;

import java.util.List;

import static com.ethan.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @version 1.0
 * @date 19/02/2019
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
