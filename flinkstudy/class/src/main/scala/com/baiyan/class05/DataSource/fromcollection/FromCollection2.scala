package com.baiyan.class05.DataSource.fromcollection

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 18:04
  * @Description:从迭代(Iterator)中创建一个数据流，指定元素数据类型的类由 iterator 返回。
  */
object FromCollection2 {
    def main(args: Array[String]): Unit = {
        val env = StreamExecutionEnvironment.getExecutionEnvironment
        val iter = Iterator(1, 2, 3, 4)
        val stream = env.fromCollection(iter)
        stream.print()
        env.execute("FirstJob")
    }
}