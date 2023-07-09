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
     * Metodo que transforma o objeto numa String
     * @return
     */
    @Override
    public String toString() {
        return "Estudante{" + super.toString() + ", number=" + number + '}';
    }
}
