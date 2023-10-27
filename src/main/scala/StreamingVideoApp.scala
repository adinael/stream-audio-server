import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString

import java.nio.file.Paths
import scala.concurrent.{ExecutionContext, Future}

object StreamingVideoApp {
  implicit val system: ActorSystem  = ActorSystem("StreamingVideoApp")
  implicit val ex: ExecutionContext = ExecutionContext.global
  private val videoFilePath         = Paths.get("src/main/resources/testvideo.webm")
  private val audioDirectoryPath    = Paths.get("src/main/resources/songs/")

  private val videoRoute: Route =
    path("video") {
      get {
        val videoSource: Source[ByteString, Future[IOResult]] =
          FileIO.fromPath(videoFilePath)

        complete(
          HttpResponse(
            entity = HttpEntity(MediaTypes.`video/webm`, videoSource)
          )
        )
      }
    }
  private val audioRoute =
    path("audio") {
      parameters("song") { song =>
        get {
          val audioFilePath = Paths.get(audioDirectoryPath.toString, s"$song.mp3")
          val audioSource: Source[ByteString, Future[IOResult]] =
            FileIO.fromPath(audioFilePath)

          complete(
            HttpResponse(
              entity = HttpEntity(MediaTypes.`audio/mpeg`, audioSource)
            )
          )
        }
      }
    }

  val routes = audioRoute ~ videoRoute
}
