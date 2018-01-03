name := "ctp-query-template"
organization := "de.commercetools"

description := "CTP query platform template"

scalaVersion := "2.12.4"

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
  "com.iheart" %% "ficus" % "1.4.3",
  "com.github.agourlay" %% "cornichon" % "0.14.2" % Test
)