package example.api.v1

import example.component._

object Rendezvous{
import zio.http._
import zio.http.endpoint._
import zio.http.codec._
val post=Endpoint(Method.POST / "api" / "v1" / "rendezvous")
  
  
  .in[CreateParticipantRequest]
  .out[Unit](status = Status.Created)
  


}