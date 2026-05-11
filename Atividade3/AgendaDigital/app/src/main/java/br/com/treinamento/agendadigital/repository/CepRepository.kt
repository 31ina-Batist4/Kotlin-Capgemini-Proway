package br.com.treinamento.agendadigital.repository

import br.com.treinamento.agendadigital.data.remote.RetrofitClient
import br.com.treinamento.agendadigital.data.model.Endereco

class CepRepository {

    suspend fun getCep(cep: String): Endereco{
        return RetrofitClient.apiCep.getCep(cep)
    }

}