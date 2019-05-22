package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/22 17:19
  * @Description: ConnectedStreams → DataStream:作用于上，功能与map和 flatMap 一样，对ConnectedStreams 中的每一个 Stream 分别进行 map
  */
object CoMapSample {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val stream1=env.readTextFile("test00")
    val stream2=env.fromCollection(List(1,2,3,4))
    val streamConnect=stream1.connect(stream2)
    val streamCoMap=streamConnect.map(
      str => str+" connect",
      in => in+100
    )
    streamCoMap.print()
    env.execute("FirstJob")
  }
}
