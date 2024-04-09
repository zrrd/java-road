package cn.learn.netty.im.packet.command;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author shaoyijiong
 * @date 2024/4/9
 */
@AllArgsConstructor
@Data
public class Packet {

    private CommandEnum commandEnum;
    private Object date;
}