import cats.effect.IO
import dto.NumberOfPlaces.NumberOfPlaces
import dto.Ship.Ship
import org.http4s.HttpRoutes
import org.http4s.dsl.io._


object Routes {

  def shipRoutes: HttpRoutes[IO] =
    HttpRoutes
      .of[IO]({
        case GET -> Root / "next" =>
          Services.next() match {
            case Some(value) => Ok(value)
            case None => Ok()
          }

        case request @ POST -> Root / "numberOfPlaces"  =>
          request.as[NumberOfPlaces].flatMap(numberOfPlaces => {
            Services.numberOfPlaces(numberOfPlaces)
            Ok()
          }).handleErrorWith(_ => BadRequest())

        case request @ POST -> Root / "ship" =>
          request.as[Ship].flatMap(ship => {
            Services.ship(ship)
            Ok()
          }).handleErrorWith(_ => BadRequest())
      })
}
