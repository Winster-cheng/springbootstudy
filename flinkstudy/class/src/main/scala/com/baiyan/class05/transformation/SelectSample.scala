package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import  org.apache.flink.api.scala._
/*
  * @Auther: peilongcheng
  * @Date: 2019/5/22 17:59
  * @Description:SplitStream→DataStream:从一个 SplitStream 中获取一个或者多个 DataStream。
  */
object SelectSample {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val stream=env.readTextFile("test00")
    val streamFlatMap=stream.flatMap(x=>x.split("\\s+"))
    val streamSplit=streamFlatMap.split(
      num =>
        (num.equals("hello")) match {
          case true => List("hello")
          case false => List("other")
        })
    val hello=streamSplit.select("hello")
    val other=streamSplit.select("other")
    other.print()
    env.execute("First Job")
  }
}
