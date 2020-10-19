Classfile /Users/haoyongchen/Documents/code/JAVA-000/Week_01/assignment01/com/haoyc/Hello.class
  Last modified 2020-10-19; size 639 bytes
  MD5 checksum dbb34b4bca7175bac0fff2fed4ca6db8
  Compiled from "Hello.java"
public class com.haoyc.Hello
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #8.#20         // java/lang/Object."<init>":()V
   #2 = Double             0.1d
   #4 = Float              1.23f
   #5 = Fieldref           #21.#22        // java/lang/System.out:Ljava/io/PrintStream;
   #6 = Methodref          #23.#24        // java/io/PrintStream.println:(I)V
   #7 = Class              #25            // com/haoyc/Hello
   #8 = Class              #26            // java/lang/Object
   #9 = Utf8               <init>
  #10 = Utf8               ()V
  #11 = Utf8               Code
  #12 = Utf8               LineNumberTable
  #13 = Utf8               main
  #14 = Utf8               ([Ljava/lang/String;)V
  #15 = Utf8               StackMapTable
  #16 = Class              #27            // "[Ljava/lang/String;"
  #17 = Class              #28            // "[I"
  #18 = Utf8               SourceFile
  #19 = Utf8               Hello.java
  #20 = NameAndType        #9:#10         // "<init>":()V
  #21 = Class              #29            // java/lang/System
  #22 = NameAndType        #30:#31        // out:Ljava/io/PrintStream;
  #23 = Class              #32            // java/io/PrintStream
  #24 = NameAndType        #33:#34        // println:(I)V
  #25 = Utf8               com/haoyc/Hello
  #26 = Utf8               java/lang/Object
  #27 = Utf8               [Ljava/lang/String;
  #28 = Utf8               [I
  #29 = Utf8               java/lang/System
  #30 = Utf8               out
  #31 = Utf8               Ljava/io/PrintStream;
  #32 = Utf8               java/io/PrintStream
  #33 = Utf8               println
  #34 = Utf8               (I)V
{
  public com.haoyc.Hello();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=4, locals=13, args_size=1
         0: bipush        10
         2: istore_1
         3: iconst_2
         4: istore_2
         5: ldc2_w        #2                  // double 0.1d
         8: dstore_3
         9: iconst_0
        10: istore        5
        12: ldc           #4                  // float 1.23f
        14: fstore        6
        16: iload_1
        17: iload_2
        18: iadd
        19: istore        7
        21: iload_1
        22: iload_2
        23: isub
        24: istore        8
        26: iload_1
        27: iload_2
        28: idiv
        29: istore        9
        31: iload_1
        32: iload_2
        33: imul
        34: istore        10
        36: iload         7
        38: iload         8
        40: if_icmple     51
        43: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
        46: iload         7
        48: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
        51: iconst_4
        52: newarray       int
        54: dup
        55: iconst_0
        56: iconst_1
        57: iastore
        58: dup
        59: iconst_1
        60: iconst_2
        61: iastore
        62: dup
        63: iconst_2
        64: iconst_3
        65: iastore
        66: dup
        67: iconst_3
        68: iconst_4
        69: iastore
        70: astore        11
        72: iconst_0
        73: istore        12
        75: iload         12
        77: aload         11
        79: arraylength
        80: if_icmpge     100
        83: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
        86: aload         11
        88: iload         12
        90: iaload
        91: invokevirtual #6                  // Method java/io/PrintStream.println:(I)V
        94: iinc          12, 1
        97: goto          75
       100: return
      LineNumberTable:
        line 5: 0
        line 6: 3
        line 8: 5
        line 9: 9
        line 10: 12
        line 12: 16
        line 13: 21
        line 14: 26
        line 15: 31
        line 17: 36
        line 18: 43
        line 21: 51
        line 22: 72
        line 23: 83
        line 22: 94
        line 25: 100
      StackMapTable: number_of_entries = 3
        frame_type = 255 /* full_frame */
          offset_delta = 51
          locals = [ class "[Ljava/lang/String;", int, int, double, int, float, int, int, int, int ]
          stack = []
        frame_type = 253 /* append */
          offset_delta = 23
          locals = [ class "[I", int ]
        frame_type = 250 /* chop */
          offset_delta = 24
}
SourceFile: "Hello.java"
