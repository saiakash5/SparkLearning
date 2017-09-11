package MovieLensDataset



import org.apache.spark.SparkContext

import scala.math.min
/**
  * Created by Akash on 8/23/17.
  */
object Min_temp
{

  def parseLine(line : String)=
  {
    val fields = line.split(",")
    val place = fields(0).toString
    val par = fields(2).toString
    val temp = fields(3).toFloat*0.1f*(9.0f/5.0f)+32.0f

    (place,par,temp)
  }

  def main(args : Array[String]): Unit =
  {
    val sc = new SparkContext("local[*]","Min_temp")

    val values = sc.textFile("/Users/Akash/Documents/Main/Apache_Spark/SparkScala/1800.csv")

    val lines = values.map(parseLine)
    val filter_lines = lines.filter(x=>x._2=="TMIN")
    val key_values = filter_lines.map(x=> (x._1,x._3.toFloat))

    val min_temp = key_values.reduceByKey((x,y)=>min(x,y))

    min_temp.collect().sorted.foreach(println)


  }

}
