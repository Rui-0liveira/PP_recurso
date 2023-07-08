/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Partner;

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
 * Classe que define o objeto Parceiro
 * Implementa a Interface Partner
 * Extends a classe Participante
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Parceiro extends Participante implements Partner{
    
    private String vat;
    
    private String site;
    
    public Parceiro(String name, String email, Instituition instituition, Contact contact, String vat, String website) {
        super(name, email, instituition, contact);
        this.vat = vat;
        this.site = website;
    }
    
    public Parceiro(String name, String email, String vat, String website) {
        super(name, email);
        this.vat = vat;
        this.site = website;
    }

    /**
     * Metodo que retorna o VAT do parceiro
     * @return VAT do parceiro
     */
    @Override
    public String getVat() {
        return vat;
    }

    /**
     * Metodo que retorna o website do parceiro
     * @return Website do parceiro
     */
    @Override
    public String getWebsite() {
        return site;
    }
    
    /**
     * Metodo que retorna o nome do parceiro
     * @return Nome do parceiro
     */
    @Override
    public String toString() {
        return "Parceiro{" + super.toString() + ", vat=" + vat + ", website=" + site +'}';
    }
}
