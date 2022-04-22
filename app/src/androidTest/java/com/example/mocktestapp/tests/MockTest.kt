package com.example.mocktestapp.tests

import android.util.Log
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mocktestapp.MainActivity
import com.example.mocktestapp.util.FileReader
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MockTest {

  private val mockWebServer = MockWebServer()

  @get:Rule
  val activityRule = ActivityScenarioRule(MainActivity::class.java)

  @Before
  fun setup() {
    mockWebServer.start(8080)
    val mockedResponse = MockResponse()
      .setResponseCode(200)
      .setBody(FileReader.readStringFromFile("success_response.json"))
      .addHeader("Content-Type", "application/json");
    mockWebServer.enqueue(mockedResponse)
  }

  @After
  fun teardown() {
    mockWebServer.shutdown()
  }


  @Test
  fun myTest() {
    Log.i("Test", "Message")
//    mockWebServer.dispatcher = object : Dispatcher() {
//      override fun dispatch(request: RecordedRequest): MockResponse {
//        return MockResponse()
//          .setResponseCode(200)
//          .setBody(FileReader.readStringFromFile("success_response.json"))
//      }
//    }

    // Optional: confirm that your app made the HTTP requests you were expecting.
    val request1: RecordedRequest = mockWebServer.takeRequest()
    Log.i("MockTest", request1.body.toString())
//    assertEquals("/v1/chat/messages/", request1.path)
//    assertNotNull(request1.getHeader("Authorization"))
//
//    val request2: RecordedRequest = server.takeRequest()
//    assertEquals("/v1/chat/messages/2", request2.path)
//
//    val request3: RecordedRequest = server.takeRequest()
//    assertEquals("/v1/chat/messages/3", request3.path)

  }
}