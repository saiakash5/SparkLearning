package MovieLensDataset

import org.apache.spark.SparkContext

/**
  * Created by Akash on 8/24/17.
  */
object WordCount {

  def main( args : Array[String]): Unit =
  {
    val sc = new SparkContext("local[*]","Word Count")

    val book = sc.textFile("/Users/Akash/Documents/Main/Apache_Spark/SparkScala/book.txt")

    val array = Array("the","is","him","has","why","what","when","you","will","whom","not","there","then","them","in","so")

    val words = book.flatMap(x => x.split("\\W+"))

    val lower_words = words.map(x => x.toLowerCase())

    val filtered_words = lower_words.filter(x => (!(array.contains(x))) )

    val count = filtered_words.map(x => (x,1)).reduceByKey((x,y)=>x+y)

//    val count = lower_words.countByValue()
//
//    count.foreach(println)

    count.sortByKey(ascending = true).collect().foreach(println)
  }

}
