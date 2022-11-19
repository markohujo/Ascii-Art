package app.ui.console

/**
 * @param args - command line arguments given by the user
 */
class ConsoleInputParser(args: Seq[String]) {

  /**
   * Parses the given input (command line arguments) into a sequence of arguments of this app
   * @return sequence of app arguments
   */
  def parse(): Seq[String] = {
    var result = Seq.empty[String]
    var argument: Option[String] = Option.empty

    for (arg <- args) {
      if (arg.startsWith("--")) {
        if (argument.isDefined)
          result = result.appended(argument.get)
        argument = Option.apply(arg)
      }
      else {
        argument = Option.apply(argument.get.appendedAll(" ").appendedAll(arg))
      }
    }

    result.appended(argument.get)
  }

}
