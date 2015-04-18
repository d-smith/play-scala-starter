import models.ComputerInfo
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.specs2.matcher._
import play.api.libs.json.Json

@RunWith(classOf[JUnitRunner])
class ComputersSpec extends Specification with JsonMatchers {
  "ComputerInfo" should {
    "Serialize as JSON" in  {
      val compInfo = ComputerInfo("a computer","b")
      val compJson = Json.toJson(compInfo)

      val name = compJson.\("name").as[String]
      name mustEqual "a computer"

    }
  }


}
