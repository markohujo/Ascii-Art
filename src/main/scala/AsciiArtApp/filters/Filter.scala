package AsciiArtApp.filters

trait Filter[T] {
  def filter(item: T): T
}
