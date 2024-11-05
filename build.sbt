ThisBuild / scalaVersion     := "2.13.15"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "endpoints-gen",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"          % "2.1.11",
      "dev.zio" %% "zio-json"     % "0.7.3",
      "dev.zio" %% "zio-http"     % "3.0.1",
      "dev.zio" %% "zio-http-gen" % "3.0.1",
      "dev.zio" %% "zio-test"     % "2.1.11" % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
