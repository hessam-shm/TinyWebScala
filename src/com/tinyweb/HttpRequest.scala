package com.tinyweb

/**
  * Created by hessam on 11/24/17.
  */
case class HttpRequest(headers: Map[String,String],body: String,path: String)
