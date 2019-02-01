package com.ethan.protocol.command;

import com.ethan.request.LoginRequestPacket;
import com.ethan.request.MessageRequestPacket;
import com.ethan.response.LoginResponsePacket;
import com.ethan.response.MessageResponsePacket;
import com.ethan.serializer.Serializer;
import com.ethan.serializer.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.ethan.protocol.command.Command.LOGIN_REQUEST;
import static com.ethan.protocol.command.Command.LOGIN_RESPONSE;
import static com.ethan.protocol.command.Command.MESSAGE_REQUEST;
import static com.ethan.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author tmpil9
 * @version 1.0
 * @date 22/01/2019
 */
public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JsonSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);

        INSTANCE = Singleton.INSTANCE.getPacketCodeC();
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 1. create ByteBuf
        // ioBuffer() 方法会返回适配 io 读写相关的内存，它会尽可能创建一个直接内存，直接内存可以理解为不受 jvm 堆管理的内存空间，写到 IO 缓冲区的效果更高
        // ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2. serializer Java Object
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3. reality transfer data
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // skip magic number
        byteBuf.skipBytes(4);
        // skip version
        byteBuf.skipBytes(1);
        // get serializer algorithm
        byte serializerAlgorithm = byteBuf.readByte();
        // get command
        byte command = byteBuf.readByte();
        // get data packer length
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return serializerMap.get(serializerAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

    enum Singleton {
        INSTANCE;

        private PacketCodeC packetCodeC;

        Singleton() {
            this.packetCodeC = new PacketCodeC();
        }

        public PacketCodeC getPacketCodeC() {
            return packetCodeC;
        }
    }

}
