###mybatis二级缓存

参考文章：https://www.jianshu.com/p/f375c3bc675f?nomobile=yes
http://www.mybatis.org/mybatis-3/zh/sqlmap-xml.html

####MyBatis虽然全局配置开启缓存，但是还是取决于是否使用了<cache/>标签，如果使用了二级缓存，需要注意：
每个<cache />代表一个单独的二级缓存，如果多个Mapper需要共享同一个二级缓存，就需要使用<cache-ref/>如果一个Mapper中查询数据时，使用了多表联查，当另一个Mapper更新相关数据时，如果没有共享一个Cache，那么下一次该Mapper查询时，就会出现读到脏数据。

####使用二级缓存一般基于以下原则：
    不经常变动的数据，但经常会使用
    数据量比较大，系统多处会用到。或者跨系统用。
    对性能有特别要求的地方。
    滥用二级缓存，有可能反而会降低性能，特别是根据条件查询缓存。
    
