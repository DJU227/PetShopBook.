package org.example.petshopbook.Model;

import java.time.LocalDate;

public class Cliente {
    private int idCliente;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String statusProcura;

    // Construtor
    public Cliente(int idCliente, String nome, String cpf, String telefone, String endereco, String statusProcura) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.statusProcura = statusProcura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    public String getStatusProcura() {
        return statusProcura;
    }

    public void setStatusProcura(String statusProcura) {
        this.statusProcura = statusProcura;
    }
}
