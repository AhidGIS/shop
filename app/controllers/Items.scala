package controllers

import play.api.mvc.{Action, Controller}

class Items extends Controller {
  val shop = models.Shop

  def list(page: Int) = Action {
    NotImplemented
  }

  val create = Action {
    NotImplemented
  }

  def details(id: Long) = Action {
    NotImplemented
  }

  def update(id: Long) = Action {
    NotImplemented
  }

  def delete(id: Long) = Action {
    NotImplemented
  }
}
