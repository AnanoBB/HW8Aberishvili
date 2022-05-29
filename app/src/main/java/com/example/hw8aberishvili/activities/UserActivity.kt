package com.example.hw8aberishvili.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hw8aberishvili.R
import com.example.hw8aberishvili.adapters.UserRecyclerviewAdapter
import com.example.hw8aberishvili.api.API
import com.example.hw8aberishvili.api.ResourceModel
import com.example.hw8aberishvili.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        val nameVal: TextView = findViewById(R.id.colorName)
        val colorBox: ImageView = findViewById(R.id.singleColorBox)
        val yearVal: TextView = findViewById(R.id.singleYear)
        val hexVal: TextView = findViewById(R.id.hexValue)
        val pantoneValue: TextView = findViewById(R.id.singlePantone)

        val resourceId = intent.extras?.getInt(UserRecyclerviewAdapter.RESOURCE_ID, -1)

        if (resourceId != -1){
            val errorTxt: TextView = findViewById(R.id.singleError)
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val reqResApi: API = retrofit.create(API::class.java)
            val call: Call<ResourceModel> = reqResApi.getResource(resourceId!!)
            call.enqueue(object : Callback<ResourceModel> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<ResourceModel>, response: Response<ResourceModel>) {
                    if (!response.isSuccessful) {
                        errorTxt.text = "Code: " + response.code()
                        return
                    }
                    val resource: User = response.body()!!.data
                    nameVal.text = resource.name
                    yearVal.text = resource.year.toString()
                    hexVal.text = resource.color
                    pantoneValue.text = resource.pantoneValue
                    colorBox.setBackgroundColor(Color.parseColor(resource.color))

                }

                override fun onFailure(call: Call<ResourceModel>, t: Throwable) {
                    errorTxt.text = t.message
                }

            })
        }
    }
}
