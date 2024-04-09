package cn.learn.netty.im.packet.command;

/**
 * @author shaoyijiong
 * @date 2024/4/9
 */
public enum CommandEnum {
    login((byte) 1);

    byte command;

    CommandEnum(Byte command) {
        this.command = command;
    }

    public static CommandEnum asCommandEnum(byte command) {
        for (CommandEnum value : values()) {
            if (value.command == command) {
                return value;
            }
        }
        return null;
    }
}