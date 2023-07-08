/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Student;
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
 * Classe que define o objeto Estudante
 * Implementa a Interface Student
 * Extends a classe Participante
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Estudante extends Participante implements Student{
    /**
     * Variável que guarda o número do estudante
     */
    private int number;
    /**
     * Variável que guarda o número de notas do estudante
     */
    private int numNotas;
    /**
     * Array que guarda as notas do estudante
     */
    private int[] notas;
    /**
     * Variável que guarda a média do estudante
     */
    private float media;
    
    /**
     * Método construtor para o objeto Estudante
     * @param name Nome do estudante
     * @param email Email do estudante
     * @param instituition Instituição do estudante
     * @param contact Contacto do estudante
     * @param number Número do estudante
     */
    public Estudante(String name, String email, Instituition instituition, Contact contact, int number) {
        super(name, email, instituition, contact);
        this.number = number;
        this.numNotas = 0;
        this.notas = new int[10];
        this.media = 0;
    }

    /**
     * Método construtor para o objeto Estudante
     * @param name Nome do estudante
     * @param email Email do estudante
     * @param number Número do estudante
     */
    public Estudante(String name, String email, int number) {
        super(name, email);
        this.number = number;
        this.numNotas = 0;
        this.notas = new int[10];
        this.media = 0;
    }
    
    /**
     * Metodo que retorna o número do estudante
     * @return Número do estudante
     */
    @Override
    public int getNumber() {
        return number;
    }
    
    /**
     * Metodo que define o número do estudante
     * @param number Número do estudante
     */
    public void setMedia(float media){
        this.media = media;
    }
    
    /**
     * Metodo que retorna a média do estudante
     * @return Média do estudante
     */
    public float getMedia(){
        return media;
    }
    
    /**
     * Metodo que transforma o objeto numa String
     * @return
     */
    @Override
    public String toString() {
        return "Estudante{" + super.toString() + ", number=" + number + '}';
    }
    
    /**
     * Método que adiciona uma nota ao estudante
     * @param nota Nota a adicionar
     */
    public void addNota(int nota){
        notas[numNotas] = nota;
        numNotas++;
    }
    
    /**
     * Método que calcula a média das notas do estudante
     */
    public void mediaDasNotas(){
        int soma = 0;
        if(this.numNotas != 0){
            for(int nota : notas){
                soma += nota;
            }
            this.setMedia(soma/this.numNotas);
        }
    }
   
}
