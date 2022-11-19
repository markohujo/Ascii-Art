package utils.correctors

trait Corrector[T] {
  /**
   *
   * @param item - item of type T to correct
   * @return corrected item of type T
   */
  def corrected(item: T): T
}
