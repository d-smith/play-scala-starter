package controllers

import play.api.mvc.{Controller, Action}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.{InMemoryCatalog, ComputerCatalog, Computer}



object Computers extends Controller {

  val computerCatalog: ComputerCatalog = new InMemoryCatalog
    

  val list = Action {
      Ok(Json.toJson(computerCatalog.list))
  }
  
  def details(name: String) = Action {
      computerCatalog.get(name) match {
          case Some(computerInfo) => Ok(Json.toJson(computerInfo))
          case None => NotFound(s"No such computer: $name")
      }
      
  }
  
  def create = Action(parse.json) { implicit request => {
      request.body.validate[Computer] match {
          case JsSuccess(createComp,_) =>
            println(createComp)
            computerCatalog.create(createComp.name, createComp.manufacturer)
            Ok(Json.toJson(createComp))
        case JsError(errors) =>
            println(errors)
            BadRequest
      }
  }
  }
}