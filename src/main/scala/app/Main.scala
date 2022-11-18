package app

import app.facades.ImageFacade
import app.ui.console.ConsoleController


object Main extends App {
  try {
    val imageFacade = new ImageFacade
    val controller = new ConsoleController(args, imageFacade)
    controller.processUserInput()
  } catch {
    case exception: IllegalArgumentException => println(exception.getMessage)
  }
}
