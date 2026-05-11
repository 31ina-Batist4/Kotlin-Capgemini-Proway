package br.com.treinamento.agendadigital.mapper

import br.com.treinamento.agendadigital.data.entity.ContatoEntity
import br.com.treinamento.agendadigital.model.Contato
import br.com.treinamento.agendadigital.model.Endereco

fun ContatoEntity.toModel(): Contato {

    return Contato(
        id = this.id,
        nome = this.nome,
        email = this.email,
        telefone = this.telefone,
        nascimento = this.nascimento,
        endereco = Endereco(
            cep = this.cep,
            bairro = this.bairro,
            logradouro = this.logradouro,
            numero = this.numero,
            estado = this.estado,
            cidade = this.cidade
        )
    )
}


fun Contato.toEntity(): ContatoEntity {
    return ContatoEntity(
        id = this.id,
        nome = this.nome,
        email = this.email,
        telefone = this.telefone,
        nascimento = this.nascimento,
        cep = this.endereco.cep,
        bairro = this.endereco.bairro,
        logradouro = this.endereco.logradouro,
        numero = this.endereco.numero,
        estado = this.endereco.estado,
        cidade = this.endereco.cidade
    )
}
