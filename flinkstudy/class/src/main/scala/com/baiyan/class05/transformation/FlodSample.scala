package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/22 21:19
  * @Description:KeyedStream → DataStream:一个有初始值的分组数据流的滚动折叠操作， 合并当前元素和前一次折叠操作的结果，并产生一个新的
  * 值，返回的流中包含每一次折叠的结果，而不是只返回最后一次折叠的最终结果。
  */
object FlodSample {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.readTextFile("test01").flatMap(item => item.split("" +
      "\\s+")).map(item => (item, 1)).keyBy(0)
    val streamReduce = stream.fold(100)( (begin, item) => (begin + item._2)
    )
    streamReduce.print()
    env.execute("FirstJob")
  }
}
