package com.vc

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.blackbox


class AnnotationMacro extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro AnnotationMacroImpl.impl
}

object AnnotationMacroImpl {
  def impl(c: blackbox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    annottees map(_.tree) match {
      case q"...$mods class $className(...$fields) extends ..$parents { ..$body }" :: Nil =>
        c.Expr[Any](
          q"""
                    $mods class $className(...$fields) extends ..$parents {
                        override def toString: ${typeOf[String]} = "annotation overrides toString method and returns this reply"
                        ..$body
                    }
               """
        )
    }
  }
}


