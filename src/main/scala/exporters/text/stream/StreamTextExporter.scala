package exporters.text.stream

import exporters.text.TextExporter

import java.io.OutputStream

class StreamTextExporter(stream: OutputStream) extends TextExporter {
  private var closed: Boolean = false

  override def save(item: String): Unit = exportToStream(item)

  protected def exportToStream(str: String): Unit = {
    if (closed)
      throw new Exception("Stream is closed.")

    stream.write(str.getBytes("UTF-8"))
    stream.flush()
  }

  def close(): Unit = {
    stream.close()
    closed = true
  }
}
