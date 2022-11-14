package AsciiArtApp

import AsciiArtApp.ui.console.ConsoleController


object Main extends App {
  val consoleController = new ConsoleController
  consoleController.processUserInput(args)
}
