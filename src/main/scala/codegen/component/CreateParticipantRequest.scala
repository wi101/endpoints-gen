package codegen.component

import zio.schema._

case class CreateParticipantRequest(
  name: Option[String],
  email: Option[String]
)
object CreateParticipantRequest {
  implicit val codec: Schema[CreateParticipantRequest] = DeriveSchema.gen[CreateParticipantRequest]
}
