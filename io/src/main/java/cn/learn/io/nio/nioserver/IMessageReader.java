package cn.learn.io.nio.nioserver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by jjenkov on 16-10-2015.
 */
public interface IMessageReader {

  void init(MessageBuffer readMessageBuffer);

  void read(Socket socket, ByteBuffer byteBuffer) throws IOException;

  List<Message> getMessages();
}
