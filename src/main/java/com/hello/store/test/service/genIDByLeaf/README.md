
使用美团leaf的id生成方式，生成数字id，使用号段方式，减少数据库查询：

1、概览：github地址： https://github.com/Meituan-Dianping/Leaf/blob/feature/spring-boot-starter/README_CN.md

2、使用方式：leaf-starter 可以使用maven仓库的：
```
	<!-- https://mvnrepository.com/artifact/com.tencent.devops.leaf/leaf-boot-starter -->
	<dependency>
	    <groupId>com.tencent.devops.leaf</groupId>
	    <artifactId>leaf-boot-starter</artifactId>
	    <version>1.0.1-RELEASE</version>
	</dependency>
```
把它放到pom.xml中

3.1、在application.properties中 按概览中的信息填进去数据库信息内容。使用号段模式（还提供snowflake的生成方式，请参考概览文档），所以 leaf.segment.enable=true  完整示例如下：
```
leaf.name=com.sankuai.leaf.opensource.test
leaf.segment.enable=true
leaf.segment.url=jdbc:mysql://192.168.9.120:3306/leaf?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8
leaf.segment.username=root
leaf.segment.password=root
```
3.2、在对应的数据库中（比如此处的leaf数据库），按照概览的脚本生成数据库表并插入一条示例数据，但是注意使用maven仓库中的starter时，表名字需要修改成 t_leaf_alloc

4、在springboot的main类中加上 @EnableLeafServer 注解

5、在需要生成id的service中，注入工具类：

	@Autowired
    private SegmentService segmentService;

6、生成id：↓，括号中就是biz_tag的字段值，要用哪个就写哪个。

		Result id = segmentService.getId("leaf-segment-test");
		long id2 = id.getId();

7、测试： http://127.0.0.1:9999/store-service/genidTest/1


8、相关文章：
https://tech.meituan.com/2017/04/21/mt-leaf.html
https://tech.meituan.com/2019/03/07/open-source-project-leaf.html
https://github.com/Meituan-Dianping/Leaf/blob/feature/spring-boot-starter/README_CN.md

