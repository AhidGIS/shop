package controllers

import models.{CreateItem, Item}
import play.api.libs.functional.syntax._
import play.api.libs.json._
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

  implicit val readsCreateItem: Reads[CreateItem] = (
    ((__ \ "name").read[String]) and ((__ \ "price").read[Double])
    )(CreateItem.apply _)

// list of all items
  def list(page: Int) = Action {
    Ok(Json.toJson(shop.list))
  }

  val create = Action(parse.json) { implicit request =>
    request.body.validate[CreateItem] match {
      case JsSuccess(createItem, _) =>
        shop.create(createItem.name, createItem.price) match {
          case Some(item) => Ok(Json.toJson(item))
          case None => InternalServerError
        }
      case JsError(errors) => BadRequest
    }
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
