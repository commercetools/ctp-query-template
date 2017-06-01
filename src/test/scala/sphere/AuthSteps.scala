package sphere

import java.nio.charset.StandardCharsets
import java.util.Base64

import com.github.agourlay.cornichon.http.{HttpRequest, HttpService, RootExtractor}
import com.github.agourlay.cornichon.steps.regular.EffectStep


trait AuthSteps {
  this: CommercetoolsCornichon ⇒

  val auth = httpServiceByURL(wsConfig.authApi)

  def create_token = EffectStep(
    title = "Create token",
    show = false,
    effect = auth.requestEffect(
      request = HttpRequest.post("/oauth/token")
        .withParams(
          "grant_type" → "client_credentials",
          "scope" → "manage_project:<project-key>")
        .withHeaders("Authorization" -> authHeaderValue(wsConfig.client.id, wsConfig.client.secret)),
      extractor = RootExtractor("token"),
      expectedStatus = Some(200)
    )
  )

  def authHeaderValue(clientId: String, clientSecret: String) =
    "Basic " + Base64.getEncoder.encodeToString(s"$clientId:$clientSecret".getBytes(StandardCharsets.UTF_8))

  def setup_auth_headers = EffectStep.fromSync(
    title = "Setup auth headers",
    show = false,
    effect = s ⇒ {
      val token = s.getJsonStringFieldUnsafe("token", path = "access_token")
      s.addValue("with-headers", HttpService.encodeSessionHeader("Authorization",s"Bearer $token"))
    }
  )
}
