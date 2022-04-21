package com.example.mocktestapp.rule

import com.example.mocktestapp.util.FileReader
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MockServerRule: TestRule {

    private val mockWebServer = MockWebServer()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                mockWebServer.start(8080)
                mockWebServer.dispatcher = object : Dispatcher() {
                    override fun dispatch(request: RecordedRequest): MockResponse {
                        return MockResponse()
                            .setResponseCode(200)
                            .setBody(FileReader.readStringFromFile("success_response.json"))
                    }
                }

                base.evaluate()

                mockWebServer.shutdown()
            }
        }
    }
}
