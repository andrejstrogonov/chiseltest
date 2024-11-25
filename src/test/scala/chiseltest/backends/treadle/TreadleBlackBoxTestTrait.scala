package chiseltest.backends.treadle

import chisel3.{Bundle, IO, Output, UInt, fromIntToWidth}
import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite

trait TreadleBlackBoxTestTrait {
  def out = Output(UInt(32.W))
  def name = "PlusArgReader"
  private val argName:  String = "ARGUMENT"
  private var argument: BigInt = 0xdeadbeefL
  def readPlusArg(argName: String, argument: BigInt): Unit = {
    this.argument = argument
  }


}
class TreadleBlackBoxTest extends AnyFunSuite with MockFactory{
  val treadleBlackBoxTestTraitCollaborator = mock[TreadleBlackBoxTestTrait]
  class TreadleBlackBoxTestTraitUnderTest extends TreadleBlackBoxTestTrait{

  }

  // Your test cases go here
  // For example:
  // test("Simple circuit") {
  //   val io = new SimpleCircuit()
  //   assert(io.out === 0.U)
  // }
}
