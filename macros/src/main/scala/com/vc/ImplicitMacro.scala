package com.vc

/** Implicit Type */
case class Point(x: Int, y: Int)
case class Line(x1: Int, x2: Int, y1: Int, y2: Int)
trait Showable[T] {
  def show(x: T): String
}

/** Normal Implicit implementation
  * different implicit implementation for Point and Line classes
  */
object ImplicitTypeDemo  {
  def show[T](x: T)(implicit s: Showable[T]): String = s.show(x)

  implicit def pointShowable = new Showable[Point] {
    override def show(p: Point): String = p.toString
  }
  implicit def lineShowable = new Showable[Line] {
    override def show(x: Line): String = x.toString
  }
}

import scala.language.experimental.macros
import scala.reflect.macros.blackbox
/** Macro Implicit implementation
  * materialized single implementation for Point and Line classes
  */
object ImplicitTypeMacroDemo {
  def show[T](x: T)(implicit s: Showable[T]): String = s.show(x)

  implicit def materializeShowable[T]: Showable[T] = macro materializeShowableImpl[T]
  def materializeShowableImpl[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Showable[T]] = {
    import c.universe._
    val tpe = c.weakTypeOf[T]
    c.Expr[Showable[T]](
      q"""
          new Showable[$tpe] {
             override def show(x: $tpe): String = x.toString
          }
       """
    )
  }
}