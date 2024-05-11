package benicio.solutions.wastewiseeats.model;

import java.io.Serializable;

public class DoacaoModel implements Serializable {
    String emailDoador;

    String idDoacao, nome, data, aceitacao, listaAlimentos, horarioRetirada;

    public DoacaoModel() {
    }

    public String getEmailDoador() {
        return emailDoador;
    }

    public void setEmailDoador(String emailDoador) {
        this.emailDoador = emailDoador;
    }

    public String getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(String idDoacao) {
        this.idDoacao = idDoacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAceitacao() {
        return aceitacao;
    }

    public void setAceitacao(String aceitacao) {
        this.aceitacao = aceitacao;
    }

    public String getListaAlimentos() {
        return listaAlimentos;
    }

    public void setListaAlimentos(String listaAlimentos) {
        this.listaAlimentos = listaAlimentos;
    }

    public String getHorarioRetirada() {
        return horarioRetirada;
    }

    public void setHorarioRetirada(String horarioRetirada) {
        this.horarioRetirada = horarioRetirada;
    }
}
