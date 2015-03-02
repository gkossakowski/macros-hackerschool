import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

object LoggingMacrosComplete {
  def log(str: String): Unit = macro logImpl
  def logImpl(c: Context)(str: c.Expr[String]): c.Expr[Unit] = {
    import c.universe._
    reify {
      if (DefaultLogger.isLogging) DefaultLogger.log(str.splice)
    }
  }

  def logAll(params: Any*): Unit = macro logAllImpl
  def logAllImpl(c: Context)(params: c.Expr[Any]*): c.Expr[Unit] = {
    import c.universe._
    def printParam(param: c.Expr[Any]): Tree = param.tree match {
      case Literal(_) => q"print($param)"
      case _ =>
        q"""print(${showCode(param.tree)} + " = " + $param)"""
    }
    val paramsPrinted = params map (x => printParam(x))
    val printSep = q"""print(", ")"""
    val allPrintStms = Util.separateElems(printSep, paramsPrinted.toList)
    c.Expr[Unit](q"""{
      ..$allPrintStms
      println()
    }"""
    )
  }

  def printCaseClass(x: Any): Unit = macro printCaseClassImpl
  def printCaseClassImpl(c: Context)(x: c.Expr[Any]): c.Expr[Unit] = {
    import c.universe._
    val tpe = x.tree.tpe
    val sym = tpe.typeSymbol
    // :-((
    def isCaseClass(s: Symbol): Boolean =
      s.asInstanceOf[scala.reflect.internal.Symbols#Symbol].isCaseClass
    if (!isCaseClass(sym)) {
      c.error(x.tree.pos, "the expression is not of a type that is a case class")
      c.Expr[Unit](q"()")
    } else {
      // :-((
      def isCaseAccessorMethod(s: Symbol): Boolean =
        s.asInstanceOf[scala.reflect.internal.Symbols#Symbol].isCaseAccessorMethod
      val caseClassParams = sym.info.decls.filter(isCaseAccessorMethod)
      def printCaseClassParam(s: Symbol): Tree = {
        val paramTpe = s.info.resultType.toString
        val paramName = s.name.toString
        q"""print($paramName + ": " + $paramTpe + " = " + x.${s.name.toTermName})"""
      }
      val printedCaseClassParams = caseClassParams.map(x => printCaseClassParam(x))
      val printStmts = Util.separateElems(q"""print(", ")""", printedCaseClassParams.toList)
      c.Expr[Unit](q"""{
        val x = $x
        print(${sym.name.toString} + "(")
        ..$printStmts;
        print(")")
        println()
      }""")
    }
  }
}
