package com.baiyan.class05.DataSource.fromcollection

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 18:23
  * @Description:从一个给定的对象序列中创建一个数据流，所有的对象必须是相同类型的。
  */
object FromElements {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val list=List(1,2,3,4)
    val stream=env.fromElements(list)
    stream.print()
    env.execute("First Job")
  }
}
