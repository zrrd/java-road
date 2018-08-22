package cn.learn.io.nio.nioserver;

/**
 * Created by jjenkov on 16-10-2015.
 */
public interface IMessageProcessor {

  void process(Message message, WriteProxy writeProxy);

}
