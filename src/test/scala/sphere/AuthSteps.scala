package sphere

import java.nio.charset.StandardCharsets
import java.util.Base64

import com.github.agourlay.cornichon.core.Step
import com.github.agourlay.cornichon.http.{HttpRequest, HttpService, RootExtractor}
import com.github.agourlay.cornichon.steps.regular.EffectStep
import com.github.agourlay.cornichon.json.CornichonJson._


trait AuthSteps {
  this: CommercetoolsCornichon =>

  val auth: HttpService = httpServiceByURL(wsConfig.authApi)

  def create_token: Step = EffectStep(
    title = "Create token",
    show = false,
    effect = auth.requestEffect(
      request = HttpRequest.post("/oauth/token")
        .withParams(
          "grant_type" -> "client_credentials",
          "scope"-> "manage_project:<project-key>")
        .withHeaders("Authorization" -> authHeaderValue(wsConfig.client.id, wsConfig.client.secret)),
      extractor = RootExtractor("token"),
      expectedStatus = Some(200)
    )
  )

  def authHeaderValue(clientId: String, clientSecret: String): String =
    "Basic " + Base64.getEncoder.encodeToString(s"$clientId:$clientSecret".getBytes(StandardCharsets.UTF_8))

  def setup_auth_headers: Step = EffectStep.fromSyncE(
    title = "Setup auth headers",
    show = false,
    effect = s => {
      for {
        cleaned <- removeFromWithHeaders("Authorization")(s.session) //cleanup header before hand to avoid duplicate entry for 'Authorization'
        token <- s.session.getJsonStringField("token", path = "access_token")
        withAuth <- addToWithHeaders("Authorization", s"Bearer $token")(cleaned)
      } yield withAuth
    }
  )
}
