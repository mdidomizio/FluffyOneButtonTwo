package com.example.flyffykitty04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.flyffykitty04.Api.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var kittyPicture: ImageView
    private lateinit var nextKittyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kittyPicture = findViewById(R.id.kittyImage)
        nextKittyButton = findViewById(R.id.nextKittyButton)

        nextKittyButton.setOnClickListener {
            addPic()
        }
    }
    private fun addPic() {
        api.getRandomPic().enqueue(
            object: Callback<List<PhotoGalleryClass>> {
                override fun onResponse(
                    call: Call<List<PhotoGalleryClass>>,
                    response: Response<List<PhotoGalleryClass>>
                ) {
                    if (response.body() != null) {
                        val listOfCatPictures: List<PhotoGalleryClass> = response.body()!!

                        Log.d("API_CALL", "This is the the api call $listOfCatPictures")
                        Log.d("API_CALL", listOfCatPictures.toString())

                        val firstElement: PhotoGalleryClass = listOfCatPictures[0]
                        val url = firstElement.url

                        Log.d("API_CALL", "URL: $url")

                        Glide
                            .with(this@MainActivity)
                            .load( url)
                            .into(kittyPicture)
                    }
                }

                override fun onFailure(call: Call<List<PhotoGalleryClass>>, t: Throwable) {
                    Log.d("API_CALL", "This call failed")
                    Toast.makeText(
                        applicationContext, "Something went wrong with the fluffy kitty picture",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
    }
}