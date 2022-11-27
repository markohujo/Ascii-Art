package app.console

import app.facades.ImageFacade

object Main extends App {
  try {
    val imageFacade = new ImageFacade
    val controller = new ConsoleUserInputHandler(args.toList, imageFacade)
    controller.processUserInput()
  } catch {
    case exception: IllegalArgumentException => println(exception.getMessage)
  }
}
