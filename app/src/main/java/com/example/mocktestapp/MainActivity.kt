package com.example.mocktestapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mocktestapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  lateinit var viewBinding: ActivityMainBinding

  private lateinit var characterAdapter: CharacterAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    viewBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(viewBinding.root)
    //setContentView(R.layout.activity_main)

    // Your code
    characterAdapter = CharacterAdapter()
    viewBinding.characterRecyclerview.adapter = characterAdapter
    //character_recyclerview.adapter = characterAdapter

    val potterApi = Retrofit.Builder()
        .baseUrl((application as PotterApp).getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(OkHttpProvider.getOkHttpClient())
        .build()
        .create(PotterApi::class.java)

    potterApi.getCharacters().enqueue(object : Callback<List<CharacterModel>> {
      override fun onFailure(call: Call<List<CharacterModel>>, t: Throwable) {
        showErrorState()
      }

      override fun onResponse(call: Call<List<CharacterModel>>,
                              response: Response<List<CharacterModel>>) {
        if (response.isSuccessful && response.body() != null) {
          val characterList = response.body()!!
          if (characterList.isEmpty()) {
            showEmptyDataState()
          } else {
            showCharacterList(characterList)
          }
        } else {
          showErrorState()
        }
      }
    })
  }

  private fun showEmptyDataState() {

    viewBinding.characterRecyclerview.visibility = View.GONE
    viewBinding.progressBar.visibility = View.GONE
    viewBinding.textview.visibility = View.VISIBLE
    viewBinding.textview.text = getString(R.string.there_seems_to_be_no_data)
  }

  private fun showCharacterList(characterList: List<CharacterModel>) {
    viewBinding.characterRecyclerview.visibility = View.VISIBLE
    viewBinding.progressBar.visibility = View.GONE
    viewBinding.textview.visibility = View.GONE
    characterAdapter.setCharacterList(characterList)
  }

  private fun showErrorState() {
    viewBinding.characterRecyclerview.visibility = View.GONE
    viewBinding.progressBar.visibility = View.GONE
    viewBinding.textview.visibility = View.VISIBLE
    viewBinding.textview.text = getString(R.string.something_went_wrong)
  }

}
