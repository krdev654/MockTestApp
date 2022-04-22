package com.example.mocktestapp.tests

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mocktestapp.HttpIdlingResourceRule
import com.example.mocktestapp.MainActivity
import com.example.mocktestapp.OkHttpProvider
import com.example.mocktestapp.R
import com.example.mocktestapp.rule.MockServerRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  @get:Rule
  val http3IdlingResource = HttpIdlingResourceRule(OkHttpProvider.getOkHttpClient())

  @get:Rule
  val mockServerRule = MockServerRule()

  @get:Rule
  val activityRule = ActivityScenarioRule(MainActivity::class.java)

  @Before
  fun setup() {
  }

  @After
  fun teardown() {
  }

  @Test
  fun testSuccessfulResponse() {
    launchActivity<MainActivity>()
    onView(withId(R.id.progress_bar))
      .check(matches(withEffectiveVisibility(Visibility.GONE)))
    onView(withId(R.id.character_recyclerview))
      .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withId(R.id.textview))
      .check(matches(withEffectiveVisibility(Visibility.GONE)))
  }
}
