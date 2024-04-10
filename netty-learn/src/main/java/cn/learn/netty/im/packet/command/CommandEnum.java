package cn.learn.netty.im.packet.command;

/**
 * 命令枚举
 *
 * @author shaoyijiong
 * @date 2024/4/9
 */
public enum CommandEnum {
    login((byte) 1),

    login_rsp((byte) 2),

    message((byte) 3),
    message_rsp((byte) 4);

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