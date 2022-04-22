package com.example.mocktestapp.tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mocktestapp.HttpIdlingResourceRule
import com.example.mocktestapp.MainActivity
import com.example.mocktestapp.OkHttpProvider
import com.example.mocktestapp.R
import com.example.mocktestapp.rule.MockServerRule
import com.example.mocktestapp.util.FileReader
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainActivityTest {



  @get:Rule
  val http3IdlingResource = HttpIdlingResourceRule(OkHttpProvider.getOkHttpClient())

  @get:Rule
  val mockServerRule = MockServerRule()

  @get:Rule
  val activityRule = ActivityScenarioRule(MainActivity::class.java)
//  @get:Rule
//  val activityScenario: ActivityScenario<MainActivity> = ActivityScenario.launch(MainActivity::class.java)

//  @get:Rule
//  val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

  //private val mockWebServer = MockWebServer()

  @Before
  fun setup() {
    //mockWebServer.start(8080)
  }

  @After
  fun teardown() {
//    mockWebServer.shutdown()
//    IdlingRegistry.getInstance().register(
//      OkHttp3IdlingResource.create(
//        "okhttp",
//        OkHttpProvider.getOkHttpClient()
//      )
//    )
  }

  @Test
  fun testSuccessfulResponse() {
//    mockWebServer.dispatcher = object : Dispatcher() {
//      override fun dispatch(request: RecordedRequest): MockResponse {
//        return MockResponse()
//          .setResponseCode(200)
//          .setBody(FileReader.readStringFromFile("success_response.json"))
//      }
//    }
    //activityRule.launchActivity(null)

    launchActivity<MainActivity>()
    onView(withId(R.id.progress_bar))
      .check(matches(withEffectiveVisibility(Visibility.GONE)))
    onView(withId(R.id.character_recyclerview))
      .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withId(R.id.textview))
      .check(matches(withEffectiveVisibility(Visibility.GONE)))
  }

//  @Test
//  fun testFailedResponse() {
//    mockWebServer.dispatcher = object : Dispatcher() {
//      override fun dispatch(request: RecordedRequest): MockResponse {
//        return MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
//      }
//    }
//
//    //activityRule.launchActivity(null)
//
//    onView(withId(R.id.progress_bar))
//        .check(matches(withEffectiveVisibility(Visibility.GONE)))
//    onView(withId(R.id.character_recyclerview))
//        .check(matches(withEffectiveVisibility(Visibility.GONE)))
//    onView(withId(R.id.textview))
//        .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//    onView(withId(R.id.textview))
//        .check(matches(withText(R.string.something_went_wrong)))
//  }

}
