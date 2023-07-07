/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

import java.time.LocalDate;
import java.util.Objects;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;

/**
 *
 * @author Rui
 */
public class Tarefas implements Task{
    
    private LocalDate start;
    private LocalDate end;
    private int duration;
    private String title;
    private String description;
    private int numSubmissions;
    private Submissao[] submissions;
     /**
     * Booleano que indica se a tarefa está completa
     */
    private boolean completed;

    public Tarefas(LocalDate start, LocalDate end, int duration, String title, String description, int numSubmissions, Submissao[] submissions) {
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.title = title;
        this.description = description;
        this.numSubmissions = numSubmissions;
        this.submissions = submissions;
    }
    
    public Tarefas(LocalDate start, int duration, String title, String description){
        this.start = start;
        this.duration = duration;
        this.title = title;
        this.description = description;
        this.end = this.start.plusDays(duration);
        this.submissions = new Submissao[10];
        this.numSubmissions = 0;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Submission[] getSubmissions() {
        return submissions;
    }

    @Override
    public int getNumberOfSubmissions() {
        return numSubmissions;
    }

    @Override
    public void addSubmission(Submission sbmsn) {
        if(sbmsn == null){
            throw new IllegalArgumentException("Submission cannot be null.");
        }
        if(numSubmissions == submissions.length){
            Submission [] temp = new Submission[submissions.length * 2];
            for(int i = 0; i < submissions.length; i++){
                temp[i] = submissions[i];
            }
            submissions = (Submissao[]) temp;
        }
        submissions[numSubmissions] = (Submissao) sbmsn;
        numSubmissions++;
    }

    @Override
    public void extendDeadline(int i) {
        if(i < 0){
            throw new IllegalArgumentException("Number of days must be positive.");
        }
        end = end.plusDays(i);
    }

    @Override
    public int compareTo(Task task) {
        if(task.getStart().isBefore(start)){
            return 1;
        }
        return -1;
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
        final Tarefas other = (Tarefas) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        String string;
        string="start = " + start +
                "\nend = " + end +
                "\nduration = " + duration +
                "\ntitle = " + title +
                "\ndescription = " + description + 
                "\nsubmissions: ";
        if(numSubmissions == 0){
            string += "null";
        }
        else{   
            for (Submission submission : submissions) {
                string += submission.toString();
            }
        }
        string+=  "\nnumberOfSubmissions = " + numSubmissions +
                '}';
                
        return string;
    }
    
    /**
     * Metodo que retorna se a tarefa está completa
     * @return Hashcode da tarefa
     */
    public Boolean isCompleted(){
        return completed;
    }

    /**
     * Metodo que define se a tarefa está completa
     * @param completed Booleano que indica se a tarefa está completa
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumSubmissions(int numSubmissions) {
        this.numSubmissions = numSubmissions;
    }

    public void setSubmissions(Submissao[] submissions) {
        this.submissions = submissions;
    }
    
    
    
}

