package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
/*
  * @Auther: peilongcheng
  * @Date: 2019/5/22 18:14
  * @Description:DataStream → DataStream:对两个或者两个以上的 DataStream 进行 union 操 作，产生一个包含所有 DataStream 元素的新
   * DataStream。注意:如果你将一个 DataStream 跟它自己做 union 操作，在新的 DataStream 中，你将看到每一个元素都 出现两次。
  */
object UnionSample {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val stream1=env.readTextFile("test00")
    val streamFlatMap1=stream1.flatMap(x=>x.split("\\s+"))
    val streamUnion=streamFlatMap1.union(streamFlatMap1)
    streamUnion.print()
    env.execute("First Job")

  }
}
