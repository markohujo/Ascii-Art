package app.importers

trait Importer[T] {
  /**
   * Loads object of type T and returns it
   *
   * @return Loaded object of type T
   */
  def load: T
}
