package com.tinyweb


/**
  * Created by hessam on 11/24/17.
  */
trait Controller {
  def handleRequest(httpRequest: HttpRequest): HttpResponse
}
