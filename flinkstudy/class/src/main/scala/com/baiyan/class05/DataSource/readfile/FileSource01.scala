package com.baiyan.class05.DataSource.readfile

import org.apache.flink.api.java.io.TextInputFormat
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

/*
  * @Auther: peilongcheng
  * @Date: 2019/5/21 17:26
  * @Description:基于文件读取的两种方式 readTextFile/readFile
  */
object FileSource01 {
    def main(args: Array[String]): Unit = {
      val env= StreamExecutionEnvironment.getExecutionEnvironment
      //路径什么都不指定，默认在当前路径的根目录下
      val stream=env.readTextFile("test00")
      stream.print()
      env.execute("FirstJob")
      println("----------------Second Job-----------------")
      val env2=StreamExecutionEnvironment.getExecutionEnvironment
      val path=new Path("test01");
      //TODO 这里为什么要输入2次相同的路径,目前认为前者只是负责读取格式
      val stream2=env2.readFile(new TextInputFormat(path),"test00");
      stream2.print()
      env2.execute("SecondJob")
    }

}
