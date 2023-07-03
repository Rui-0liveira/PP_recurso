/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

import java.time.LocalDateTime;
import ma02_resources.participants.Student;
import ma02_resources.project.Submission;
import participantes.Estudante;

/**
 *
 * @author Rui
 */
public class Submissao implements Submission{
    
    private LocalDateTime date;
    private Estudante student;
    private String text;

    public Submissao(LocalDateTime date, Estudante student, String text) {
        this.date = date;
        this.student = student;
        this.text = text;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int compareTo(Submission sbmsn) {
        if(sbmsn.getDate().isBefore(date)){
            return 1;
        }
        return -1;
    }
    
    @Override
    public String toString() {
        String string;
        string="date = " + date +
                "\nstudent: " + student.toString() +
                "\ntext = " + text;                
                
        return string;
        
    }
    
}

