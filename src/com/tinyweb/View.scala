package com.tinyweb

/**
  * Created by hessam on 11/24/17.
  */
trait View {
  def render(model :Map[String, List[String]]): String
}
