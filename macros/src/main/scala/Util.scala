object Util {
  /**
   * Inserts `sep` element between consecutive pairs of elements of `col`.
   * Examples:
   *
   *   separateElems("q", Nil) == Nil
   *   separateElems("q", List("a")) == List("a")
   *   separateElems("q", List("a", "b")) == List("a", "q", "b")
   *   separateElems("q", List("a", "b", "c", "d")) == List("a", "q", "b", "q", "c", "q", "d")
   */
  def separateElems[T](sep: T, col: List[T]): List[T] = col match {
    case Nil | _ :: Nil => col
    case head :: tail => head :: sep :: separateElems(sep, tail)
  }
}
