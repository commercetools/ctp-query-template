package sphere

case class WsConfig(api: String, authApi: String, projectName: String, client: ClientConfig)
case class ClientConfig(secret: String, id: String)
