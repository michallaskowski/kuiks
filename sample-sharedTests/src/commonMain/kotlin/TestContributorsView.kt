package dev.michallaskowski.kuiks.sample.sharedTests

import dev.michallaskowski.kuiks.ApplicationWrapper
import dev.michallaskowski.kuiks.BaseTest
import dev.michallaskowski.mokttp.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.random.Random
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

open class TestContributorsView: BaseTest() {
    private lateinit var app: ApplicationWrapper
    private lateinit var httpServer: HttpServer

    @BeforeTest
    fun setUp() {
        httpServer = HttpServer()
        app = ApplicationWrapper(AppSetup.identifier)
    }

    @AfterTest
    fun tearDown() {
        httpServer.stop()
    }

    @Test
    fun testStubsHttpResponse() {
        httpServer.router = MockRouter()
        val port = 8080//Random.nextInt(1025, 10000)
        httpServer.start(port)

        app.launch(arguments = mapOf( "contributors_url" to "http://localhost:$port" ))
        app.elementWithTestId("contributors").tap()

        val label = app.elementWithTestId("label")
        label.hasText("Dawid")
        check(label.getText().equals("Dawid, Oskar"))
    }
}

@Serializable
internal data class Model(val login: String, val contributions: Int)

class MockRouter: Router {
    override fun handleRequest(request: Request): Response {
        if (request.method == "GET" && request.path?.startsWith("/repos/") == true) {
            val data = Json(JsonConfiguration.Stable).stringify(
                Model.serializer().list,
                listOf(Model("Dawid", 1), Model("Oskar", 2)))
            return Response(200, emptyMap(), Data(fromString = data), "application/json")
        } else {
            return Response(404, emptyMap(), null, null)
        }
    }
}