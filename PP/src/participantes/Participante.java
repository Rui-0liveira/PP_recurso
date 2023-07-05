/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import java.util.Objects;
import ma02_resources.participants.*;

/**
 *
 * @author Rui
 */
public abstract class Participante implements Participant{
    
    private String name;
    private String email;
    private Contact contact;
    private Instituition instituition;

    public Participante(String name, String email, Instituition instituition, Contact contact) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.instituition = instituition;
    }

    public Participante(String name, String email) {
        this.name = name;
        this.email = email;
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
    
    @Override
    public String toString() {
        return "name=" + name + ", email=" + email + ", contact=" + contact.getPhone() + ", instituition=" + instituition.getName() ;
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
        final Participante other = (Participante) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}