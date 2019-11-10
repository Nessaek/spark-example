
val circeVersion = "0.11.1"

libraryDependencies ++= Seq(  "org.postgresql" % "postgresql" % "42.1.4",
  "org.apache.spark" %% "spark-sql" % "2.4.4"

) ++ Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
