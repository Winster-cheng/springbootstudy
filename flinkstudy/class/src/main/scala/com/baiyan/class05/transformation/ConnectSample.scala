package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 18:47
  * @Description:DataStream,DataStream → ConnectedStreams:连接两个保持他们类型的数据流， 两个数据流被 Connect 之后，只是被放在了一个同一个流中，内部依然保持各自的 数据和形式不发生任何变化，两个流相互独立。
  */
object ConnectSample {
  def main(args: Array[String]): Unit = {
    val env=StreamExecutionEnvironment.getExecutionEnvironment
    val streamText=env.readTextFile("test00")
    val streamList=env.generateSequence(1,10)

    val streamConnect=streamText.connect(streamList);

    streamConnect.map(item=>println("text:"+item), item=>println("list:"+item))

//    val streamConnect2 =streamConnect.map(x=>x,x=>x>3)

//    streamConnect2.map(x=>println(x))

    env.execute("First Strat")
  }
}
