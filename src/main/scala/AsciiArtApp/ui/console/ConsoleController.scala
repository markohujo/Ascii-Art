package AsciiArtApp.ui.console

import AsciiArtApp.ui.Controller

class ConsoleController extends Controller[Array[String]] {

  override def processUserInput(input: Array[String]): Unit = {
    if (input.isEmpty)
      throw new IllegalArgumentException("No arguments were given by the user.")

    sortArguments(parseInput(input)).foreach(executeArgument)
  }

  private def parseInput(input: Array[String]): Seq[String] = {
    ???
  }

  private def sortArguments(arguments: Seq[String]): Seq[String] = {
    ???
  }

  private def executeArgument(argument: String): Unit = {
    ???

    // TODO arguments could be case classes ???
    // TODO pattern matching ???
  }

}
