package com.vc

import org.scalatest.{FlatSpec, Matchers}

class DynamicMacroSpec extends FlatSpec with Matchers {

  "Adding greeting as a string to map" should "add it as a field to json object" in {
    val json = new DynamicMacro(Map("greeting" -> "We can basically return anything from here"))
    /** There is no field called greeting in DynamicMacro class
      * but passing Map("greeting" -> "We can basically return anything from here")
      * will make `greeting` field to DynamicMacro object
      */
    assert(json.greeting == "We can basically return anything from here")
  }

}
