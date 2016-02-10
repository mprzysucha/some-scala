/**
  * @author Michal Przysucha
  * @since 04.02.2016 17:05:00
  */
object PatternMatching {

  def main(args: Array[String]): Unit = {
    printCapitalCity("Warsaw")
    printCapitalCity("Bratislava")
    printCapitalCity("Zurich")
  }

  def printCapitalCity(city: String) = println(city + " is capital city of " + capitalCitiesToCountries(city))

  def capitalCitiesToCountries(city: String): String = city match {
    case "Warsaw" => "Poland"
    case "Berlin" => "Germany"
    case "Bratislava" => "Slovakia"
    case _ => "... I don't know"
  }

}
