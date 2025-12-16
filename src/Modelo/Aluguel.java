package Modelo;

public class Aluguel {
    private int codigo;
    private int cod_dvd;
    private int cod_cliente;
    private String horario;
    private String data_aluguel;
    private String data_devolucao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Atenção: O nome deste método deve bater com o DAO (getCod_dvd)
    public int getCod_dvd() {
        return cod_dvd;
    }

    public void setCod_dvd(int cod_dvd) {
        this.cod_dvd = cod_dvd;
    }

    // Atenção: O nome deste método deve bater com o DAO (getCod_cliente)
    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getData_aluguel() {
        return data_aluguel;
    }

    public void setData_aluguel(String data_aluguel) {
        this.data_aluguel = data_aluguel;
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}