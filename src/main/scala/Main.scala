import Routes.shipRoutes
import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.implicits._



object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    BlazeServerBuilder[IO](runtime.compute)
      .bindHttp(8888, "localhost")
      .withHttpApp(shipRoutes.orNotFound)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}
