package dto

import cats.effect.IO
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder, HCursor, Json}
import org.http4s.{EntityDecoder, EntityEncoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}

object NumberOfPlaces {

  case class NumberOfPlaces(numberOfPlaces: Int)

  implicit val numberOfPlacesDecoder: Decoder[NumberOfPlaces] = (c: HCursor) => for {
    numberOfPlaces <- c.get[Int]("numberOfPlaces")
  } yield NumberOfPlaces(numberOfPlaces)

  implicit val numberOfPlacesEncoder: Encoder[NumberOfPlaces] = (a: NumberOfPlaces) => Json.obj("numberOfPlaces" -> a.numberOfPlaces.asJson)

  implicit val numberOfPlacesEntityDecoder: EntityDecoder[IO, NumberOfPlaces] = jsonOf[IO, NumberOfPlaces]
  implicit val numberOfPlacesEntityEncoder: EntityEncoder[IO, NumberOfPlaces] = jsonEncoderOf[IO, NumberOfPlaces]
}

