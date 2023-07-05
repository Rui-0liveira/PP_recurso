/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Instituition;

/**
 *
 * @author Rui
 */
public class Facilitador extends Participante implements Facilitator{
    
    private String areaOfExpertise;
    
    public Facilitador(String name, String email, Instituition instituition, Contact contact,String areaOfExpertise) {
        super(name, email, instituition, contact);
        this.areaOfExpertise = areaOfExpertise;
    }

    @Override
    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    @Override
    public void setAreaOfExpertise(String string) {
        this.areaOfExpertise = string;
    }

    @Override
    public String toString() {
        return "Facilitador{" + super.toString() + ", Area de Expecialidade=" + areaOfExpertise +'}';
    }
}

