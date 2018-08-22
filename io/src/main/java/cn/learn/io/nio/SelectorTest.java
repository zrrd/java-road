package cn.learn.io.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector我们可以实现单线程操作多个channel
 *
 * @author 邵益炯
 * @date 2018/8/22
 */
public class SelectorTest {

  public static void main(String[] args) throws Exception {
    SocketChannel channel = SocketChannel.open();
    channel.connect(new InetSocketAddress("127.0.0.1", 8080));
    Selector selector = Selector.open();
    //为了同Selector挂了Channel，我们必须先把Channel注册到Selector上，这个操作使用SelectableChannel。register()
    //Channel必须是非阻塞的。所以FileChannel不适用Selector，因为FileChannel不能切换为非阻塞模式。Socket channel可以正常使用。
    //SelectionKey Connect Accept Read Write
    channel.configureBlocking(false);
    //向select注册channel
    SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
    while (true) {
      int readyChannels = selector.select();
      if (readyChannels == 0) {
        continue;
      }
      Set<SelectionKey> selectedKeys = selector.selectedKeys();
      Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
      while (keyIterator.hasNext()) {
        SelectionKey keyNext = keyIterator.next();
        if (keyNext.isAcceptable()) {
          // a connection was accepted by a ServerSocketChannel.
        } else if (keyNext.isConnectable()) {
          // a connection was established with a remote server.
        } else if (keyNext.isReadable()) {
          // a channel is ready for reading
        } else if (keyNext.isWritable()) {
          // a channel is ready for writing
        }
        keyIterator.remove();
      }
    }
  }
}
