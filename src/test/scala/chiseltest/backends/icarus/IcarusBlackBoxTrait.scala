package chiseltest.backends.icarus

import chisel3.{Bundle, IO, Input, Output, UInt, fromIntToWidth}
import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.mockito.MockitoSugar.mock

trait IcarusBlackBoxTrait {
    val io: Bundle = IO(new Bundle {
        val a: UInt = Input(UInt(8.W))
        val b: UInt = Input(UInt(8.W))
        val q: UInt = Output(UInt(8.W))
    })
}
class TestBlackBox extends AnyFunSuite with MockFactory{
  val blackBox = mock[IcarusBlackBoxTrait]

  test("BlackBox should be able to add two numbers") {
    blackBox.io.a := 10.U
    blackBox.io.b := 20.U
}


