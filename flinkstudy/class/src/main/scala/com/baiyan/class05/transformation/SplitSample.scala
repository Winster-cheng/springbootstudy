package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
/*
  * @Auther: peilongcheng
  * @Date: 2019/5/22 17:35
  * @Description:DataStream → SplitStream:根据某些特征把一个 DataStream 拆分成两个或者,和
多个 DataStream。
  */
object SplitSample {
  def main(args: Array[String]): Unit = {
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
      env.execute("First Job")
    }
  }
}
