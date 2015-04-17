package controllers

import play.api.mvc.{Controller, Action}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.ComputerInfo



object Computers extends Controller {
    
  val computerMap = scala.collection.mutable.HashMap.empty[String, ComputerInfo]    
    
  val list = Action {
      Ok(Json.toJson(computerMap.values.toList))
  }
  
  def details(name: String) = Action {
      computerMap.get(name) match {
          case Some(computerInfo) => Ok(Json.toJson(computerInfo))
          case None => NotFound(s"No such computer: $name")
      }
      
  }
  
  def create = Action(parse.json) { implicit request => {
      request.body.validate[ComputerInfo] match {
          case JsSuccess(createComp,_) =>
            println(createComp)
            computerMap += (createComp.name -> createComp)
            Ok(Json.toJson(createComp))
        case JsError(errors) =>
            println(errors)
            BadRequest
      }
  }
  }
}