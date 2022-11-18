package app.filters

trait Filter[T] {
  def filter(item: T): T
}
