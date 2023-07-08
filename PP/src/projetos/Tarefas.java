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
 * Nome: Rodrigo Bamdé Chantre Lopes
 * Número: 8210191
 * Turma: T4
 * 
 * Nome: Rui Alexande da Silva Oliveira
 * Número: 8210322
 * Turma: T3
 */

/**
 * Classe que define o objeto Tarefas
 * Implementa a Interface Task
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Tarefas implements Task{
    /**
     * Variável que guarda a data de início da tarefa
     */
    private LocalDate start;
    /**
     * Variável que guarda a data de fim da tarefa
     */
    private LocalDate end;
    /**
     * Variável que guarda a duração da tarefa
     */
    private int duration;
    /**
     * Variável que guarda o título da tarefa
     */
    private String title;
    /**
     * Variável que guarda a descrição da tarefa
     */
    private String description;
    /**
     * Variável que guarda o número de submissões da tarefa
     */
    private int numSubmissions;
    /**
     * Array que guarda as submissões da tarefa
     */
    private Submissao[] submissions;
     /**
     * Booleano que indica se a tarefa está completa
     */
    private boolean completed;

    /**
     * Método construtor para o objeto Tarefa
     * @param start Data de início da tarefa
     * @param end Data de fim da tarefa
     * @param duration Duração da tarefa
     * @param title Título da tarefa
     * @param description Descrição da tarefa
     * @param submissions Array de Submissões da tarefa
     * @param numberOfSubmissions Número de submissões da tarefa
     */
    public Tarefas(LocalDate start, int duration, String title, String description, int numSubmissions, Submissao[] submissions) {
        if (start.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data inválida");
        }
        this.start = start;
        this.end = this.start.plusDays(duration);
        this.duration = duration;
        this.title = title;
        this.description = description;
        this.numSubmissions = numSubmissions;
        this.submissions = submissions;
    }
    
    /**
     * Método construtor para o objeto Tarefa
     * @param start Data de início da tarefa
     * @param duration Duração da tarefa
     * @param title Título da tarefa
     * @param description Descrição da tarefa
     */
    public Tarefas(LocalDate start, int duration, String title, String description){
        this.start = start;
        this.duration = duration;
        this.title = title;
        this.description = description;
        this.end = this.start.plusDays(duration);
        this.submissions = new Submissao[10];
        this.numSubmissions = 0;
    }

    /**
     * Metodo que retorna a data de início da tarefa
     * @return Data de início da tarefa
     */
    @Override
    public LocalDate getStart() {
        return start;
    }

    /**
     * Metodo que retorna a data de fim da tarefa
     * @return Data de fim da tarefa
     */
    @Override
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Metodo que retorna a duração da tarefa
     * @return Duração da tarefa
     */
    @Override
    public int getDuration() {
        return duration;
    }

    /**
     * Metodo que retorna o título da tarefa
     * @return Título da tarefa
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Metodo que retorna a descrição da tarefa
     * @return Descrição da tarefa
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Metodo que retorna o array de submissões da tarefa
     * @return Array de submissões da tarefa
     */
    @Override
    public Submission[] getSubmissions() {
        return submissions;
    }

    /**
     * Metodo que retorna o número de submissões da tarefa
     * @return Número de submissões da tarefa
     */
    @Override
    public int getNumberOfSubmissions() {
        return numSubmissions;
    }

    /**
     * Metodo que adiciona uma submissão à tarefa
     * @param sbmsn Submissão a adicionar
     * @throws IllegalArgumentException "Submission cannot be null." se a submissão for nula
     */
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

    /**
     * Metodo que estende o prazo da tarefa pelo número de dias dado
     * @param i Número de dias a adicionar
     * @throws IllegalArgumentException "Number of days must be positive." se o número de dias for negativo
     */
    @Override
    public void extendDeadline(int i) {
        if(i < 0){
            throw new IllegalArgumentException("Number of days must be positive.");
        }
        end = end.plusDays(i);
    }

    /**
     * Metodo que compara duas tarefas pela sua data de início
     * @param task Tarefa a comparar
     * @return -1, 1 se a tarefa for menor, igual ou maior que a tarefa especificada
     */
    @Override
    public int compareTo(Task task) {
        if(task.getStart().isBefore(start)){
            return 1;
        }
        return -1;
    }
    
    /**
     * Metodo que compara dois objetos
     * @param obj Objeto a comparar
     * @return true se os objetos forem iguais, false se não forem
     */
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
    
    /**
     * Metodo que retorna uma string com a informação da tarefa
     */
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

    /**
     * Metodo que defone a data de início da tarefa
     * @param start Data de início da tarefa
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    /**
     * Metodo que define a data de fim da tarefa
     * @param end Data de fim da tarefa
     */
    public void setEnd(LocalDate end) {
        this.end = end;
    }

    /**
     * Metodo que define a duração da tarefa
     * @param duration Duração da tarefa
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Metodo que define o título da tarefa
     * @param title Título da tarefa
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Metodo que define a descrição da tarefa
     * @param description Descrição da tarefa
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Metodo que define o número de submissões da tarefa
     * @param numSubmissions Número de submissões da tarefa
     */
    public void setNumSubmissions(int numSubmissions) {
        this.numSubmissions = numSubmissions;
    }

    /**
     * Metodo que define o array de submissões da tarefa
     * @param submissions Array de submissões da tarefa
     */
    public void setSubmissions(Submissao[] submissions) {
        this.submissions = submissions;
    }
    
    
    
}

