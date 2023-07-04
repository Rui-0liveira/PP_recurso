/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

import participantes.Estudante;

/**
 *
 * @author rodri
 */
public class Avaliacao {
    
    private Estudante student;
    private Projeto project;
    private int autoAvaliacao;
    private int heteroAvaliacao;

    public Avaliacao(Estudante student, Projeto project, int autoAvaliacao, int heteroAvaliacao) {
        this.student = student;
        this.project = project;
        this.autoAvaliacao = autoAvaliacao;
        this.heteroAvaliacao = heteroAvaliacao;
    }

    public Avaliacao(Estudante student, Projeto project) {
        this.student = student;
        this.project = project;
        this.autoAvaliacao = 0;
        this.heteroAvaliacao = 0;
    }

    public int getAutoAvaliacao() {
        return autoAvaliacao;
    }

    public void setAutoAvaliacao(int autoAvaliacao) {
        this.autoAvaliacao = autoAvaliacao;
    }

    public int getHeteroAvaliacao() {
        return heteroAvaliacao;
    }

    public void setHeteroAvaliacao(int heteroAvaliacao) {
        this.heteroAvaliacao = heteroAvaliacao;
    }
    
    public Projeto getProjectAv(){
        return project;
    }
    
    public Estudante getStudent(){
        return student;
    }
    
}
