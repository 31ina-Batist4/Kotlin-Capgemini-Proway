package br.com.treinamento.agendadigital.data.remote

import br.com.treinamento.agendadigital.service.ConsultaCepService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // url consulta cep
    private const val BASE_API_CEP = "https://viacep.com.br/"

    val apiCep: ConsultaCepService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_API_CEP)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConsultaCepService::class.java)
    }
}