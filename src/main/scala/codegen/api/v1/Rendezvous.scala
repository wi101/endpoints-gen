package codegen.api.v1

import codegen.component._

object Rendezvous {
  import zio.http._
  import zio.http.endpoint._
  import zio.http.codec._
  val post = Endpoint(Method.POST / "api" / "v1" / "rendezvous")
    .in[CreateParticipantRequest]
    .out[Unit](status = Status.Created)

}
