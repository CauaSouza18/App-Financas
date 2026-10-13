package model;

import java.time.LocalDate;

public class Ganho {
    private int id;
    private  double valor;
    private String plataforma;
    private  LocalDate Cadastrado_Em;
    public double getValor() {
        return valor;
    }
    public String getPlataforma() {
        return plataforma;
    }
    public Ganho(int id, double valor, String plataforma, LocalDate Cadastrado_Em) {
        this.id = id;
        this.valor = valor;
        this.plataforma = plataforma;
        this.Cadastrado_Em = Cadastrado_Em;
    }
    public LocalDate getCadastrado_Em() {
        return Cadastrado_Em;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    public void setCadastrado_Em(LocalDate Cadastrado_Em) {
        this.Cadastrado_Em = Cadastrado_Em;
    }
    public int getId() {    
        return id;
    }
}
