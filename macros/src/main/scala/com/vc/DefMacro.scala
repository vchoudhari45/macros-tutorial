package com.vc

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object DefMacro {

  def stringChecker(s: String): String = macro stringCheckerImpl

  def stringCheckerImpl(c: blackbox.Context)(s: c.Expr[String]): c.Expr[String] = {
    import c.universe._
    s match {
      case Expr(Literal(Constant(nValue: String))) =>
        val result = normalStringChecker(nValue)
        c.Expr(Literal(Constant(result)))
      case _ => c.Expr(Literal(Constant("Don't send me anything other than String")))
    }
  }

  private[this] def normalStringChecker(s: String): String =
    if(s.length >= 4) s
    else throw new Exception("length should be >= 4")
}
