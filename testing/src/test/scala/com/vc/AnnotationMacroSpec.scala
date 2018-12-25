package com.vc

import org.scalatest.{FlatSpec, Matchers}

class AnnotationMacroSpec extends FlatSpec with Matchers {

  "after annotating TestAnnotation case class toString" should "override to static value" in {
    @AnnotationMacro case class AnnotationTest(someField: String)
    val annotationTest = AnnotationTest("someField")
    assert(annotationTest.toString == "Static reply")
  }

}
