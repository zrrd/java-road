package cn.learn.designpattern.chain_of_responsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链
 *
 * @author 邵益炯
 * @date 2018/10/29
 */
public class MsgProcessChain {

  private List<Process> chains = new ArrayList<>();

  /**
   * 添加责任链
   */
  public MsgProcessChain addChain(Process process) {
    chains.add(process);
    return this;
  }

  /**
   * 执行处理
   */
  public void process(String msg) {
    for (Process process : chains) {
      process.doProcess(msg);
    }
  }
}
