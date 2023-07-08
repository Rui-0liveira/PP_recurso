/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import java.util.Objects;
import ma02_resources.participants.*;
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
 * Classe Abstrata que define o objeto Participante
 * Implementa a Interface Participant
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public abstract class Participante implements Participant{
    
    private String name;
    
    private String email;
    
    private Contact contact;
    
    private Instituition instituition;

    /**
     * Método construtor para o objeto Participante
     * @param name Nome do participante
     * @param email Email do participante
     * @param instituition Instituição do participante
     * @param contact Contacto do participante
     */
    public Participante(String name, String email, Instituition instituition, Contact contact) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.instituition = instituition;
    }

    /**
     * Método construtor para o objeto Participante
     * @param name Nome do participante
     * @param email Email do participante
     */
    public Participante(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    /**
     * metodo que retorna o nome do participante
     * @return nome do participante
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * metodo que retorna o email do participante
     * @return email do participante
     */
    @Override
    public String getEmail() {
        return email;
    }
    
    /**
     * metodo que retorna o contacto do participante
     * @return contacto do participante
     */
    @Override
    public Contact getContact() {
        return contact;
    }
    
    /**
     * metodo que retorna a instituição do participante
     * @return instituição do participante
     */
    @Override
    public Instituition getInstituition() {
        return instituition;
    }
    
    /**
     * metodo que define a instituição do participante
     * @param instn instituição do participante
     */
    @Override
    public void setInstituition(Instituition instn) {
        this.instituition = instn;
    }

    /**
     * metodo que define o contacto do participante
     * @param cntct contacto do participante
     */
    @Override
    public void setContact(Contact cntct) {
        this.contact = cntct;
    }
    
    /**
     * metodo que retorna uma string com a informação do objeto
     * @return string com a informação do objeto
     */
    @Override
    public String toString() {
        return "name=" + name + ", email=" + email + ", contact=" + contact.getPhone() + ", instituition=" + instituition.getName() ;
    }

     /**
     * metodo que compara dois objetos
     * @param obj objeto a comparar
     * @return true se os objetos forem iguais, false se forem diferentes
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Participante other = (Participante) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}