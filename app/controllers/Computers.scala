package controllers

import play.api.mvc.{Controller, Action}
import play.api.libs.json._

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
}

object Computers extends Controller {
  val list = Action {
      val comp = ComputerInfo("sparcstation 1", "sun")
      Ok(Json.toJson(comp))
  }
  
  def details(name: String) = Action {
      val comp = ComputerInfo(name, "AcmeComputer")
      Ok(Json.toJson(comp))
  }
}