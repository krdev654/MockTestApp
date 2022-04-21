package com.example.mocktestapp.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.mocktestapp.PotterTestApp

class MockTestRunner : AndroidJUnitRunner() {

  override fun newApplication(cl: ClassLoader?, className: String?,
                              context: Context?): Application {
    return super.newApplication(cl, PotterTestApp::class.java.name, context)
  }
}