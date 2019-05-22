package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
/*
  * @Auther: peilongcheng
  * @Date: 2019/5/22 17:32
  * @Description: ConnectedStreams → DataStream:作用于上，功能与map和 flatMap 一样，对ConnectedStreams 中的每一个 Stream 分别进行 flatmap
  */
object CoFlatMapSample {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val stream1=env.readTextFile("test00")
    val stream2=env.readTextFile("test01")
    val streamConnect=stream1.connect(stream2)
    val streamFlatMap=streamConnect.flatMap(
      str=>str+"stream1",
      str=>str.split("\\s+")
    )
    streamFlatMap.print()
    env.execute("First Job")
  }
}
