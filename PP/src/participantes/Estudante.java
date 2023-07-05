/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package participantes;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Student;

/**
 *
 * @author Rui
 */
public class Estudante extends Participante implements Student{
    
    private int number;
    private String name;
    private String email;
    private Contact contact;
    private Instituition instituition;
    private int numNotas;
    private int[] notas;
    private float media;
    
    public Estudante(String name, String email, Instituition instituition, Contact contact, int number) {
        super(name, email, instituition, contact);
        this.number = number;
        this.numNotas = 0;
        this.notas = new int[10];
        this.media = 0;
    }

    public Estudante(String name, String email, int number) {
        super(name, email);
        this.number = number;
        this.numNotas = 0;
        this.notas = new int[10];
        this.media = 0;
    }
    
    @Override
    public int getNumber() {
        return number;
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
    
    public void setMedia(float media){
        this.media = media;
    }
    
    public float getMedia(){
        return media;
    }
    
    @Override
    public String toString() {
        return "StudentClass{" + "name=" + name + ", email=" + email + ", instituition=" + instituition + ", contact=" + contact + ", number=" + number + '}';
    }
    
    public void addNota(int nota){
        notas[numNotas] = nota;
        numNotas++;
    }
    
    public void mediaDasNotas(){
        int soma = 0;
        if(this.numNotas != 0){
            for(int nota : notas){
                soma += nota;
            }
            this.setMedia(soma/this.numNotas);
        }
    }
}
