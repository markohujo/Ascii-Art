package AsciiArtApp.ui

/**
 * Controller class used for processing user input
 *
 * @tparam T type of input
 */
trait Controller[T] {
  def processUserInput(input: T): Unit
}
