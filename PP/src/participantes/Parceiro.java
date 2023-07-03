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
    private String name;
    private String email;
    private Contact contact;
    private Instituition instituition;
    
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
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Contact getContact() {
        return contact;
    }

    @Override
    public Instituition getInstituition() {
        return instituition;
    }

    @Override
    public void setInstituition(Instituition instn) {
        this.instituition = instn;
    }

    @Override
    public void setContact(Contact cntct) {
        this.contact = cntct;
    }
    
}
