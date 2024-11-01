import zio._
import zio.http.codec.HttpCodec.Path
import zio.http.endpoint.openapi.OpenAPI
import zio.http.gen.openapi.EndpointGen
import zio.http.gen.scala.CodeGen

import java.nio.file.{Files, Paths}
import scala.io.Source

object Main extends ZIOAppDefault {

  def readJsonFileAsString(filePath: String): String = {
    val source = Source.fromInputStream(getClass.getResourceAsStream(filePath))
    try source.getLines().mkString("\n")
    finally source.close()
  }
  override def run = {
    for {
      json <- ZIO.attempt(readJsonFileAsString("/api.json"))
      openApi <- ZIO.fromEither(OpenAPI.fromJson(json))
      scala <- ZIO.attempt(EndpointGen.fromOpenAPI(openApi))
      directory <- ZIO.attempt(Files.createDirectories(Paths.get("src")))
      _ <- ZIO.attempt(CodeGen.writeFiles(scala,java.nio.file.Paths.get(directory.toString, "main", "scala"), "example", None)).debug("codegen")
    } yield()
  }
}
