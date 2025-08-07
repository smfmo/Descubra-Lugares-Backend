package com.github.smfmo.descubra_lugares.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lugar")
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "url")
    private String url;

    @Column(name = "avaliacao")
    private Integer avaliacao;

    public Lugar() {

    }

    public Lugar(String nome, Categoria categoria,
                 String localizacao, String url,
                 Integer avaliacao ) {

        this.nome = nome;
        this.categoria = categoria;
        this.localizacao = localizacao;
        this.url = url;
        this.avaliacao = avaliacao;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

}
