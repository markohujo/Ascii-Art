package filters

/**
 * @tparam T type of object that is filtered by this filter
 */
trait Filter[T] {
  /**
   * Applies this given filter to the given item
   * @param item item to filter
   * @return filtered item
   */
  def apply(item: T): T
}
