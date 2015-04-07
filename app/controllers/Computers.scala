package controllers

import play.api.mvc.{Controller, Action}
import play.api.libs.json.Json

object Computers extends Controller {
  val list = Action {
    Ok(Json.obj(
        "name" -> "Sun Sparcstation",
        "manufacturer" -> "Sun"
    ))      
  }
}