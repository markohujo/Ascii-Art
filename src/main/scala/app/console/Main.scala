package app.console

import app.facades.ImageFacade

object Main extends App {
  try {
    val imageFacade = new ImageFacade
    val inputHandler = new ConsoleUserInputHandler(args.toList, imageFacade)
    inputHandler.processUserInput()
  } catch {
    case exception: IllegalArgumentException => println(exception.getMessage)
  }
}
