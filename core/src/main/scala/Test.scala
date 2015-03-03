object Test extends App {
  def foo(x: Int): Int = x+2
  val bla = "abc"
  // uncomment for bad things to happen
  // def print(x: Any): Unit = println("Launching missiles")

  // LoggingMacros.log("some msg " + foo(25))

  // LoggingMacros.logAll("12", foo(25), bla)

  // case class Foo(x: Int, y: String)
  // val myFoo = Foo(71, "abc")
  // LoggingMacros.printCaseClass(myFoo)
}
