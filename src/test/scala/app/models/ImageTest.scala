package app.models

import app.models.pixel.RgbPixel
import org.mockito.ArgumentMatchers.{any, anyInt}
import org.mockito.Mockito.verify
import org.mockito.MockitoSugar.mock
import org.scalatest.FunSuite

class ImageTest extends FunSuite {

  private val pixelGridMock = mock[PixelGrid[RgbPixel]]
  private val image: Image[RgbPixel] = new Image[RgbPixel](pixelGridMock)

  // feature of transforming an image is already tested in PixelGridTest
  test("transform test - should call pixel grid") {
    image.transform(any())
    verify(pixelGridMock).transform(any())
  }

  // feature of getting a pixel at the given coordinates is already tested in PixelGridTest
  test("pixel at test - should call pixel grid") {
    image.pixelAt(0, 0)
    verify(pixelGridMock).at(anyInt(), anyInt())
  }
}
