object Test extends App {
  val foo: Int = 2
  def bar(x: Int) = x*3
  //LoggingMacros.logAll("my variables", foo, bar(5))
  //LoggingMacros.log("some expensive computation" + bar(2))
  case class Foo(x: Int, y: String)
  class Bar(x: Int)
  LoggingMacros.printCaseClass(Foo(23, "abc"))
  //LoggingMacros.printCaseClass(new Bar(12))
}
