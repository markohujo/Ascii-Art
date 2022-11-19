package exporters

import exporters.text.stream.StreamTextExporter
import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

class StreamTextExporterTest extends FunSuite {

  test("export") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)
    val text = "Saved item"
    exporter.save(text)
    assert(stream.toString("UTF-8") == text)
  }

  test("closed stream") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)
    exporter.close()
    assertThrows[Exception] {
      exporter.save("")
    }
  }

}
