package com.vc

import scala.language.dynamics
class DynamicMacro(fields: Map[String, Any]) extends Dynamic {
  def selectDynamic(fieldName: String) = fields(fieldName)
}
