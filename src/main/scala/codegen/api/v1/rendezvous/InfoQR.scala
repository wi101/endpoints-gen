package codegen.api.v1.rendezvous

import codegen.component._

object InfoQR {
  import zio.http._
  import zio.http.endpoint._
  import zio.http.codec._
  val get = Endpoint(Method.GET / "api" / "v1" / "rendezvous" / "infoQR")
    .query(HttpCodec.query[String]("path"))
    .in[Unit]
    .outError[Unit](status = Status.UnprocessableEntity)

}
