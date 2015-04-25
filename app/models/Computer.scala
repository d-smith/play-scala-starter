package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Computer(name: String, manufacturer: String)



object Computer {
  implicit val implicitComputerInfoWrites = new Writes[Computer] {
    def writes(comp: Computer) : JsValue = {
      Json.obj(
        "name" -> comp.name,
        "manufacturer" -> comp.manufacturer
      )
    }
  }

  implicit val readsComputerInfo: Reads[Computer] = (
    ((__ \ "name").read[String]) and
      ((__ \ "manufacturer").read[String])
    )(Computer.apply _)

}
