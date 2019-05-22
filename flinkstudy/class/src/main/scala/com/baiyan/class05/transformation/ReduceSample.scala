package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/22 21:11
  * @Description:KeyedStream → DataStream:一个分组数据流的聚合操作，合并当前的元素 和上次聚合的结果，产生一个新的值，返回的流中包含
  * 每一次聚合的结果，而不是 只返回最后一次聚合的最终结果。
  */
object ReduceSample {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val keyByStream=env.readTextFile("test01").flatMap(x=>x.split("\\s+")).map(x=>(x,1)).keyBy(0)
    //TODO wordcount,keyBy究竟是什么意思
    //注意，在keyBy以后，每个key只包含一个word
    val reduceStream=keyByStream.reduce((x,y)=>(x._1,x._2+y._2))
    //注意在不引入窗口的情况下，每部操作都会被记录下来
    reduceStream.print()
    env.execute("First Job")
  }
}
