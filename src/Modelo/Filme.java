package Modelo;

public class Filme {
    private int codigo;
    private String titulo;
    private int ano;
    private String duracao;
    private int cod_categoria;
    private int cod_classificacao;
    private String capa; // Caminho da imagem

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public String getDuracao() { return duracao; }
    public void setDuracao(String duracao) { this.duracao = duracao; }
    public int getCod_categoria() { return cod_categoria; }
    public void setCod_categoria(int cod_categoria) { this.cod_categoria = cod_categoria; }
    public int getCod_classificacao() { return cod_classificacao; }
    public void setCod_classificacao(int cod_classificacao) { this.cod_classificacao = cod_classificacao; }
    public String getCapa() { return capa; }
    public void setCapa(String capa) { this.capa = capa; }
}