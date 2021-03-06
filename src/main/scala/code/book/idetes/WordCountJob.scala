package code.book.idetes

import org.apache.flink.api.scala._

object WordCountJob {
  def main(args: Array[String]) {
    // 1.设置运行环境
    val env = ExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(4)
    //2.创造测试数据
    val text = env.readTextFile("hdfs://qingcheng12:9000/input/spark/README.md")

    //3.进行wordcount运算
    val counts = text.flatMap(_.toLowerCase.split("\\W+"))
      .map((_, 1)).groupBy(0).sum(1)

    //4.打印测试结构
    counts.print()
  }
}