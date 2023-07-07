/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Partner;
import ma02_resources.participants.Student;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import participantes.*;

/**
 *
 * @author Rui
 */
public class Projeto implements Project{

    private String name;
    private String description;
    private int numParticipants;
    private Participante[] participants;
    private int numStudents;
    private int numPartners;
    private int numFacilitators;
    private int numTasks;
    private int totalTasksCompleted;
    private Tarefas[] tasks;
    private int maxTasks;
    private long maxParticipants;
    private int maxPartners;
    private int maxStudents;
    private int maxFacilitators;
    private String[] tags = new String[1];

    
    public Projeto(String name, String description, String[] tags, int maxStudents, int maxPartners, int maxFacilitators){
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.maxStudents = maxStudents;
        this.maxPartners = maxPartners;
        this.maxFacilitators = maxFacilitators;
        maxParticipants = maxFacilitators + maxStudents + maxPartners;
        numParticipants = 0;
        numStudents = 0;
        numPartners = 0;
        numFacilitators = 0;
        numTasks = 0;
        totalTasksCompleted = 0;
        participants = new Participante [5];
        maxTasks = 30;
        tasks = new Tarefas[maxTasks];
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getNumberOfParticipants() {
        return numParticipants;
    }

    @Override
    public int getNumberOfStudents() {
        return numStudents;
    }

    @Override
    public int getNumberOfPartners() {
        return numPartners;
    }

    @Override
    public int getNumberOfFacilitators() {
        return numFacilitators;
    }

    @Override
    public int getNumberOfTasks() {
        return numTasks;
    }

    @Override
    public int getMaximumNumberOfTasks() {
        return maxTasks;
    }

    @Override
    public long getMaximumNumberOfParticipants() {
        return maxParticipants;
    }
    
    public Participant[] getParticipants(){
        return participants;
    }

    @Override
    public int getMaximumNumberOfStudents() {
        return maxStudents;
    }

    @Override
    public int getMaximumNumberOfPartners() {
       return maxPartners;
    }

    @Override
    public int getMaximumNumberOfFacilitators() {
        return maxFacilitators;
    }
    
    @Override
    public void addParticipant(Participant p) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        if(numParticipants == maxParticipants){
            throw new IllegalNumberOfParticipantType("Limite de participantes já atingido");
        }
        if(p instanceof Partner){
            if(maxPartners == numPartners){
                throw new IllegalNumberOfParticipantType("Limite de parceiros já atingido");
            }
            numPartners++;
        }  
        if(p instanceof Student){
            if(maxStudents == numStudents){
                throw new IllegalNumberOfParticipantType("Limite de estudantes já atingido");
            }
            numStudents++;
        }
        if(p instanceof Facilitator){
            if( maxFacilitators == numFacilitators){
                throw new IllegalNumberOfParticipantType("Limite de facilitatores já atingido");
            }
            numFacilitators++;
        }
        for(int i = 0; i < numParticipants; i++){
            if(participants[i].equals(p)){
                throw new ParticipantAlreadyInProject("Participante já se encontra neste projeto");
            }
        }
        if(numParticipants == participants.length){
            aumentarParticipantes();
        }
        participants[numParticipants] = (Participante) p;
        numParticipants++;
    }

    @Override
    public Participant removeParticipant(String string) {
        Participant temp;
        for(int i = 0; i < numParticipants; i++){
            if(participants[i].getEmail().equals(string)){
                temp = participants[i];
                organizar_array(i);
                participants[numParticipants] = null;
                numParticipants--;
                if(temp instanceof Partner){
                    numPartners--;
                }else if(temp instanceof Student){
                    numStudents--;
                }else if(temp instanceof Facilitator){
                    numFacilitators--;
                }

                return temp;
            }
        }
        throw new IllegalArgumentException("Participante não existe");
    }

    private void organizar_array(int pos){
        
        if(pos > maxParticipants){
            throw new IllegalArgumentException("Posição do array inválida");
        }
        for(int i = pos; i < numParticipants; i++){
            participants[i] = participants[i+1];
        }
    }
    
    @Override
    public Participant getParticipant(String string) {
        for(int i = 0; i < numParticipants; i++){
            if(participants[i].getEmail().equals(string)){
                return participants[i];
            }
        }
        throw new IllegalArgumentException("Participante não existe");
    }

    @Override
    public String[] getTags() {
        return tags;
    }

