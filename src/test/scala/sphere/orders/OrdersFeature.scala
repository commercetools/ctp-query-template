package sphere.orders

import sphere.CommercetoolsCornichon

class OrdersFeature extends CommercetoolsCornichon {

  def feature =
    Feature("Orders feature") {

      Scenario("query orders") {

        When I get("/orders").withParams(
          "WHERE" -> "lastModifiedAt>=1485413088"
        )

        And I show_last_body_json

        Then assert status.is(200)
      }
    }
}