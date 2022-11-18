package app

import app.ui.console.ConsoleController


object Main extends App {
  try {
    val executor = new Executor
    val controller = new ConsoleController(executor, args)
    controller.processUserInput()
  } catch {
    case exception: IllegalArgumentException => println(exception.getMessage)
  }
}
