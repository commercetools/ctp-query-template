
name := "ctp-query-template"
organization := "de.commercetools"

description := "CTP query platform template"

scalaVersion := "2.12.7"

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

testFrameworks += new TestFramework("com.github.agourlay.cornichon.framework.CornichonFramework")

libraryDependencies ++= Seq(
  "com.iheart" %% "ficus" % "1.4.4",
  "com.github.agourlay" %% "cornichon-test-framework" % "0.16.3" % Test
)