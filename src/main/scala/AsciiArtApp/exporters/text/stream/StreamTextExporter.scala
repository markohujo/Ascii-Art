package AsciiArtApp.exporters.text.stream

import AsciiArtApp.exporters.text.TextExporter

import java.io.OutputStream

abstract class StreamTextExporter(stream: OutputStream) extends TextExporter {
  private var closed: Boolean = false

  override def export(item: String): Unit = exportToStream(item)

  def close(): Unit = {
    stream.close()
    closed = true
  }

  protected def exportToStream(str: String): Unit = {
    if (closed)
      throw new Exception("Stream is closed.")

    stream.write(str.getBytes("UTF-8"))
    stream.flush()
  }
}
