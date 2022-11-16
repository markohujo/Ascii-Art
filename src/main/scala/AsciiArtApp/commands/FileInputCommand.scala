package AsciiArtApp.commands

import AsciiArtApp.importers.image.rgb.input.FileInputRGBImageImporter

case class FileInputCommand(path: String) extends Command {
  override def execute(): Unit = {
    val image = new FileInputRGBImageImporter(path).load
    // TODO
  }
}
