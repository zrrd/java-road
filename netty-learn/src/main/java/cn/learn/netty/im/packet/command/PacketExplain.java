package cn.learn.netty.im.packet.command;

/**
 * 一个包的整体定义 并没有实际用处
 *
 * @author shaoyijiong
 * @date 2024/4/9
 */
public interface PacketExplain {

    int MAGIC_NUMBER = 0x20240409;


    byte[] MAGIC_NUMBER_BYTE = new byte[]{0x20, 0x24, 0x04, 0x09};
    /**
     * 版本号 默认1
     */
    byte version = 1;

    /**
     * 指令
     */
    byte getCommand();

    /**
     * 消息长度
     */
    Integer getLength();

    byte[] data();

}