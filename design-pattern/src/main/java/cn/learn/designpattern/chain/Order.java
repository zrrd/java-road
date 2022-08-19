package cn.learn.designpattern.chain;

/**
 * @author shaoyijiong
 * @date 2022/3/3
 */
public interface Order {

    default int getOrder() {
        return 0;
    }
}