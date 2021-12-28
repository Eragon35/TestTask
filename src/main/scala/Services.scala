import dto.NumberOfPlaces.NumberOfPlaces
import dto.Response.Response
import dto.Ship.Ship

import scala.collection.mutable

object Services {

  var capacity: Int = 0
  var isCapacitySet: Boolean = false
  val shipQueue: scala.collection.mutable.Queue[Response] = scala.collection.mutable.Queue[Response]()
  var shipGarage: mutable.Set[Int] = scala.collection.mutable.Set[Int]()


  def numberOfPlaces(numberOfPlaces: NumberOfPlaces): Unit = {
    if (!isCapacitySet){
      capacity = numberOfPlaces.numberOfPlaces
      isCapacitySet = true
    }
  }


  def next(): Option[Response] = {
    if (shipQueue.nonEmpty) Some(shipQueue.dequeue())
    else None
  }


  def ship(ship: Ship): Unit = {
    var result: Int = 0
    /*
    while we have free space in garage we
    find max value inside set, because it's the time
    when support for previous ships would be finished
    and we add result + ship.handleTime to the set
     */
    if (shipGarage.size < capacity) {
      if (shipGarage.nonEmpty) result = shipGarage.max
      shipGarage += (result + ship.handleTime)
    }
    else {
      /*
      we finding the lowest result inside the set
      if it's lower than timeOfArrival, then
      we can stay at the garage waiting the support
      if min bigger then we can't wait support in garage
      because there are no free space, so we return -1
       */
      val min = shipGarage.min
      if (min <= ship.timeOfArrival) {
        shipGarage -= min
        result = shipGarage.max
        shipGarage += (result + ship.handleTime)
      } else result = -1
    }
    shipQueue += Response(result)
  }
}
