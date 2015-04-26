package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Computer(name: String, manufacturer: String)

trait ComputerCatalog {
  def list: Iterable[Computer]

  def create(name: String, manufacturer: String) : Option[Computer]

  def get(name: String) : Option[Computer]

}

object InMemoryCatalog extends ComputerCatalog {
  val computerMap = scala.collection.mutable.HashMap.empty[String, Computer]

  override def list: Iterable[Computer] = computerMap.values.seq

  override def get(name: String): Option[Computer] = computerMap.get(name)

  override def create(name: String, manufacturer: String): Option[Computer] =
      computerMap.put(name, Computer(name, manufacturer))
  
}

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
