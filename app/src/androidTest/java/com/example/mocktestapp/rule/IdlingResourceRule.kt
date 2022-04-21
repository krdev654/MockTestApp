package com.example.mocktestapp

import android.support.test.espresso.IdlingResource
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import android.support.test.espresso.IdlingRegistry

class HttpIdlingResourceRule(okHttpClient: OkHttpClient?) : TestRule {
    private val resource: IdlingResource
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                IdlingRegistry.getInstance().register(resource)
                base.evaluate()
                IdlingRegistry.getInstance().unregister(resource)
            }
        }
    }

    init {
        resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient!!)
    }
}
