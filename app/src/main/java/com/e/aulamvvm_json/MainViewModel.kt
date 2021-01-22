package com.e.aulamvvm_json

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.IOException
import javax.security.auth.login.LoginException

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val context = getApplication<Application>().applicationContext

    //maneira roots, sem biblioteca gson
    // vai retornar uma lista de gastos
    fun getGastos(): ArrayList<Gasto> {

        //declaração da lista apenas
        var listGasto: ArrayList<Gasto> = arrayListOf()


        try {
            //atraves do contexto, consegue acessar o diretorio assets
            //pegou o arquivo json e transformou em String
            val jsonString = context.assets.open("gastos.json")
                .bufferedReader().use { it.readText() }

            //transforma o arquivo que era string em um array
            var jsonArray = JSONArray(jsonString)

            //loop pra percorrer o array para poder ler cada um dos objetos
            for (i in 0..jsonArray.length() - 1) {
                val jsonObj = jsonArray.getJSONObject(i)

                //transformar objeto esse jsonObt em objeto do tipo gasto
                val gasto = Gasto(
                    jsonObj.getInt("id"),
                    jsonObj.getString("desc"),
                    jsonObj.getDouble("valor")
                )
                listGasto.add(gasto)
            }

            return listGasto

        } catch (ioExeption: IOException) {
            Log.i("MainViewModel", ioExeption.toString())
            //vai retornar lista vazia pois nao entrou no try enão fez o processo
            return listGasto

        }
    }


    //usando gson, antes de tudo incluir a dependencia do gson no gradle
    fun getGastosGsom(): List<Gasto> {

        var listGastos : List<Gasto> = listOf()

        try {
            //pegando o arquivo json dentro do assets através do contexto
            val jsonString = context.assets.open("gastos.json")
                .bufferedReader().use { it.readText() }

            //tem que informar o tipo de retorno atraves do token
             listGastos =Gson().fromJson(jsonString, object : TypeToken<List<Gasto>>() {}.type)

            return listGastos

        } catch (ioExeption: IOException) {
            Log.i("MainViewModel", ioExeption.toString())
            return listGastos
        }

    }
}