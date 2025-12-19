package Modelo;

public class Cliente {
    
    // Atributos (Espelham as colunas da tabela 'cliente' do banco)
    private int codigo; // Corresponde ao idcliente
    private String nome;
    private String rg;
    private String cpf;
    private String telefone;
    private String email;
    private String nascimento; // O campo que estava faltando o Getter/Setter
    
    // Campos de Endereço
    private String rua;
    private int numero;
    private String bairro;
    private String cep;

    // --- GETTERS E SETTERS ---
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // --- AQUI ESTAVA O PROBLEMA: ADICIONADOS OS MÉTODOS DE NASCIMENTO ---
    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    // --------------------------------------------------------------------

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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
}