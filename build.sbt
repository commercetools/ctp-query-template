
name := "ctp-query-template"
organization := "de.commercetools"

description := "CTP query platform template"

scalaVersion := "2.13.3"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-encoding", "UTF-8",
  "-Ywarn-dead-code",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-feature"
)

testFrameworks += new TestFramework("com.github.agourlay.cornichon.framework.CornichonFramework")

libraryDependencies ++= Seq(
  "com.iheart" %% "ficus" % "1.4.7" % Test,
  "com.github.agourlay" %% "cornichon-test-framework" % "0.19.2" % Test
)
