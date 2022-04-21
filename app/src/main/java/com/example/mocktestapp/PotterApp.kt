package com.example.mocktestapp

import android.app.Application

open class PotterApp : Application() {
  open fun getBaseUrl() = "https://www.potterapi.com"
}