package MovieLensDataset

import org.apache.spark.SparkContext

/**
  * Created by Akash on 8/25/17.
  */
object TotalAmountSpentByCustomer {

  def parseLine(line : String) ={

    val fields = line.split(",")

    val cust_id = fields(0).toInt
//    val item_id = fields(1).toInt
    val amt = fields(2).toFloat

    (cust_id,amt)
  }

  def main(args : Array[String]) ={

    val sc = new SparkContext("local[*]","AmountSpentByCustomer")

    val orders = sc.textFile("/Users/Akash/Documents/Main/Apache_Spark/SparkScala/customer-orders.csv")

    val values = orders.map(parseLine).reduceByKey((x,y)=>(x+y))

    values.sortByKey().collect().foreach(println)

  }

}
