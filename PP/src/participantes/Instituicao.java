/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import java.util.Objects;
import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.InstituitionType;
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
 * Classe que define o objeto Instituicao
 * Implementa a Interface Instituition
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Instituicao implements Instituition{
    /**
     * Variável que guarda o nome da instituição
     */
    private String name;
    /**
     * Variável que guarda o email da instituição
     */
    private String email;
    /**
     * Variável que guarda o tipo de instituição
     */
    private InstituitionType type;
    /**
     * Variável que guarda o contacto da instituição
     */
    private Contact contact;
    /**
     * Variável que guarda o website da instituição
     */
    private String site;
    /**
     * Variável que guarda a descrição da instituição
     */
    private String description;

    /**
     * Método construtor para o objeto Instituição
     * @param name Nome da instituição
     * @param email Email da instituição
     * @param type Tipo de instituição
     * @param contact Contacto da instituição
     * @param website Website da instituição
     * @param description Descrição da instituição
     */
    public Instituicao(String name, String email, InstituitionType type, Contact contact, String site, String description) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.contact = contact;
        this.site = site;
        this.description = description;
    }

    public Instituicao() {
        
    }

     /**
     * Metodo que retorna o nome da instituição
     * @return Nome da instituição
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Metodo que retorna o email da instituição
     * @return Email da instituição
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Metodo que retorna o tipo de instituição
     * @return Tipo de instituição
     */
    @Override
    public InstituitionType getType() {
        return type;
    }

    /**
     * Metodo que retorna o contacto da instituição
     * @return Contacto da instituição
     */
    @Override
    public Contact getContact() {
        return contact;
    }

    /**
     * Metodo que retorna o website da instituição
     * @return Website da instituição
     */
    @Override
    public String getWebsite() {
        return site;
    }

     /**
     * Metodo que retorna a descrição da instituição
     * @return Descrição da instituição
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Metodo que define o website da instituição
     * @param string website da instituição
     */
    @Override
    public void setWebsite(String string) {
        this.site = string;
    }

    /**
     * Metodo que define a descrição da instituição
     * @param string Descrição da instituição
     */
    @Override
    public void setDescription(String string) {
        this.description = string;
    }

    /**
     * Metodo que define o contacto da instituição
     * @param cntct contacto da instituição
     */
    @Override
    public void setContact(Contact cntct) {
        this.contact = cntct;
    }

    /**
     * Metodo que define o tipo de instituição
     * @param it Tipo de instituição
     */
    @Override
    public void setType(InstituitionType it) {
        this.type = it;
    }
    
    /**
     * Metodo que compara dois objetos
     * @param obj Ojeto a ser comparado
     * @return True se os objetos forem iguais, false se não forem
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
        final Instituicao other = (Instituicao) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
    
}

