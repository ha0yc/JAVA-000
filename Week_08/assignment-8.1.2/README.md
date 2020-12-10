# 使用指引
启动Application类里的main方法即可。

# 踩坑总结
1. shardingsphere的官方文档给的配置适用于spring boot 1.x,建议能做一个说明，同时尽快升级到spring boot 2.x。

2. sharding-jdbc的starter配置方式学习成本有点高，文档把所有配置项放到一个页面，容易混淆，建议ss细化文档。

3. 由于mapper.xml自动生成格式不规范，在insert时生成sql被换行Parameter index out of range (1 > number of parameters, which is 0).，导致报错。手动修改mapper.xml解决问题

4. 真心建议修改ss的examples，如果是一个fresh man，确实很难读。

5. 配置好了之后ss确实很方便，只需要配置mybatis的datasource就可以了，对业务代码无侵入，点个赞。