package com.vc

import org.scalatest.{FlatSpec, Matchers}
class ImplicitMacroSpec extends FlatSpec with Matchers {

  it should "return success for ImplicitTypeMacroDemo" in {
    import com.vc.ImplicitTypeMacroDemo._
    val p = Point(1, 2)
    val l = Line(1, 2, 3, 4)
    assert(show(p) == "Point(1,2)")
    assert(show(l) == "Line(1,2,3,4)")
  }

  it should "return success for ImplicitTypeDemo" in {
    import com.vc.ImplicitTypeDemo._
    val p = Point(1, 2)
    val l = Line(1, 2, 3, 4)
    assert(show(p) == "Point(1,2)")
    assert(show(l) == "Line(1,2,3,4)")
  }
}


