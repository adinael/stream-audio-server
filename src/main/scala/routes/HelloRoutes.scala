package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

object HelloRoutes {

  def route: Route =
    path("hello") {
      get {
        complete("Hello, Akka HTTP!")
      }
    }
}
