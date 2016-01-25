package sphere.search

import sphere.CommercetoolsCornichon


class SearchFeature extends CommercetoolsCornichon {

  def feature =
    Feature("Search feature") {

      Scenario("aggregation strategy produces the same result") {

        When I GET("/product-projections/suggest", params =
          "searchKeywords.en" ->"someInput"
        )

        And I save_body_key("searchKeywords.en" -> "first-res")

        When I GET("/product-projections/suggest", params =
          "searchKeywords.en" ->"someInput"
        )

        And I save_body_key("searchKeywords.en" -> "second-res")

        And assert json_equality_for("first-res", "second-res")

        Then assert status_is(200)

      }
    }
}
