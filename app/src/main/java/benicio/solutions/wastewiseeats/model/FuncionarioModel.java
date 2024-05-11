package benicio.solutions.wastewiseeats.model;

import java.io.Serializable;

public class FuncionarioModel implements Serializable {
    String nome, cargo, telefone, docuemnto, nomeRestaurante;


    public FuncionarioModel() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDocuemnto() {
        return docuemnto;
    }

    public void setDocuemnto(String docuemnto) {
        this.docuemnto = docuemnto;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }
}
