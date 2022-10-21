package model;

public class Product {

    public Product(final String nome, final String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Product(){

    }

    private Integer id;
    private String nome;
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
