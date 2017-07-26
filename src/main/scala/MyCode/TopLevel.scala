/*
 * SpinalHDL
 * Copyright (c) Dolu, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package MyCode

import spinal.core._
import spinal.lib._
import spinal.lib.experimental.math._

class MyTopLevel extends Component {
  val io = new Bundle {
    val cond0 = in  Bool
    val cond1 = in  Bool
    val flag  = out Bool
    val state = out UInt (16 bits)
  }
  val counter = Reg(Floating16()) init(0)

  when(io.cond0){
    counter := counter + BigDecimal ("1.0")
  }

  io.state := counter.asBits.asUInt
  io.flag  := (counter.asBits.asUInt === 0) | io.cond1
}

object MyTopLevel {
  def main(args: Array[String]) {
    //SpinalVhdl(new MyTopLevel)
    SpinalVerilog(new MyTopLevel)
  }
}

