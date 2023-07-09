/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

/**
 * Nome: Rodrigo Bamdé Chantre Lopes
 * Número: 8210191
 * Turma: T4
 * 
 * Nome: Rui Alexande da Silva Oliveira
 * Número: 8210322
 * Turma: T3
 */

/**
 * Classe que define o objeto Avaliação
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Avaliacao {
    /**
     * Variavel que guarda uma autoAvaliação
     */
    private int autoAvaliacao;
    
    /**
     * Variavel que guarda uma heteroAvaliação
     */
    private int heteroAvaliacao;
    
    /**
     * Variavel que guarda uma classificação
     */
    private Classificacao classificacao;

    /**
     * Método construtor de uma Avaliação
     * @param autoAvaliacao 
     * @param heteroAvaliacao 
     */
    public Avaliacao(int autoAvaliacao, int heteroAvaliacao) {
        this.autoAvaliacao = autoAvaliacao;
        this.heteroAvaliacao = heteroAvaliacao;
    }

    /**
     * Metodo construtor de uma Avaliação
     */
    public Avaliacao() {
        this.autoAvaliacao = 0;
        this.heteroAvaliacao = 0;
    }

    /**
     * Método get da autoavaliação
     * @return a autoavaliaçãop
     */
    public int getAutoAvaliacao() {
        return autoAvaliacao;
    }
    
    /**
     * Método set autoAvaliação
     * @param autoAvaliacao uma autoAvaliação
     */
    public void setAutoAvaliacao(int autoAvaliacao) {
        this.autoAvaliacao = autoAvaliacao;
    }

    /**
     * Método get da HeteroAvaliação
     * @return a heteroAvaliação
     */
    public int getHeteroAvaliacao() {
        return heteroAvaliacao;
    }

    /**
     * Método set da HeteroAvaliação
     * @param heteroAvaliacao uma heteroAvaliação
     */
    public void setHeteroAvaliacao(int heteroAvaliacao) {
        this.heteroAvaliacao = heteroAvaliacao;
        setClassificacao(heteroAvaliacao);
    }

    /**
     * Método get da classificação
     * @return a classificação
     */
    public Classificacao getClassificacao() {
        return classificacao;
    }

    /**
     * Método set da classificação
     * @param nota heteroavaliação
     */
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

    /**
     * Método to string da avaliação
     * @return string com os dados da avaliação
     */
    @Override
    public String toString() {
        return "AutoAvaliação: " + autoAvaliacao +
                ", HeteroAvaliação: " + heteroAvaliacao +
                ", Classificação: " + classificacao;
    }
    
    
}
