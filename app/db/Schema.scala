package db


object Schema {
  val queryLanguage = scala.slick.driver.H2Driver.simple
  import queryLanguage._
  import scala.slick.lifted.{Tag, TableQuery}

  class Computers(tag: Tag) extends Table[(Long,String,String)](tag,"computers") {
    val id = column[Long]("id", O.AutoInc)
    val name = column[String]("name")
    val manufacturer = column[String]("manufacturer")
    override def * = (id,name,manufacturer)
  }
}
