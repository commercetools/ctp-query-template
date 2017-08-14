package sphere.search

import sphere.CommercetoolsCornichon


class SearchFeature extends CommercetoolsCornichon {

  def feature =
    Feature("Search feature") {

      Scenario("use suggestion endpoint") {

        When I get("/product-projections/suggest").withParams(
          "searchKeywords.en" ->"someInput"
        )

        Then assert status.is(200)

      }
    }
}
