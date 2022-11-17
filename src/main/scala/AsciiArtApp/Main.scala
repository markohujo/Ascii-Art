package AsciiArtApp

import AsciiArtApp.ui.console.ConsoleController


object Main extends App {
  try {
    val executor = new Executor
    val controller = new ConsoleController(executor)
    controller.processUserInput(args)
  } catch {
    case exception: IllegalArgumentException => println(exception.getMessage)
  }
}
