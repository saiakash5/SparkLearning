package MovieLensDataset

import org.apache.spark.SparkContext

/**
  * Created by Akash on 8/25/17.
  */
object PopularMovie {

  def parseline(line: String)={

    val fields = line.split("\t")

    val movie_id = fields(1).toInt



    (movie_id)
  }

  def main(args : Array[String])={

    val sc = new SparkContext("local[*]","Popular Movie")

    val data = sc.textFile("/Users/Akash/Documents/Main/Apache_Spark/ml-100k/u.data")

    val values = data.map(parseline).map(x=>(x,1)).reduceByKey((x,y)=>(x+y))

    val sorted_movie = values.map(x=>(x._2,x._1))

    sorted_movie.sortByKey(ascending = false).collect().foreach(println)


  }
}
