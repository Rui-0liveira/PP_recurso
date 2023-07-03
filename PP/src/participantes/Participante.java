/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

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
}