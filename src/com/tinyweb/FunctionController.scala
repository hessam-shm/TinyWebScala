package com.tinyweb


/**
  * Created by hessam on 11/24/17.
  */
class FunctionController(view: View, doRequest: (HttpRequest) => Map[String,List[String]]) extends Controller{

  override def handleRequest(request: HttpRequest): HttpResponse = {
    var responseCode = 200
    var responseBody = " "

    try{
      val model = doRequest(request)
      responseBody = view.render(model)
      HttpResponse(responseBody,200)
    }catch {
      //case e: ControllerException => HttpResponse(" ",e.getStatusCode)
      //case e: RenderingException => HttpResponse("Exception while rendering",500)
      case e: Exception => HttpResponse(" ",500)
    }
  }
}
