package nk.com.spark

import io.circe._
import io.circe.generic.semiauto._
import org.apache.spark.sql.{DataFrame, Encoders, SparkSession}


object Connector extends App {


  runJdbcDatasetExample()

  private def runJdbcDatasetExample(): Unit = {

    implicit val jsonEncoder = Encoders.product[Name]

    implicit val circeEncoder: ObjectEncoder[Name] = deriveEncoder[Name]


    val spark: SparkSession = SparkSession
      .builder()
      .appName("Spark SQL basic project")
      .config("spark.master", "local")
      .config("spark.driver.bindAddress", "127.0.0.1")
      .getOrCreate()

    val sqlContext = spark.sqlContext


    val jdbcDF = sqlContext.read
      .format("jdbc")
      .option("url", "jdbc:postgresql://localhost/sparkdb")
      .option("dbtable", "sparktb")
      .option("user", "postgres")
      .option("password", "root")
      .load()

    val query: DataFrame = jdbcDF.select("id", "first_name", "last_name")
    //As case class
    val test = query.as[Name].collectAsList()
print(test)
    //as json
    val test2 = query.toJSON.collectAsList()
print(test2)

    query.write.json("./random_json_file")


  }

}
