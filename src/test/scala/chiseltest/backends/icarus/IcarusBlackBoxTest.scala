// SPDX-License-Identifier: Apache-2.0

package chiseltest.backends.icarus

import chisel3._
import chisel3.util._
import chiseltest.RawTester.test
import chiseltest._
import chiseltest.backends.treadle.PlusArgReaderWrapper
import chiseltest.simulator.{PlusArgsAnnotation, RequiresIcarus}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalamock.scalatest.MockFactory

import scala.util.Random

class BlackBoxAdderIO extends Bundle {
  val a: UInt = Input(UInt(8.W))
  val b: UInt = Input(UInt(8.W))
  val q: UInt = Output(UInt(8.W))
}

class BlackBoxAdder extends BlackBox with HasBlackBoxInline {
  val io: BlackBoxAdderIO = IO(new BlackBoxAdderIO).suggestName("io")
  setInline(
    "BlackBoxAdder.v",
    """module BlackBoxAdder(a, b, q);
      |input [7:0] a, b;
      |output [7:0] q;
      |assign q = a + b;
      |endmodule
      |""".stripMargin
  )
}

class BlackBoxAdderWrapper extends Module {
  val io: BlackBoxAdderIO = IO(new BlackBoxAdderIO)
  val m: BlackBoxAdder = Module(new BlackBoxAdder)
  m.io <> io
}


class IcarusBlackBoxTests extends AnyFlatSpec {
  behavior.of {
    "Icarus Backend"
  }

  val annos: Seq[IcarusBackendAnnotation.type] = Seq(IcarusBackendAnnotation)

  it should "support IcarusVerilog black boxes" taggedAs RequiresIcarus in {
    val rand = new Random()
    val mask = (BigInt(1) << 8) - 1
    test(new BlackBoxAdderWrapper).withAnnotations(annos) { dut =>
      (0 until 1000).foreach { _ =>
        val a = BigInt(8, rand)
        val b = BigInt(8, rand)
        val q = (a + b) & mask
        dut.io.a.poke(a.U)
        dut.io.b.poke(b.U)
        dut.io.q.expect(q.U)
      }
    }
  }
}
