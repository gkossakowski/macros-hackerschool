trait Logger {
  def isLogging: Boolean
  def log(str: String): Unit
}

object DefaultLogger extends Logger {
  def isLogging: Boolean = true
  def log(str: String): Unit = println(str)
}
