package chiseltest.backends.icarus

import chisel3.{UInt, fromIntToLiteral}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.{times, verify}
import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite

import scala.util.Random

trait IcarusBlackBoxTrait {
  def add(a: UInt, b: UInt, q: UInt): Unit
}
class TestBlackBox extends AnyFunSuite with MockFactory {
  val aCapture = new ArgumentCaptor[UInt]
  val bCapture = new ArgumentCaptor[UInt]
  val qCapture = new ArgumentCaptor[UInt]
  val blackBoxMock = mock[IcarusBlackBoxTrait]

  test("black box should call the add method") {
    verify(blackBoxMock, times(1)).add(aCapture.capture(), bCapture.capture(), qCapture.capture())
  }
}