    @Override
    public boolean hasTag(String string) {
        for (String tag : tags) {
            if(tag != null){
                if (tag.equals(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void addTask(Task task) throws IllegalNumberOfTasks, TaskAlreadyInProject {
        if(numTasks == maxTasks){
            throw new IllegalNumberOfTasks("Limite de tasks já atingido");
        }
        for(int i = 0; i < numTasks; i++){
            if(tasks[i].equals(task)){
                throw new TaskAlreadyInProject("Task já se encontra neste projeto");
            }
        }
        tasks[numTasks] = (Tarefas)task;
        numTasks++;
    }

    @Override
    public Task getTask(String string) {
        for(int i = 0; i < numTasks; i++){
            if(tasks[i].getTitle().equals(string)){
                return tasks[i];
            }
        }
        return null;
    }

    @Override
    public Task[] getTasks() {
        return tasks;
    }

    @Override
    public boolean isCompleted() {
        for(int i = 0; i < numTasks; i++){
            if(tasks[i].getNumberOfSubmissions() > 0){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasParticipant(String email){
        for(int i = 0; i < numParticipants; i++){
            if(participants[i].getEmail().equals(email)){
                return true;
            }              
        }
        return false;
    }
    
    @Override
    public String toString() {
        String string;
        string = " name = " + name +  
                "\n description = " + description +
                "\n tags =  ";
        for (String tag : tags) {
            string += tag + "  ";
        }
        string += "\n numberOfParticipants = " + numParticipants +
                "\n numberOfStudents = " + numStudents +
                "\n numberOfPartners = " + numPartners +
                "\n numberOfFacilitators = " + numFacilitators +
                "\n maxParticipants = " + maxParticipants +
                "\n maxStudents = " + maxStudents +
                "\n maxPartners = " + maxPartners +
                "\n maxFacilitators = " + maxFacilitators +
                "\n participants: ";
        if(numParticipants == 0){
            string += "null";
        }
        for(int i = 0; i < numParticipants; i++){
            string += "\n{\n"  + participants[i].toString()+ "\n}";
        }
        
        string +="\n maxTasks = " + maxTasks + 
                "\n numberOfTasks = " + numTasks +
                "\n tasks: " ;
        if(numTasks == 0){
            string += "null";
        }
        for(int i = 0; i < numTasks; i++){
            string += "\n{\n"  + tasks[i].toString()+ "\n}";
        }
        return  string;
                
    }
    
    /**
     * Metodo que retorna o numero de submissões do das tasks do projeto
     * @return Número de submissões do das tasks do projeto
     */
    public int getNumSubmissionTask() {
        int numTasks = 0;
        for(int i = 0; i < numTasks; i++){
            if(tasks[i].getNumberOfSubmissions() > 0){
                numTasks = numTasks + tasks[i].getNumberOfSubmissions();
            }
        }
        return numTasks;
    }
   
    /**
     * Metodo que retorna a media de task concluidas do projeto
     * @return Média de task concluidas do projeto
     */
    public double getProjectProgress(){
    //verificar se a task está completa baseado pelo tempo
        for(int i = 0; i < numTasks; i++){
            if(this.getTasks()[i].getEnd().isBefore(java.time.LocalDate.now())){
                if(this.getTasks()[i] instanceof Tarefas){
                    Tarefas task = (Tarefas) this.getTasks()[i];
                    task.setCompleted(true);
                    totalTasksCompleted++;
                }
            }
        }
        double progress;
        if(totalTasksCompleted == 0){
           progress = 0;
        }
        else{
            progress = maxTasks / totalTasksCompleted * 100;     
        }
       
        return progress ;

   }

    public void aumentarParticipantes() {
        Participante[] temp = new Participante[getNumberOfParticipants() + 5];
        for (int i = 0; i < participants.length; i++) {
            temp[i] = participants[i];
        }
        participants = temp;
    }
    
    /**
     * Metodo que imprime a media de tempo de conclusão das tasks de um projeto
     */
    public void mediaTempoTasks(){
        float media = 0;
        float soma = 0;
        if(this.getNumberOfTasks() <= 0){
        for(int i = 0; i < this.numTasks; i++){
            soma += this.tasks[i].getDuration();
        }
        media = soma / this.numTasks;
        System.out.println("A media de tempo das tasks é: " + media);
        }
        else{
           System.out.println("Não há tasks"); 
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    public void setParticipants(Participante[] participants) {
        this.participants = participants;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public void setNumPartners(int numPartners) {
        this.numPartners = numPartners;
    }

    public void setNumFacilitators(int numFacilitators) {
        this.numFacilitators = numFacilitators;
    }

    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }

    public void setTotalTasksCompleted(int totalTasksCompleted) {
        this.totalTasksCompleted = totalTasksCompleted;
    }

    public void setTasks(Tarefas[] tasks) {
        this.tasks = tasks;
    }

    public void setMaxTasks(int maxTasks) {
        this.maxTasks = maxTasks;
    }

    public void setMaxParticipants(long maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setMaxPartners(int maxPartners) {
        this.maxPartners = maxPartners;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public void setMaxFacilitators(int maxFacilitators) {
        this.maxFacilitators = maxFacilitators;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    
}

