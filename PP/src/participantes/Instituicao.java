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
 *
 * @author Rui
 */
public class Instituicao implements Instituition{
    
    private String name;
    private String email;
    private InstituitionType type;
    private Contact contact;
    private String site;
    private String description;

    public Instituicao(String name, String email, InstituitionType type, Contact contact, String site, String description) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.contact = contact;
        this.site = site;
        this.description = description;
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
    public InstituitionType getType() {
        return type;
    }

    @Override
    public Contact getContact() {
        return contact;
    }

    @Override
    public String getWebsite() {
        return site;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setWebsite(String string) {
        this.site = string;
    }

    @Override
    public void setDescription(String string) {
        this.description = string;
    }

    @Override
    public void setContact(Contact cntct) {
        this.contact = cntct;
    }

    @Override
    public void setType(InstituitionType it) {
        this.type = it;
    }
    
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
    
}

