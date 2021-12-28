package dto

import cats.effect.IO
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder, HCursor, Json}
import org.http4s.{EntityDecoder, EntityEncoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}

object Ship {
  case class Ship(timeOfArrival: Int, handleTime: Int)


  implicit val shipDecoder: Decoder[Ship] = (c: HCursor) => for {
    arrival <- c.get[Int]("timeOfArrival")
    handle <- c.get[Int]("handleTime")
  } yield Ship(arrival, handle)

  implicit val shipEncoder: Encoder[Ship] = (a: Ship) => Json.obj(
    "timeOfArrival" -> a.timeOfArrival.asJson,
    "handleTime" -> a.handleTime.asJson
  )

  implicit val shipEntityDecoder: EntityDecoder[IO, Ship] = jsonOf[IO, Ship]
  implicit val shipEntityEncoder: EntityEncoder[IO, Ship] = jsonEncoderOf[IO, Ship]
}

