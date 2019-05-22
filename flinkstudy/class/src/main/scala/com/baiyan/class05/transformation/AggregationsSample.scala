package com.baiyan.class05.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
/*
  * @Auther: peilongcheng
  * @Date: 2019/5/22 21:22
  * @Description:KeyedStream → DataStream:分组数据流上的滚动聚合操作。min 和 minBy 的 区别是 min 返回的是一个最小值，而 minBy
  * 返回的是其字段中包含最小值的元素(同 样原理适用于 max 和 maxBy)，返回的流中包含每一次聚合的结果，而不是只返回 最后一次聚合的最终结果。
  */
object AggregationsSample {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.readTextFile("test02").map(item => (item.split("\\s+")(0), item.split("\\s+")(1).toLong)).keyBy(0)
    val streamReduce = stream.max(1)
    streamReduce.print()
    env.execute("FirstJob")
  }
}
