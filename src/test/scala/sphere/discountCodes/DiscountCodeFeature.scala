package sphere.discountCodes

import sphere.CommercetoolsCornichon


class DiscountCodeFeature extends CommercetoolsCornichon{

  def feature =
    Feature("DiscountCode feature") {

      Scenario("create 100 distinct discount codes").ignoredBecause("Ignored by default because it does not clean up the test data") {

        When a post("/cart-discounts").withBody(
          """
            {
              "name": {"en": "10 USD discount on everything!"},
              "description": {"en": "Generic 10 USD discount"},
              "value": {
              "type": "absolute",
              "money": [{
                "currencyCode": "USD",
                "centAmount": 1000
              }]},
              "cartPredicate": "1 = 1",
              "target": { "type": "lineItems", "predicate": "1 = 1" },
              "requiresDiscountCode" : true,
              "validFrom": "2014-05-15T10:15:55+0100",
              "validUntil": "2024-12-15T09:55:12+0100",
              "sortOrder": "0.456",
              "isActive": true
            }
            """
        )

        Then assert status.is(201)

        And I save_body_path("id" → "cart-discount-id")

        Repeat(100) {

          When I post("/discount-codes").withBody(
            """
            {
              "code": "<random-uuid>",
              "cartDiscounts": [{"typeId" : "cart-discount", "id": "<cart-discount-id>"}],
              "maxApplications": 1
            }
            """
          )

          Then assert status.is(201)

        }

        And I get("/discount-codes")

        Then assert body.path("total").is(100)
      }
    }
}

