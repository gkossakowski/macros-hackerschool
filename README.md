# Macros presented for Hacker Schoolers

Three macros that I was live coding in front of awesome Hacker school crowd:

  * `log` - simple logging utility that shows how to use `reify` macro. Probably
     one of the simplest macros that does something useful.
  * `logAll` - a logging macro that logs all variables passed to it. Both names
     of passed variables and their values are logged. This macro shows how
     to use quasiquotations to construct trees that cannot be expressed in valid
     Scala (so we can't simply use reify)
  * `printCaseClass` - pretty prints value of a type that is a case class. This
    macro shows how to inspect types and symbols to extract information that
    is not readily available in trees

## Running

You need [`sbt`](http://www.scala-sbt.org/) installed to run the project. Once
installed, just type `sbt` and then in the shell call `run`.

Whem your code is being compiled, you'll see that Scala compiler prints trees
that it compiles. This way you can easily inspect how expansion of particular
macro looks like exactly.
