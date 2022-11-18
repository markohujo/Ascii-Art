package app.filters

trait Filter[T] {
  def apply(item: T): T
}
