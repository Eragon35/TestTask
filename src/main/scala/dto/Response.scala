package dto

import cats.effect.IO
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder, HCursor, Json}
import org.http4s.{EntityDecoder, EntityEncoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}

object Response {

  case class Response(response: Int)

  implicit val responseDecoder: Decoder[Response] = (c: HCursor) => for {
    response <- c.get[Int]("response")
  } yield Response(response)
  implicit val responseEncoder: Encoder[Response] = (a: Response) => Json.obj("response" -> a.response.asJson)

  implicit val responseEntityDecoder: EntityDecoder[IO, Response] = jsonOf[IO, Response]
  implicit val responseEntityEncoder: EntityEncoder[IO, Response] = jsonEncoderOf[IO, Response]
}
