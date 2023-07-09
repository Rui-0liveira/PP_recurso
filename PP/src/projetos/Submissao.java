/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

import java.time.LocalDateTime;
import ma02_resources.participants.Student;
import ma02_resources.project.Status;
import ma02_resources.project.Submission;
import participantes.Estudante;

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
 * Classe que define o objeto Submissao
 * Implementa a interface Submission
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Submissao implements Submission{
    /**
     * Variável que guarda a data da submissão
     */
    private LocalDateTime date;
    /**
     * Variável que guarda o estudante que fez a submissão
     */
    private Estudante student;
    /**
     * Variável que guarda o texto da submissão
     */
    private String text;
    
    /**
     * Variavel que guarda a avaliação da submissão
     */
    private Avaliacao avaliacao;
    
    /**
     * Método construtor para o objeto Submissão
     * @param date Data da submissão
     * @param student Estudante que fez a submissão
     * @param text Texto da submissão
     */
    public Submissao(LocalDateTime date, Estudante student, String text) {
        this.date = date;
        this.student = student;
        this.text = text;
    }

    /**
     * Método que retorna a data da submissão
     * @return Data da submissão
     */
    @Override
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Método que retorna o estudante que fez a submissão
     * @return Estudante que fez a submissão
     */
    @Override
    public Student getStudent() {
        return student;
    }

    /**
     * Método que retorna o texto da submissão
     * @return Texto da submissão
     */
    @Override
    public String getText() {
        return text;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    /**
     * Método que compara duas submissões
     * @param sbmsn Submissão a comparar
     * @return 1 se a submissão for mais recente, -1 se for mais antiga
     */
    @Override
    public int compareTo(Submission sbmsn) {
        if(sbmsn.getDate().isBefore(date)){
            return 1;
        }
        return -1;
    }
    
    /**
     * Método que retorna a representação textual da submissão
     * @return Representação textual da submissão
     */
    @Override
    public String toString() {
        String string;
        string="date = " + date +
                "\nstudent: " + student.toString() +
                "\navaliação: ";
        if(avaliacao != null){
            string  += avaliacao.toString();
        }    
        string += "\ntext = " + text;                
                
        return string;
        
    }
    
}

