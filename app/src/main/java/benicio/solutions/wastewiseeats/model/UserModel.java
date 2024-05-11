package benicio.solutions.wastewiseeats.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    String
            email = "",
            nome = "",
            senha = "",
            proprietario = "",
            documento = "",
            tele = "",
            horarioInicio = "",
            horarioFim = "",
            horarioRetirada = "",
            documentoRestaturante = "",
            rua = "",
            numero = "",
            bairro = "",
            cep = "",
            cidade = "",
            estado = "";

    int tipoConta = 0;  // 0 centro 1 proprietario 2 funcionario

    public UserModel() {
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getHorarioRetirada() {
        return horarioRetirada;
    }

    public void setHorarioRetirada(String horarioRetirada) {
        this.horarioRetirada = horarioRetirada;
    }

    public String getDocumentoRestaturante() {
        return documentoRestaturante;
    }

    public void setDocumentoRestaturante(String documentoRestaturante) {
        this.documentoRestaturante = documentoRestaturante;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
