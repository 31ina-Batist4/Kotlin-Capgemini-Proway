package br.com.treinamento.agendadigital.service

import br.com.treinamento.agendadigital.data.model.Endereco
import retrofit2.http.GET
import retrofit2.http.Path

interface ConsultaCepService {

    @GET("ws/{cep}/json")
    suspend fun getCep(@Path("cep") cep: String) : Endereco
}