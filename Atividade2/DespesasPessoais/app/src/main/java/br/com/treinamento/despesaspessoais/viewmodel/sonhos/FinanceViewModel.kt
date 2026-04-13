import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.treinamento.despesaspessoais.model.Sonho
import br.com.treinamento.despesaspessoais.ui.screens.sonhos.SonhoFormUiState
import kotlinx.coroutines.flow.*
import java.util.*
import kotlin.collections.plus

data class FinanceItem(
    val descricao: String,
    val valor: Double,
    val data: Date = Date()
)

class FinanceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SonhoFormUiState())
    val uiState: StateFlow<SonhoFormUiState> = _uiState

    private val _ganhos = MutableStateFlow<List<FinanceItem>>(emptyList())
    private val _gastos = MutableStateFlow<List<FinanceItem>>(emptyList())

    val ganhos: StateFlow<List<FinanceItem>> = _ganhos
    val gastos: StateFlow<List<FinanceItem>> = _gastos

    private val _sonho = MutableStateFlow<Sonho?>(null)
    val sonho: StateFlow<Sonho?> = _sonho


    fun onTituloChange(valor: String) {
        _uiState.value = _uiState.value.copy(titulo = valor)
    }

    fun onValorChange(valor: String) {
        _uiState.value = _uiState.value.copy(valor = valor)
    }

    fun onDataInicialChange(data: Date) {
        _uiState.value = _uiState.value.copy(dataInicial = data)
    }

    fun onDataFinalChange(data: Date) {
        _uiState.value = _uiState.value.copy(dataFinal = data)
    }

    val totalGanhos: StateFlow<Double> =
        _ganhos
            .map { lista -> lista.sumOf { it.valor } }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                0.0
            )

    val totalGastos: StateFlow<Double> =
        _gastos
            .map { lista -> lista.sumOf { it.valor } }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                0.0
            )

    val saldoDisponivel: StateFlow<Double> =
        combine(totalGanhos, totalGastos) { ganhos, gastos ->
            ganhos - gastos
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0
        )

    fun adicionarGanho(descricao: String, valor: Double) {
        _ganhos.value = _ganhos.value + FinanceItem(descricao, valor)
    }

    fun adicionarGasto(descricao: String, valor: Double) {
        _gastos.value = _gastos.value + FinanceItem(descricao, valor)
    }


    fun salvarSonho(sonho: Sonho) {
        _sonho.value = sonho
    }

}