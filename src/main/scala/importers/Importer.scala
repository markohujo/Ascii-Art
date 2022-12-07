package importers

/**
 * @tparam T type of object that is loaded by this imported
 */
trait Importer[T] {
  /**
   * Loads object of type T and returns it
   *
   * @return Loaded object of type T
   */
  def load: T
}
