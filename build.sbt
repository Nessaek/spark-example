name := "spark"

version := "0.1"

scalaVersion := "2.12.8"


lazy val `spark` =
  project.in(file("."))
    .aggregate(`spark-job`)


lazy val `spark-job` =  project in file("spark-job")