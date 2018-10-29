package cn.learn.design_pattern.chain_of_responsibility;

/**
 * 责任链模式接口
 *
 * @author 邵益炯
 * @date 2018/10/29
 */
public interface Process {

  /**
   * 执行处理
   *
   * @param msg 过滤的信息
   */
  void doProcess(String msg);
}
