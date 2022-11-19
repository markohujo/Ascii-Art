package exporters.text.stream

import java.io.{File, FileOutputStream}

class FileTextExporter(path: String) extends AbstractStreamTextExporter(new FileOutputStream(new File(path))) {}
