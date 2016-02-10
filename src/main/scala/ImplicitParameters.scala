/**
  * @author Michal Przysucha
  * @since 03.02.2016 17:39:00
  */
object ImplicitParameters {

  def func1(implicit a: Long, b: Int) = println(a, b)

  def func2(a: Int*)(implicit b: Long) = println(a, b)

  def func3(implicit a: Int*) = println(a)

  def func4(implicit a: Seq[Int]) = println(a)

  def main(args: Array[String]): Unit = {
    implicit val a1 = 100L
    implicit val a2 = 10
    implicit val a3: Seq[Int] = Array(10, 20)
    func1
    func2(1, 2, 3)
    // func3 // can't do that
    func4
  }

}
