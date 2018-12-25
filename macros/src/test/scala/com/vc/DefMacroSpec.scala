package com.vc

import com.vc.DefMacro.stringChecker
import org.scalatest.{FlatSpec, Matchers}

class DefMacroSpec extends FlatSpec with Matchers {

  it should "fail at compile time" in {
    """stringChecker("com")""" shouldNot compile
  }

  it should "success at compile time" in {
    assert(stringChecker("compile") == "compile")
  }

}
