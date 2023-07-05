/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Partner;

/**
 *
 * @author Rui
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

    @Override
    public String getVat() {
        return vat;
    }

    @Override
    public String getWebsite() {
        return site;
    }
    
    @Override
    public String toString() {
        return "Parceiro{" + super.toString() + ", vat=" + vat + ", website=" + site +'}';
    }
}
