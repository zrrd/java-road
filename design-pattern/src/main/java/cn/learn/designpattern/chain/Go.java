package cn.learn.designpattern.chain;

/**
 * @author 邵益炯
 * @date 2018/10/29
 */
public class Go {

  public static void main(String[] args) {
    String msg = "加链接弗利萨,的沙坑里发生了";

    //添加责任链
    MsgProcessChain chain = new MsgProcessChain().addChain(new SensitiveWordProcess())
        .addChain(new CopyrightProcess());
    chain.process(msg);
  }
}
