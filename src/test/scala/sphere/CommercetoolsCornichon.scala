package sphere

import com.github.agourlay.cornichon.CornichonFeature
import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

trait CommercetoolsCornichon extends CornichonFeature with AuthSteps{

  implicit lazy val wsConfig = ConfigFactory.load().as[WsConfig]("ws")

  override lazy val baseUrl = s"${wsConfig.api}/<project-key>"

  beforeEachScenario {
    Attach {
      save("project-key" -> wsConfig.projectName)
      create_token
      setup_auth_headers
    }
  }
}
