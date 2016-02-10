package sphere.search

import sphere.CommercetoolsCornichon


class SearchFeature extends CommercetoolsCornichon {

  def feature =
    Feature("Search feature") {

      Scenario("aggregation strategy produces the same result") {

        When I get("/product-projections/suggest").withParams(
          "searchKeywords.en" ->"someInput"
        )

        And I save_body_key("searchKeywords.en" -> "first-res")

        When I get("/product-projections/suggest").withParams(
          "searchKeywords.en" ->"someInput"
        )

        And I save_body_key("searchKeywords.en" -> "second-res")

        And assert session_json_values("first-res", "second-res").areEquals

        Then assert status.is(200)

      }
    }
}
