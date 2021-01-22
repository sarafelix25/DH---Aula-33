package com.e.aulamvvm_json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    //instaciação do view model - usar dependencia fragment ktx no gradle
    private val viewModel : MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listGastos = viewModel.getGastos()

        //vai imprimir cada item
        listGastos.forEach{
            Log.i("MainActivity", it.toString())
        }

    }

}