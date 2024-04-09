package cn.learn.netty.im.packet.command;

import io.netty.buffer.ByteBuf;

/**
 * @author shaoyijiong
 * @date 2024/4/9
 */
public class TestCommand {

    public static void main(String[] args) {
        LoginRequest loginRequest = LoginRequest.newBuilder().setUserId(1L).setUsername("张三").setPassword("aa").build();
        ByteBuf encode = PacketCode.encode(CommandEnum.login, loginRequest);
        Packet decode = PacketCode.decode(encode);
        System.out.println(decode.getDate());
    }
}