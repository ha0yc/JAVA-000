# 线上系统环境
系统：suse 12 sp5 4C4G
JDK：IBM JDK 1.8
运行系统：
    容器：Liberty
    应用服务：spring boot + dubbo + spring mvc


# JVM参数
-XX:MaxPermSize=256m 
-Xms1024M
-Xmx2048M
-Xverbosegclog:/path/gc.log,10,10000

# 参数分析
最大永久代大小设置为256M，初始堆大小为1024M，最大堆大小为2048M。为操作系统运行和其他监控服务等预留了1.5G空间，满足系统运行需要。生产环境运行稳定，线上并发较少，单容器在10qps左右。