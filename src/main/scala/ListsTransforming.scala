/**
  * @author Michal Przysucha
  * @since 04.02.2016 17:11:00
  */
object ListsTransforming {

  def main(args: Array[String]): Unit = {
    val listInts = List(1, 2, 23, 24, 182, 27, -123, -42, 74, 23487, -324)
    val listAll = List("Warsaw", 123, BigDecimal("3.1415927"), 77L, "Helo world", BigInt(Int.MaxValue) * BigInt(Int.MaxValue) * BigInt(Int.MaxValue) * BigInt(Int.MaxValue))

    println(listInts)
    println(listAll)

    println(listInts filter (_ * 3 % 7 == 0) map (_.toDouble) map (_ / 5))

    println(listAll flatMap (elem => elem match {
      case x: BigInt => Some(x)
      case x: BigDecimal => Some(x)
      case _ => None
    }))

    // the match collapsed
    println(listAll flatMap {
      case x: BigInt => Some(x)
      case x: BigDecimal => Some(x)
      case _ => None
    })
  }

}
