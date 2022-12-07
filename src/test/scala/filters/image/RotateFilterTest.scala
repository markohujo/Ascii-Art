package filters.image

import app.models.pixel.AsciiPixel
import app.models.{Image, PixelGrid}
import filters.image.ascii.RotateImageFilter
import org.scalatest.FunSuite

class RotateFilterTest extends FunSuite {

  private val asciiImage = Image(PixelGrid(Seq(
    Seq(AsciiPixel('A'), AsciiPixel('B'), AsciiPixel('C')),
    Seq(AsciiPixel('D'), AsciiPixel('E'), AsciiPixel('F')),
  )))

  test("test rotate 0") {
    val filter = new RotateImageFilter(0)
    val rotatedImage = filter.apply(asciiImage)
    assert(asciiImage == rotatedImage)
  }

  test("test rotate image 90") {
    val filter = new RotateImageFilter(90)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.width && rotatedImage.width == asciiImage.height)
    assert90(rotatedImage)
  }

  test("test rotate image -270") {
    val filter = new RotateImageFilter(-270)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.width && rotatedImage.width == asciiImage.height)
    assert90(rotatedImage)
  }

  test("test rotate image 450") {
    val filter = new RotateImageFilter(450)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.width && rotatedImage.width == asciiImage.height)
    assert90(rotatedImage)
  }

  test("test rotate 180") {
    val filter = new RotateImageFilter(180)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.height && rotatedImage.width == asciiImage.width)
    assert180(rotatedImage)
  }

  test("test rotate -180") {
    val filter = new RotateImageFilter(-180)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.height && rotatedImage.width == asciiImage.width)
    assert180(rotatedImage)
  }

  test("test rotate 540") {
    val filter = new RotateImageFilter(540)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.height && rotatedImage.width == asciiImage.width)
    assert180(rotatedImage)
  }

  test("test rotate 270") {
    val filter = new RotateImageFilter(270)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.width && rotatedImage.width == asciiImage.height)
    assert270(rotatedImage)
  }

  test("test rotate -90") {
    val filter = new RotateImageFilter(-90)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.width && rotatedImage.width == asciiImage.height)
    assert270(rotatedImage)
  }

  test("test rotate 630") {
    val filter = new RotateImageFilter(630)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.width && rotatedImage.width == asciiImage.height)
    assert270(rotatedImage)
  }

  private def assert90(rotatedImage: Image[AsciiPixel]): Unit = {
    assert(rotatedImage.pixelAt(0, 0).value == 'D')
    assert(rotatedImage.pixelAt(0, 1).value == 'A')
    assert(rotatedImage.pixelAt(1, 0).value == 'E')
    assert(rotatedImage.pixelAt(1, 1).value == 'B')
    assert(rotatedImage.pixelAt(2, 0).value == 'F')
    assert(rotatedImage.pixelAt(2, 1).value == 'C')
  }

  private def assert180(rotatedImage: Image[AsciiPixel]): Unit = {
    assert(rotatedImage.pixelAt(0, 0).value == 'F')
    assert(rotatedImage.pixelAt(0, 1).value == 'E')
    assert(rotatedImage.pixelAt(0, 2).value == 'D')
    assert(rotatedImage.pixelAt(1, 0).value == 'C')
    assert(rotatedImage.pixelAt(1, 1).value == 'B')
    assert(rotatedImage.pixelAt(1, 2).value == 'A')
  }

  private def assert270(rotatedImage: Image[AsciiPixel]): Unit = {
    assert(rotatedImage.pixelAt(0, 0).value == 'C')
    assert(rotatedImage.pixelAt(0, 1).value == 'F')
    assert(rotatedImage.pixelAt(1, 0).value == 'B')
    assert(rotatedImage.pixelAt(1, 1).value == 'E')
    assert(rotatedImage.pixelAt(2, 0).value == 'A')
    assert(rotatedImage.pixelAt(2, 1).value == 'D')
  }
}
