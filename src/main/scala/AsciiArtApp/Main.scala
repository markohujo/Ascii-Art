package AsciiArtApp

import AsciiArtApp.ui.console.ConsoleController


object Main extends App {
  new ConsoleController().processUserInput(args)
  Executor.run()
}
