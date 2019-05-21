package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 18:37
  * @Description:DataStream → DataStream:输入一个参数，产生 0 个、1 个或者多个输出。
  */
object FlatMapSample {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.readTextFile("test00")
    val streamFlatMap = stream.flatMap { x => x.split("/n") }
    streamFlatMap.print()
    env.execute("First Job")
  }
}
