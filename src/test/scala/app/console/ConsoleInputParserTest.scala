package app.console

import org.scalatest.FunSuite

class ConsoleInputParserTest extends FunSuite {

  test("parser test 1") {
    val args = Array("--input", "\"filePath\"", "--invert", "--output-console")
    val parser = new ConsoleInputParser(args)
    val result = parser.parse()
    assert(result.size == 3)
    assert(result.head == "--input \"filePath\"")
    assert(result(1) == "--invert")
    assert(result(2) == "--output-console")
  }

  test("parser test 2") {
    val args = Array("--input", "\"filePath\"", "--invert", "--output-console", "--brightness", "-50")
    val parser = new ConsoleInputParser(args)
    val result = parser.parse()
    assert(result.size == 4)
    assert(result.head == "--input \"filePath\"")
    assert(result(1) == "--invert")
    assert(result(2) == "--output-console")
    assert(result(3) == "--brightness -50")
  }

  test("parser test 3") {
    val args = Array("--input-random")
    val parser = new ConsoleInputParser(args)
    val result = parser.parse()
    assert(result.size == 1)
    assert(result.head == "--input-random")
  }

}
