package com.example.mocktestapp.tests

import android.util.Log
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mocktestapp.MainActivity
import okhttp3.mockwebserver.MockWebServer
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
  }

  @After
  fun teardown() {
    mockWebServer.shutdown()
  }


  @Test
  fun myTest() {
    Log.i("Test", "Message")
  }
}