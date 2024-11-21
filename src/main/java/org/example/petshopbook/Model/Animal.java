package org.example.petshopbook.Model;

public class Animal {
    private int idAnimal;
    private String nome;
    private String tipo;
    private int idade;
    private String sexo;
    private String raca;
    private String descricao;
    private String status;
    private String imagem; // Caminho para a imagem
    private int idAnunciante;

    // Construtor
    public Animal(int idAnimal, String nome, String tipo, int idade, String sexo, String raca, String descricao, String status, String imagem, int idAnunciante) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.tipo = tipo;
        this.idade = idade;
        this.sexo = sexo;
        this.raca = raca;
        this.descricao = descricao;
        this.status = status;
        this.imagem = imagem;
        this.idAnunciante = idAnunciante;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(int idAnunciante) {
        this.idAnunciante = idAnunciante;
    }
}
