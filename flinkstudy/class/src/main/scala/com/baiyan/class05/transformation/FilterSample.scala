package com.baiyan.class05.transformation
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 18:42
  * @Description:
  */
object FilterSample {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val stream=env.generateSequence(1,10)
    val streamFilter=stream.filter(
      x=>x>3
    )
    streamFilter.print()
    env.execute("First Job")
  }
}
