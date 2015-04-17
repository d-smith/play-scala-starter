package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class ComputerInfo(name: String, manufacturer: String)

object ComputerInfo {
  implicit val implicitComputerInfoWrites = new Writes[ComputerInfo] {
    def writes(comp: ComputerInfo) : JsValue = {
      Json.obj(
        "name" -> comp.name,
        "manufacturer" -> comp.manufacturer
      )
    }
  }

  implicit val readsComputerInfo: Reads[ComputerInfo] = (
    ((__ \ "name").read[String]) and
      ((__ \ "manufacturer").read[String])
    )(ComputerInfo.apply _)

}
