name := "ctp-query-template"
organization := "de.commercetools"

description := "CTP query platform template"

scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-encoding", "UTF-8",
  "-Ywarn-dead-code",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-feature",
  "-Ywarn-unused-import"
)

libraryDependencies ++= Seq(
  "net.ceedubs" %% "ficus" % "1.1.2",
  "com.github.agourlay" %% "cornichon" % "0.7.2" % "test"
)

// Comment for // execution
//parallelExecution in Test := false