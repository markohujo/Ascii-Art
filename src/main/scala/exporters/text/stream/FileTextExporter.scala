package exporters.text.stream

import java.io.{File, FileOutputStream}

class FileTextExporter(path: String) extends StreamTextExporter(new FileOutputStream(new File(path))) {}
