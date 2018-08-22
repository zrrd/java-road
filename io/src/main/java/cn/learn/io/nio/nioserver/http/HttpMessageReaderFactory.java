package cn.learn.io.nio.nioserver.http;

import cn.learn.io.nio.nioserver.IMessageReader;
import cn.learn.io.nio.nioserver.IMessageReaderFactory;


/**
 * Created by jjenkov on 18-10-2015.
 */
public class HttpMessageReaderFactory implements IMessageReaderFactory {

    public HttpMessageReaderFactory() {
    }

    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
