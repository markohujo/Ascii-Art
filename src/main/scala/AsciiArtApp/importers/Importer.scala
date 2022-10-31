package AsciiArtApp.importers

trait Importer[T] {
  def load: T
}
