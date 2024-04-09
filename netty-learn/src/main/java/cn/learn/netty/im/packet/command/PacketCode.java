package cn.learn.netty.im.packet.command;

import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static cn.learn.netty.im.packet.command.PacketExplain.MAGIC_NUMBER;
import static cn.learn.netty.im.packet.command.PacketExplain.MAGIC_NUMBER_BYTE;

/**
 * @author shaoyijiong
 * @date 2024/4/9
 */
public class PacketCode {

    private static final Map<Byte, Class<?>> packetTypeMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(CommandEnum.login.command, LoginRequest.class);

    }

    public static ByteBuf encode(CommandEnum commandEnum, MessageLite messageLite) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(PacketExplain.version);
        byteBuf.writeByte(commandEnum.command);
        byte[] byteArray = messageLite.toByteArray();
        byteBuf.writeInt(byteArray.length);
        byteBuf.writeBytes(byteArray);
        return byteBuf;
    }

    public static Packet decode(ByteBuf byteBuf) {
        // 校验魔数
        byte[] magicNumber = new byte[4];
        byteBuf.readBytes(magicNumber);
        if (!Arrays.equals(magicNumber, MAGIC_NUMBER_BYTE)) {
            throw new RuntimeException("不支持的协议");
        }
        byte version = byteBuf.readByte();
        if (version != PacketExplain.version) {
            throw new RuntimeException("不支持的协议版本");
        }
        byte command = byteBuf.readByte();
        CommandEnum commandEnum = CommandEnum.asCommandEnum(command);
        if (commandEnum == null) {
            throw new RuntimeException("不支持的指令");
        }
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<?> clazz = packetTypeMap.get(command);
        if (clazz != null) {
            MessageLite messageLite = null;
            try {
                messageLite = (MessageLite) clazz.getMethod("parseFrom", byte[].class).invoke(null, bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Packet(commandEnum, messageLite);
        }
        return null;
    }

    public static void main(String[] args) {
        int magicNumber = MAGIC_NUMBER;
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (magicNumber >> 24);
        bytes[1] = (byte) (magicNumber >> 16);
        bytes[2] = (byte) (magicNumber >> 8);
        bytes[3] = (byte) magicNumber;
        byte[] magicNumberByte = MAGIC_NUMBER_BYTE;
        System.out.println(Arrays.equals(bytes, magicNumberByte));

    }


}