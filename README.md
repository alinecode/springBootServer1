常见springboot框架搭建

以下按代码顺序的排序，主要功能代码的位置、实现过程与注意事项一般都在代码的类注释中。需要引入的jar在pom中也有注释。

1、注解的实现

com.hello.store.test.annotationLog

2、AOP使用

/springbootTest/src/main/java/com/hello/store/test/configTest/AspectConfig.java

3、获取配置文件中的配置

/springbootTest/src/main/java/com/hello/store/test/configTest/StaticValueInj.java

4、常见controller

/springbootTest/src/main/java/com/hello/store/test/contoller/Hello.java

5、beetlSql的dao

/springbootTest/src/main/java/com/hello/store/test/dao/UserAccountDao.java

6、代码生成器

com.hello.store.test.gen

7、redis集成
/springbootTest/src/main/java/com/hello/store/test/redis/UserRedis.java

8、自动定时器使用

/springbootTest/src/main/java/com/hello/store/test/schedule/TestScheduled.java

9、oauth2使用

com.hello.store.test.security 里面两个包分别是资源和授权服务器的实现

10、swagger配置

/springbootTest/src/main/java/com/hello/store/test/swagger/SwaggerConfig.java

11、二叉树生成与遍历

/springbootTest/src/main/java/com/hello/store/test/service/algorithm/BinTree.java

12、集合类之Map的使用

/springbootTest/src/main/java/com/hello/store/test/service/algorithm/MapAl.java

13、冒泡排序和快速排序

/springbootTest/src/main/java/com/hello/store/test/service/algorithm/MapAl.java

14、异步方法的使用

com.hello.store.test.service.async

15、下载文件测试

/springbootTest/src/main/java/com/hello/store/test/service/downloadFile/DownloadController.java

16、前后端Excel表格交互使用

com.hello.store.test.service.excel

17、执行数据库存储过程

com.hello.store.test.service.excutePROCEDURE

18、美团开源的leaf快速使用

com.hello.store.test.service.genIDByLeaf

19、http网络请求

com.hello.store.test.service.httpsend

20、webservice模拟请求与相应

com.hello.store.test.webservice

21、MongoDB使用

com.hello.store.test.service.mongodb

22、PDF模板生成的使用

com.hello.store.test.service.pdf

23、rabbit使用与测试

com.hello.store.test.service.rabbitMQ

24、一般情况下的service、controller的使用

com.hello.store.test.service.userAccount

25、webSocket服务端使用

com.hello.store.test.webSocket





