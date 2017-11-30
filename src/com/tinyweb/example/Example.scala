package com.tinyweb.example

import com.tinyweb.{FunctionController, FunctionView, HttpRequest, TinyWeb}

import scala.util.Random

/**
  * Created by Hessam Shafiei Moqaddam on 11/30/17.
  */
class Example {

    def greetingViewRenderer(model: Map[String, List[String]]) = "<h1>Friendly Greetings:%s".format(
      model
      getOrElse("greetings",List[String]())
      map(renderGreeting)
      mkString ", ")

    private def renderGreeting(greeting: String) = "<h2>%s</h2>".format(greeting)

    def greetingView = new FunctionView(greetingViewRenderer)

    def handleGreetingRequest(request: HttpRequest) = Map("greetings" -> request.body.split(",").toList.map(makeGreeting))

    private def random = new Random()
    private def greetings = Vector("Hello","Greetings","Salutations","Hola","Hej","Salam")
    private def makeGreeting(name: String) = "%s, %s".format(greetings(random.nextInt(greetings.size)),name)

    def greetingController = new FunctionController(greetingView,handleGreetingRequest)

    private def loggingFilter(request: HttpRequest) = {
      println("In logging filter - request for path: %s".format(request.path))
      request
    }

}

object Example{
  def main(args: Array[String]): Unit = {

    val example = new Example

    def tinyWeb = new TinyWeb(Map("/greeting" -> example.greetingController), List(example.loggingFilter))
    def testHttpRequest = HttpRequest(Map("test" -> "test header"),body="A,B,C,D",path="/greeting")
    tinyWeb.handleRequest(testHttpRequest)
  }
}
