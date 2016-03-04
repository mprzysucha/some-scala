/**
  * @author Michal Przysucha
  * @since 03.02.2016 17:44:00
  */
object ImplicitConversions {

  implicit def stringToBigDecimal(x: String): BigDecimal = BigDecimal.apply(x)

  //  implicit def stringToInteger(x: String): Integer = x.length

  def abc(b: BigDecimal) = b + 1

  def abc(b: Integer) = b + 2

  def main(args: Array[String]): Unit =
    println(abc("5"))

}
