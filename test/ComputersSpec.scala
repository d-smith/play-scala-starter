import models.{InMemoryCatalog, Computer}
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
      val compInfo = Computer("a computer","b")
      val compJson = Json.toJson(compInfo)

      compJson.\("name").as[String] mustEqual "a computer"
      compJson.\("manufacturer").as[String] mustEqual("b")

    }
  }

  //TODO - how to order tests
  "ComputerCatalog" should {

    "return None when retrieving a computer not in the list" in {
      InMemoryCatalog.clearOutAllCatalogRecords()
      val fooComp = InMemoryCatalog.get("foo")
      fooComp must beEqualTo(None)
    }

    "return an empty list when not items are not in the catalog" in {
      InMemoryCatalog.clearOutAllCatalogRecords()
      val computers = InMemoryCatalog.list
      computers.size must beEqualTo(0)
    }


   "return an option containing created object" in {
     val computer = InMemoryCatalog.create("foo", "foo maker")
     computer match {
       case Some(c) =>
         c.name must beEqualTo("foo")
         c.manufacturer must beEqualTo(("foo maker"))
       case None => ko("None returned when Some expected")
     }

     "return a list containing the created computers" in {
       InMemoryCatalog.create("bar", "bar maker")
       InMemoryCatalog.create("foo", "foo maker")
       val computers = InMemoryCatalog.list
       computers.size must beEqualTo(2)
       computers.exists(_.name == "bar") must beEqualTo(true)
       computers.exists(_.name == "foo") must beEqualTo(true)
     }

   }

 }

}
