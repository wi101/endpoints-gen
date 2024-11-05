import zio._
import zio.http.codec.HttpCodec.Path
import zio.http.endpoint.openapi.OpenAPI
import zio.http.gen.openapi.EndpointGen
import zio.http.gen.scala.CodeGen

import java.nio.file.{Files, Paths}
import scala.io.Source

object Main extends ZIOAppDefault {

  def readJsonFileAsString(filePath: String): Task[String] = for {
    source <- ZIO.attempt(Source.fromInputStream(getClass.getResourceAsStream(filePath)))
    res    <- ZIO.attempt(source.getLines().mkString("\n")).ensuring(ZIO.succeed(source.close()))
  } yield res

  override def run =
    for {
      json                        <- readJsonFileAsString("/api.json")
      scalaFmt                    <- ZIO.attempt(java.nio.file.Paths.get(".scalafmt.conf"))
      openApi                     <- ZIO.fromEither(OpenAPI.fromJson(json))
      endpointsAndComponentsFiles <- ZIO.attempt(EndpointGen.fromOpenAPI(openApi))
      directory                   <- ZIO.attempt(Files.createDirectories(Paths.get("src")))
      _                           <- ZIO
                                       .attempt(
                                         CodeGen.writeFiles(
                                           endpointsAndComponentsFiles,
                                           java.nio.file.Paths.get(directory.toString, "main", "scala", "codegen"),
                                           "codegen",
                                           Some(scalaFmt)
                                         )
                                       )
                                       .debug("codegen")
    } yield ()
}
