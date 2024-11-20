package chiseltest.backends.icarus

import chisel3.DontCare.:=
import chisel3.simulator.PeekPokeAPI.testableData
import chisel3.{Bundle, IO, Input, Output, UInt, fromIntToLiteral, fromIntToWidth}
import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.util.Random

trait IcarusBlackBoxTrait {
        val a: UInt = Input(UInt(8.W))
        val b: UInt = Input(UInt(8.W))
        val q: UInt = Output(UInt(8.W))
}
class TestBlackBox extends AnyFunSuite with MockFactory {
  val blackBox = mock[IcarusBlackBoxTrait]

  test("support IcarusVerilog black boxes") {
   blackBox.io.a := 8.U
   blackBox.io.b := 8.U
   blackBox.io.q := 16.U
    blackBox.io.q should be(convertToAnyShouldWrapper(16.U))
  }
}


