package example.api.v1

import example.component._

object Health{
import zio.http._
import zio.http.endpoint._
import zio.http.codec._
val get=Endpoint(Method.GET / "api" / "v1" / "health")
  
  
  .in[Unit]
  .out[Unit](status = Status.Ok)
  


}