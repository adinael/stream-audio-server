package services

object HelloService {
  def getGreeting(name: String): String = s"Hello, $name!"
}