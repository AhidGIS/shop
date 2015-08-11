package controllers

import models.Item
import play.api.libs.json.{Json, Writes}
import play.api.mvc.{Action, Controller}

class Items extends Controller {
  val shop = models.Shop
  shop.create("ma", 123)
  shop.create("htrhrh", 23.574)

  implicit val writesItem = Writes[Item] {
    case Item(id, name, price) =>
      Json.obj(
        "id" -> id,
        "name" -> name,
        "price" -> price
      )
  }

  def list(page: Int) = Action {
    Ok(Json.toJson(shop.list))
  }

  val create = Action {
    NotImplemented
  }

  def details(id: Long) = Action {
    shop.get(id) match {
      case Some(item) =>
        Ok(Json.toJson(item))
      case None => NotFound
    }
  }

  def update(id: Long) = Action {
    NotImplemented
  }

  def delete(id: Long) = Action {
    NotImplemented
  }
}
