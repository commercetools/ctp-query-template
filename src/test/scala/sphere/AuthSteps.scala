package sphere

import java.nio.charset.StandardCharsets
import java.util.Base64
import com.github.agourlay.cornichon.core.RunnableStep._


trait AuthSteps {
  this: CommercetoolsCornichon ⇒

  val auth = httpServiceByURL(wsConfig.authApi)

  def create_token = effectful(
    title = "Create token",
    show = false,
    effect = s ⇒ {
      val clientSecret = wsConfig.client.secret
      val clientId = wsConfig.client.id
      auth.Post(
        url = "/oauth/token",
        payload = "",
        params = Seq("grant_type" → "client_credentials", "scope" → s"manage_project:<project-key>"),
        headers = Seq(("Authorization", "Basic " + Base64.getEncoder.encodeToString(s"$clientId:$clientSecret".getBytes(StandardCharsets.UTF_8)))),
        extractor = Some("token"),
        expected = Some(200)
      )(s)
    }
  )

  def setup_auth_headers = effectful(
    title = "Setup auth headers",
    show = false,
    effect = s ⇒ {
      val token = (s.getJson("token") \ "access_token").values.toString
      s.addValue("with-headers", s"Authorization|Bearer $token")
    }
  )
}
