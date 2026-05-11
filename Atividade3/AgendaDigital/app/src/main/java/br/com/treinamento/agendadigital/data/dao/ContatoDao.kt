package br.com.treinamento.agendadigital.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.treinamento.agendadigital.data.entity.ContatoEntity

@Dao
interface ContatoDao {

    //cadastrar contato
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contato: ContatoEntity)

    //alterar contato
    @Update()
   fun update(contato: ContatoEntity)

    // remover contato
    @Delete()
   fun delete(contato: ContatoEntity)

    //selecionar contato
    @Query("SELECT * FROM contatos")
    fun select(): LiveData<List<ContatoEntity>>

    @Query("SELECT * FROM contatos WHERE id = :id")
    fun getById(id: Int): LiveData<ContatoEntity>

    @Query("SELECT * FROM contatos WHERE id = :id LIMIT 1")
    fun get(id: Int): ContatoEntity

}