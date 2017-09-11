package MovieLensDataset

/**
  * Created by Akash on 8/22/17.
  */

import org.apache.spark.SparkContext

object friends_by_name {

  def parseline(line: String) ={
    val fields = line.split(",")
    val name = fields(1).toString
    val friends1 = fields(3).toInt

    (name, friends1)

  }

  def main(args : Array[String]): Unit =
  {
    val sc = new SparkContext("local","friends_name")

    val lines = sc.textFile("/Users/Akash/Documents/Main/Apache_Spark/SparkScala/fakefriends.csv")

    val rdd = lines.map(parseline)
    val result = rdd.mapValues(x =>(x,1)).reduceByKey((x,y) => (x._1+y._1, x._2+y._2))
    val friends = result.mapValues(x => x._1/x._2)

    friends.collect().sorted.foreach(println)

  }
}
