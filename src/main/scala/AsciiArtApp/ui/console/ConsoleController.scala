package AsciiArtApp.ui.console

import AsciiArtApp.ui.Controller

class ConsoleController extends Controller[Array[String]] {

  override def processUserInput(input: Array[String]): Unit = {
    println(input.length)
    input.foreach(println)
  }

}
