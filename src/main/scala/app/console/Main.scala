package app.console

import app.facades.ImageFacade

object Main extends App {
  try {
    val imageFacade = new ImageFacade
    val controller = new ConsoleController(args, imageFacade)
    controller.processUserInput()
  } catch {
    case exception: IllegalArgumentException => println(exception.getMessage)
  }
}
