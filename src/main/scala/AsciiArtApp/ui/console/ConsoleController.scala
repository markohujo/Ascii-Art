package AsciiArtApp.ui.console

import AsciiArtApp.ui.Controller

class ConsoleController extends Controller[Array[String]] {

  override def processUserInput(input: Array[String]): Unit = {
    input.foreach(println)
    if (input.isEmpty)
      throw new IllegalArgumentException("No arguments were given by the user.")

    if (input.count(_.startsWith("--image")) == 0)
      throw new IllegalArgumentException("No --image argument specified.")

    if (input.count(_.startsWith("--image")) > 1)
      throw new IllegalArgumentException("More than 1 --image* argument specified.")

    // TODO throw if unsupported image extension

    sortArguments(parseInput(input)).foreach(execute)
  }

  private def parseInput(input: Array[String]): Seq[String] = {
    ???
  }

  private def sortArguments(arguments: Seq[String]): Seq[String] = {
    ???
  }

  private def execute(argument: String): Unit = {
    ???

    // TODO arguments could be case classes ???
    // TODO pattern matching ???
  }

}
