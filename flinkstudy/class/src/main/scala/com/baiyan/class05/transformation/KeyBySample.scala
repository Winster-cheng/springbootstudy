package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/22 21:03
  * @Description:DataStream → KeyedStream:输入必须是 Tuple 类型，逻辑地将一个流拆分成
不相交的分区，每个分区包含具有相同 key 的元素，在内部以 hash 的形式实现的。
  */
object KeyBySample {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val stream=env.readTextFile("test00")
    val streamFlatMap=stream.flatMap(x=>x.split("\\s+"))
    val streamMap=streamFlatMap.map(x=>(x,1))
    //TODO KeyBy输入0和1有什么区别？
    //keyBy(0)表示以该tuple中第一个元素进行hash分区，所以keyBy(1)意味着以(x,1)中的1分区，都分到同一个区去了
    val streamByKey=streamMap.keyBy(1)
    streamByKey.print()
    env.execute("First Job")
  }
}
