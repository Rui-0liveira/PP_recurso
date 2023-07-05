/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

import participantes.Estudante;

/**
 *
 * @author rodri
 */
public class Avaliacao {
    
    private Estudante student;
    private Projeto project;
    private int autoAvaliacao;
    private int heteroAvaliacao;
    private Classificacao classificacao;

    public Avaliacao(Estudante student, Projeto project, int autoAvaliacao, int heteroAvaliacao) {
        this.student = student;
        this.project = project;
        this.autoAvaliacao = autoAvaliacao;
        this.heteroAvaliacao = heteroAvaliacao;
    }

    public Avaliacao(Estudante student, Projeto project) {
        this.student = student;
        this.project = project;
        this.autoAvaliacao = 0;
        this.heteroAvaliacao = 0;
    }

    public int getAutoAvaliacao() {
        return autoAvaliacao;
    }

    public void setAutoAvaliacao(int autoAvaliacao) {
        this.autoAvaliacao = autoAvaliacao;
    }

    public int getHeteroAvaliacao() {
        return heteroAvaliacao;
    }

    public void setHeteroAvaliacao(int heteroAvaliacao) {
        this.heteroAvaliacao = heteroAvaliacao;
        setClassificacao(heteroAvaliacao);
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int nota) {
         if(nota <= 19 && nota >= 0){
            this.classificacao = Classificacao.FRACO;
        }else if(nota <=49 && nota >= 20){
            this.classificacao = Classificacao.INSUFICIENTE;
        }else if(nota <= 69 && nota >= 50){
            this.classificacao = Classificacao.SUFICIENTE;
        }else if(nota <= 89 && nota >= 70){
            this.classificacao = Classificacao.BOM;
        }else if(nota <= 100 && nota >= 90){
            this.classificacao = Classificacao.EXCELENTE;
        }else{
            throw new IllegalArgumentException("Nota fora da escala......Impossivel atribuir Rank");
        }
    }
    

    public Projeto getProjectAv(){
        return project;
    }
    
    public Estudante getStudent(){
        return student;
    }
    

}
