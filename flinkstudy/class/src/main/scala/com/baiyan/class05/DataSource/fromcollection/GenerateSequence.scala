package com.baiyan.class05.DataSource.fromcollection

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 18:25
  * @Description:从给定的间隔中并行地产生一个数字序列。
  */
object GenerateSequence {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val stream=env.generateSequence(1,10)
    stream.print()
    env.execute("First Job")
  }
}
