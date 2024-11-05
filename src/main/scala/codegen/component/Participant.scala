package codegen.component

import zio.schema._
import java.util.UUID

case class Participant(
  id: Option[UUID],
  name: Option[String],
  email: Option[String]
)
object Participant {
  implicit val codec: Schema[Participant] = DeriveSchema.gen[Participant]
}
