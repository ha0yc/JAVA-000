Compiled from "Hello.java"
public class com.haoyc.Hello {
  public com.haoyc.Hello(); //构造函数
    Code:
       0: aload_0 //加载this 参数
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V 调用父类构造方法
       4: return  //返回

  public static void main(java.lang.String[]);
    Code:
       0: bipush        10 //当 int 取值 -128~127 （10）时，JVM 采用 bipush 指令将常量压入栈中。
       2: istore_1         //栈顶int数值存入第2局部变量
       3: iconst_2         //当int取值-1-5时，使用iconst入栈
       4: istore_2         //存入第3变量
       5: ldc2_w        #2 // 是一个 long integer 或 double 数字。               // double 0.1d
       8: dstore_3         //存入第四变量，类型是double
       9: iconst_0         //入栈第一变量 boolean
      10: istore        5  //存储第一变量 boolean
      12: ldc           #4 //入栈float变量                // float 1.23f
      14: fstore        6 //存储float变量
      16: iload_1          //读取int变量2
      17: iload_2          //读取int变量3
      18: iadd             //执行int +操作
      19: istore        7  //存储加操作结果
      21: iload_1          
      22: iload_2
      23: isub             //减法
      24: istore        8
      26: iload_1
      27: iload_2
      28: idiv             //除法
      29: istore        9
      31: iload_1
      32: iload_2
      33: imul             //乘法
      34: istore        10
      36: iload         7
      38: iload         8
      40: if_icmple     51 //if比较
      43: getstatic     #5 //调用System.out获取静态对象               // Field java/lang/System.out:Ljava/io/PrintStream;
      46: iload         7
      48: invokevirtual #6 //调用out.println                 // Method java/io/PrintStream.println:(I)V
      51: iconst_4         //
      52: newarray       int //定义int数组
      54: dup            //复制栈顶数值并将复制值压入栈顶
      55: iconst_0 
      56: iconst_1       //
      57: iastore        //它用于给一个int数组的给定索引赋值，将栈顶的1存放到数组的0索引的位置
      58: dup
      59: iconst_1
      60: iconst_2
      61: iastore        //将栈顶的2存放到数组的1索引的位置
      62: dup
      63: iconst_2
      64: iconst_3
      65: iastore        //将栈顶3存放到数组的2索引的位置
      66: dup
      67: iconst_3
      68: iconst_4
      69: iastore       //将栈顶的4存放到数组的3索引的位置
      70: astore        11//存储数组
      72: iconst_0
      73: istore        12//存储0索引
      75: iload         12
      77: aload         11
      79: arraylength     //获取数组长度
      80: if_icmpge     100 //大小比较
      83: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
      86: aload         11
      88: iload         12
      90: iaload
      91: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
      94: iinc          12, 1 //索引i自增
      97: goto          75    //跳转到75行
     100: return
}
