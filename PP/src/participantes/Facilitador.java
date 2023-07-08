/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Instituition;

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
 * Classe que define o objeto Facilitador
 * Implementa a Interface Facilitaor
 * Extends a classe Participante
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Facilitador extends Participante implements Facilitator{
    /**
     * Variável que guarda a area de especialização do facilitador
     */
    private String areaOfExpertise;
    
    /**
     * Método construtor para o objeto Facilitador
     * @param name Nome do facilitador
     * @param email Email do facilitador
     * @param instituition Instituição do facilitador
     * @param contact Contacto do facilitador
     * @param areaOfExpertise Area de especialização do facilitador
     */
    public Facilitador(String name, String email, Instituition instituition, Contact contact,String areaOfExpertise) {
        super(name, email, instituition, contact);
        this.areaOfExpertise = areaOfExpertise;
    }

    /**
     * @return Retorna a area de especialização do facilitador
     */
    @Override
    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    /**
     * @param string Define a area de especialização do facilitador
     */
    @Override
    public void setAreaOfExpertise(String string) {
        this.areaOfExpertise = string;
    }

    /**
     * @return Retorna a informação do facilitador
     */
    @Override
    public String toString() {
        return "Facilitador{" + super.toString() + ", Area de Expecialidade=" + areaOfExpertise +'}';
    }
}

