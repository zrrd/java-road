package cn.learn.jvm.jmm;

/**
 * @author shaoyijiong
 * @date 2023/2/28
 */
public class JvmStack {

    /**
     * https://www.mashibing.com/study?courseNo=245&sectionNo=53597&systemId=1&activeIndex=1
     *
     *   Local Variable Table 局部变量表 下面main方法存在两个局部变量 序号0 args 序号1 i
     *
     *   Operand Stack 操作栈 用于进行栈操作
     *
     *
     *
     * 使用 jclasslib 工具查看栈的相关信息
     *
     *  0 bipush 8 (将int 8放到栈中) 
     *  2 istore_1 (将栈顶赋值给局部变量表中的序号1 ) 这两句话完成了 int i = 8
     *  3 iload_1  (将局部变量表中序号为1的数组放入栈中 这时候栈中为8)
     *  4 iinc 1 by 1 (将局部变量表中序号为1的局部变量加1 这时候局部变量表中为9)
     *  7 istore_1 (将栈顶赋值给局部变量表中的序号1 将栈中的8赋值给局部变量表中的1号位置)
     *  8 getstatic #2 <java/lang/System.out : Ljava/io/PrintStream;>
     * 11 iload_1
     * 12 invokevirtual #3 <java/io/PrintStream.println : (I)V>
     * 15 return
     */
    public static void main(String[] args) {
        int i = 8;
        i = i++;
        System.out.println(i);
    }
}