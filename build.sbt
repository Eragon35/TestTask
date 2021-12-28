name := "AntipinTestTask"

version := "0.1"

scalaVersion := "2.12.8"

val http4sVersion = "1.0.0-M21"
val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "io.circe" %% "circe-generic" % circeVersion
)
