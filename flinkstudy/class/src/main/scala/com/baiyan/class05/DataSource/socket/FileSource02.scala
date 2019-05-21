package com.baiyan.class05.DataSource.socket

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 17:38
  * @Description:基于 Socket 的数据源，通过nc -l 启动端口
  */
object FileSource02 {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val socketStream=env.socketTextStream("localhost",11111)
    socketStream.print()
    env.execute("First Job!!")
  }
}
