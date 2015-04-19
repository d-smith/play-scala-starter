import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication, PlaySpecification}

@RunWith(classOf[JUnitRunner])
class ComputerAppSpec extends PlaySpecification {

    "Computer application" should {
      "return a 404 when asking for a computer that isn't known" in new WithApplication {
        route(FakeRequest(GET, "/computers/foo")) match {
          case Some(response) => status(response) must equalTo(NOT_FOUND)
          case None => failure
        }
      }
    }
}
