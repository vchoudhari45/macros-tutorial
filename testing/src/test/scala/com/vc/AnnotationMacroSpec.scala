package com.vc

import org.scalatest.{FlatSpec, Matchers}

class AnnotationMacroSpec extends FlatSpec with Matchers {

  "after annotating TestAnnotation case class toString" should "override to static value" in {
    @AnnotationMacro class AnnotationTest
    val annotationTest = new AnnotationTest
    println(annotationTest.toString)
  }

}
