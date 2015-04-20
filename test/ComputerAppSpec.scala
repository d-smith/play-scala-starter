import models.ComputerInfo
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test.Helpers._
import play.api.test.{FakeHeaders, FakeRequest, WithApplication, PlaySpecification}
import play.api.libs.json.Json

@RunWith(classOf[JUnitRunner])
class ComputerAppSpec extends PlaySpecification {

  "Computer application" should {
    "return a 404 when asking for a computer that isn't known" in new WithApplication {
      route(FakeRequest(GET, "/computers/foo")) match {
        case Some(response) => status(response) must equalTo(NOT_FOUND)
        case None => failure
      }
    }

    "accept a computer definition posted to it" in new WithApplication {
      val compInfo = ComputerInfo("a-computer", "b")
      val compJson = Json.toJson(compInfo)
      val request = FakeRequest(
        POST,
        "/computers",
        FakeHeaders(
          Seq("Content-type" -> Seq("application/json"))
        ),
        compJson)

      route(request) match {
        case Some(response) => status(response) must equalTo(OK)
        case None => failure
      }


      route(FakeRequest(GET, "/computers/a-computer")) match {
        case Some(response) => status(response) must equalTo(OK)
        case None => failure
      }

      //TODO - make sure the comp we got back has the values we expect
    }


  }
}
