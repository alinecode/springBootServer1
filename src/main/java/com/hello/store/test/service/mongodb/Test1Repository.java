//package com.hello.store.test.service.mongodb;
//
//import java.util.Date;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//
//import com.hello.store.test.entity.Test1;
//
///**
// * 继承MongoRepository这种方式方式本项目已弃用。
// * @see com.hello.store.test.service.mongodb.MonController
// * 
// * 参考资料
// * https://spring.io/guides/gs/accessing-data-mongodb/
// * https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference
// * https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
// * https://docs.spring.io/spring-data/mongodb/docs/2.0.5.RELEASE/reference/html/ 主要
// */
////@Repository
//public interface Test1Repository extends MongoRepository<Test1, Long> {
//	
////    /** 
////     * 根据名称模糊查询 
////     * @param name 
////     * @return 
////     */
////    List<Test1> findByNameLike(String name);
//    
//    
////    @Query(value="{'tid':?0,'title':{'$regex':?1,'$options':'i'},createTime':{'$gt':?2,'$lt':?3}}")  
////    public Page<T> query(String tid,String title,Date beginTime,Date endTime, Pageable pageable);  
//    
////    @Query(value="{'name':?0}")  
////    public Page<T> query(String name,String age,Date beginTime,Date endTime, Pageable pageable);  
//    /**
//     * 可用 根据方法名称自动拼接
//     * @param name
//     * @param pageable
//     * @return
//     */
//    public Page<Test1> findByNameLike(String name, Pageable pageable);  
//    
//    /**
//     * 使用@Query  可用，但时间有问题
//     * bug https://stackoverflow.com/questions/56862718/making-date-queries-on-mongodb-using-json-on-springdatamongodb
//     * @param name
//     * @param sdate
//     * @param sdate2
//     * @param pageable
//     * @return
//     */
////    @Query(value="{'name':?0,\"creatdata\":{$gt:'?1',$lt:'?2'}}")
////    public Page<Test1> findByNameLike(String name,Date sdate,Date sdate2,Pageable pageable);  
//    
//    
//    
//    
//}