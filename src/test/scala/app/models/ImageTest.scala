package app.models

import app.models.pixel.RGBPixel
import org.mockito.ArgumentMatchers.{any, anyInt}
import org.mockito.Mockito.verify
import org.mockito.MockitoSugar.mock
import org.scalatest.FunSuite

class ImageTest extends FunSuite {

  private val pixelGridMock = mock[PixelGrid[RGBPixel]]
  private val image: Image[RGBPixel] = new Image[RGBPixel](pixelGridMock)

  test("transform test should call pixel grid") {
    image.pixelAt(0, 0)
    verify(pixelGridMock).at(anyInt(), anyInt())
  }

  test("pixel at test should call pixel grid") {
    image.transform(any())
    verify(pixelGridMock).transform(any())
  }
}
