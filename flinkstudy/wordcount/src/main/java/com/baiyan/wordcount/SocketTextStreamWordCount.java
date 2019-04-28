package com.baiyan.wordcount;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @Auther: peilongcheng
 * @Date: 2019/3/26 16:02
 * @Description:
 */
public class SocketTextStreamWordCount {


    public static void main(String[] args) throws Exception {
        //参数检查
        if (args.length != 2) {
            System.err.println("USAGE:\nSocketTextStreamWordCount <hostname> <port>");
            return;
        }

        String hostName = args[0];
        Integer port = Integer.parseInt(args[1]);

        //flink程序的第一步是创建一个StreamExecutionEnvironment (或者是ExecutionEnvironment，如果你编写的是一个batch批处理job)。这可以用于设置执行参数和可以设置一个输入源来从外部系统读取数据。
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //创建一个source输入源
        DataStream<String> text = env.socketTextStream(hostName, port);

        //计数
        DataStream<Tuple2<String, Integer>> counts = text.flatMap(new LineSplitter())
                .keyBy(0)
                .sum(1);
        counts.print();

        env.execute("Java WordCount from SocketTextStream Example");

    }


    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) {
            String[] tokens = s.toLowerCase().split("\\W+");

            for (String token: tokens) {
                if (token.length() > 0) {
                    collector.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }
}