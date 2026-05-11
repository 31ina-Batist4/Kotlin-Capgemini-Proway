package br.com.treinamento.agendadigital.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import br.com.treinamento.agendadigital.data.database.AppDatabase
import br.com.treinamento.agendadigital.data.remote.RetrofitClient
import br.com.treinamento.agendadigital.mapper.toEntity
import br.com.treinamento.agendadigital.mapper.toModel
import br.com.treinamento.agendadigital.model.Contato
import br.com.treinamento.agendadigital.model.Endereco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


data class ContatoUiState(
    val id: Int = 0,
    val nome: String = "",
    val email: String = "",
    val telefone: String = "",
    val nascimento: String = "",
    val cep: String = "",
    val bairro: String = "",
    val logradouro: String = "",
    val numero: String = "",
    val cidade: String = "",
    val estado: String = ""
)

class ContatoViewModel(
    application: Application
): AndroidViewModel(application) {

   private val dao = AppDatabase.getDatabase(application).contatoDao()

    val nome = MutableLiveData("")
    val email = MutableLiveData("")
    val telefone = MutableLiveData("")
    val nascimento = MutableLiveData("")
    val cep = MutableLiveData("")
    val bairro = MutableLiveData("")
    val logradouro = MutableLiveData("")
    val numero = MutableLiveData("")
    val estado = MutableLiveData("")
    val cidade = MutableLiveData("")


    private val _uiState = MutableLiveData(ContatoUiState())
    val uiState: LiveData<ContatoUiState> = _uiState


    val textoBusca = MutableLiveData("")

    val contatos: LiveData<List<Contato>> =
        dao.select().map { lista ->
            lista.map { it.toModel() }
        }


    val filtro: LiveData<List<Contato>> = MediatorLiveData<List<Contato>>().apply {
        fun filtrar() {
            val lista = contatos.value ?: emptyList()
            val busca = textoBusca.value ?: ""

            value = lista.filter {
                it.nome.contains(busca, ignoreCase = true)
            }
        }
        addSource(contatos) { filtrar() }
        addSource(textoBusca) { filtrar() }
    }

    fun insertContato() {
        viewModelScope.launch(Dispatchers.IO)  {
            dao.insert(requestContato().toEntity())
           contatos
        }
    }

    fun deleteContato(contato: Contato) {
        viewModelScope.launch(Dispatchers.IO)  {
            dao.delete(contato.toEntity())
           contatos
        }
    }

    fun updateContato() {
        viewModelScope.launch(Dispatchers.IO)  {
            dao.update(requestContato().toEntity())
           contatos
        }
    }
    fun getContatoById(id: Int): LiveData<Contato> {
        return dao.getById(id).map { it.toModel()}
    }

    fun getContato(id: Int){
       viewModelScope.launch {
           val entity = dao.get(id)
           val contato = entity.toModel()

           if (entity == null) {
               println("Contato não encontrado") // ou tratar erro
               return@launch
           }
           
           _uiState.postValue(
               ContatoUiState(
                   id = contato.id,
                   nome = contato.nome,
                   email = contato.email,
                   telefone = contato.telefone,
                   nascimento = contato.nascimento,
                   cep = contato.endereco.cep,
                   bairro = contato.endereco.bairro,
                   logradouro = contato.endereco.logradouro,
                   numero = contato.endereco.numero?.toString() ?: "",
                   cidade = contato.endereco.cidade,
                   estado = contato.endereco.estado
               )
           )
       }
    }

    fun onChangeNome(valor: String) {
        _uiState.value = _uiState.value?.copy(nome = valor)
    }

    fun onChangeEmail(valor: String) {
        _uiState.value = _uiState.value?.copy(email = valor)
    }

    fun onChangeTelefone(valor: String) {
        _uiState.value = _uiState.value?.copy(telefone = valor)
    }

    fun onChangeNascimento(valor: String) {
        _uiState.value = _uiState.value?.copy(nascimento = valor)
    }

    fun onChangeCep(valor: String) {
        _uiState.value = _uiState.value?.copy(cep = valor)
    }

    fun onChangeBairro(valor: String) {
        _uiState.value = _uiState.value?.copy(bairro = valor)
    }

    fun onChangeLogradouro(valor: String) {
        _uiState.value = _uiState.value?.copy(logradouro = valor)
    }

    fun onChangeNumero(valor: String) {
        _uiState.value = _uiState.value?.copy(numero = valor)
    }

    fun onChangeCidade(valor: String) {
        _uiState.value = _uiState.value?.copy(cidade = valor)
    }

    fun onChangeEstado(valor: String) {
        _uiState.value = _uiState.value?.copy(estado = valor)
    }

    fun clearForm() {
        nome.value = ""
        email.value = ""
        telefone.value = ""
        nascimento.value = ""
        cep.value = ""
        bairro.value = ""
        logradouro.value = ""
        numero.value = ""
        cidade.value = ""
        estado.value = ""

    }

    fun requestContato(): Contato {
        val s = uiState.value!!
     return  Contato(
         id = s.id,
         nome = s.nome,
         email = s.email,
         telefone = s.telefone,
         nascimento = s.telefone,
         endereco = Endereco(
             cep = s.email,
             bairro = s.bairro,
             logradouro = s.logradouro,
             numero = s.numero.toIntOrNull(),
             estado = s.estado,
             cidade =s.cidade
         )
      )
    }

    fun getEndrecoCep() {
        val cep = uiState.value?.cep ?: ""
        if(cep.length != 8) return
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiCep.getCep(cep)

                _uiState.value = _uiState.value.copy(
                    logradouro = response.logradouro,
                    bairro = response.bairro,
                    cidade = response.localidade,
                    estado = response.estado
                )
            } catch (e: Exception) {
                println("Erro ao consultar CEP")
            }
        }
    }

}