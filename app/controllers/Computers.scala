package controllers

import play.api.mvc.{Controller, Action}
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

object Computers extends Controller {
  val list = Action {
      val comp = ComputerInfo("sparcstation 1", "sun")
      val comp2 = ComputerInfo("mac book", "apple")
      val compList = List(comp, comp2)
      Ok(Json.toJson(compList))
  }
  
  def details(name: String) = Action {
      val comp = ComputerInfo(name, "AcmeComputer")
      Ok(Json.toJson(comp))
  }
  
  def create = Action(parse.json) { implicit request => {
      request.body.validate[ComputerInfo] match {
          case JsSuccess(createComp,_) =>
            println(createComp)
            Ok(Json.toJson(createComp))
        case JsError(errors) =>
            println(errors)
            BadRequest
      }
  }
  }
}