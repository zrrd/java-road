package cn.learn.algorithm.others;

import java.util.Map;

/**
 * 动态规划走楼梯的简单应用
 *
 * @author 邵益炯
 * @date 2018/9/25
 */
public class DynamicPlanning {

  /**
   * 问题描述  走n阶段楼梯  每次可以走1阶或2阶 有几种走法 并打印
   */

  // 1.递归：时间复杂度 2^n
  private static int plan1(int n) {
    if (n < 1) {
      return 0;
    }

    if (n == 1) {
      return 1;
    }

    if (n == 2) {
      return 2;
    }

    return plan1(n - 1) + plan1(n - 2);
  }

  //2.备忘录算法：时间复杂度n
  private static int plan2(int count, Map<Integer,Integer> map) {
    if (count<1){
      return 0;
    }
    if (count<=2){
      return count;
    }
    if(map.containsKey(count)){
      return map.get(count);
    }else {
      int value=plan2(count-1,map)+plan2(count-2,map);
      map.put(count,value);
      return value;
    }
  }



  public static void main(String[] args) {
    System.out.println(plan1(10));
  }
}
