package com.baiyan.class05.DataSource.fromcollection

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 17:57
  * @Description:从集合中创建一个数据流，集合中所有元素的类型是一致的。
  */
object FromCollection1 {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val list=List(1,2,3,4)
    val stream=env.fromCollection(list)
    stream.print()
    env.execute("FirstJob")
  }
}
