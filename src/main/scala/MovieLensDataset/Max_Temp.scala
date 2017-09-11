package MovieLensDataset

import org.apache.spark.SparkContext
/**
  * Created by Akash on 8/23/17.
  */
object Max_Temp {

  def parseLine(line : String)=
  {
    val fields = line.split(",")
    val date = fields(1).toInt
    val parameters = fields(2).toString
    val prcp_values = fields(3).toInt

    (date,parameters,prcp_values)
    //18000101,PRCP,0
  }

  def main(args : Array[String]): Unit =
  {
    val sc = new SparkContext("local[*]","Max_temp")

    val values = sc.textFile("/Users/Akash/Documents/Main/Apache_Spark/SparkScala/1800.csv")

    val lines = values.map(parseLine)
    val filter_lines = lines.filter(x=>x._2=="PRCP")
    val key_values = filter_lines.map(x=> (x._3,x._1))

    val min_temp = key_values.sortByKey(ascending = false).collect()

    min_temp.foreach(println)


  }

}
