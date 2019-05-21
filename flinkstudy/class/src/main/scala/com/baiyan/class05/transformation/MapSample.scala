package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 18:33
  * @Description:DataStream → DataStream:输入一个参数产生一个参数。
  */
object MapSample {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val stream=env.generateSequence(1,10)
    val streamMap =stream.map(x=>x*2)
    streamMap.print()

    env.execute("FirstJob")
  }
}
