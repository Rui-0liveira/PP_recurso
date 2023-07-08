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
 * Nome: Rodrigo Bamdé Chantre Lopes
 * Número: 8210191
 * Turma: T4
 * 
 * Nome: Rui Alexande da Silva Oliveira
 * Número: 8210322
 * Turma: T3
 */

/**
 * Classe que define o objeto Projeto
 * Implementa a interface Project
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Projeto implements Project{
    /**
     * Variavel que guarda o nome do projeto
     */
    private String name;
    /**
     * Variavel que guarda a descrição do projeto
     */
    private String description;
    /**
     * Variavel que guarda o número de participantes do projeto
     */
    private int numParticipants;
    /**
     * Variavel que guarda o array de participantes do projeto
     */
    private Participante[] participants;
    /**
     * Variavel que guarda o número de estudantes do projeto
     */
    private int numStudents;
    /**
     * Variavel que guarda o número de partners do projeto
     */
    private int numPartners;
    /**
     * Variavel que guarda o número de facilitadores do projeto
     */
    private int numFacilitators;
    /**
     * Variavel que guarda o número de tarefas do projeto
     */
    private int numTasks;
    /**
     * Variavel que guarda o número total de tarefas concluidas do projeto
     */
    private int totalTasksCompleted;
    /**
     * Variavel que guarda o array de tarefas do projeto
     */
    private Tarefas[] tasks;
    /**
     * Variavel que guarda o número máximo de tarefas do projeto
     */
    private int maxTasks;
    /**
     * Variavel que guarda o número máximo de participantes do projeto
     */
    private long maxParticipants;
    /**
     * Variavel que guarda o número máximo de partners do projeto
     */
    private int maxPartners;
    /**
     * Variavel que guarda o número máximo de estudantes do projeto
     */
    private int maxStudents;
    /**
     * Variavel que guarda o número máximo de facilitadores do projeto
     */
    private int maxFacilitators;
    /**
     * Variavel que guarda as tags do projeto
     */
    private String[] tags = new String[1];
    /**
     * Variavel que guarda se o projeto está completo
     */
    private boolean completed;
    
    /**
     * Método construtor para o objeto Projeto
     * @param name nome do projeto
     * @param description descrição do projeto
     * @param tags tags do projeto
     * @param maxStudents numero maximo de estudantes
     * @param maxPartners numero maximo de parceiros
     * @param maxFacilitators numero maximo de facilitadores
     */
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
    
    /**
     * Metodo que retorna o nome do projeto
     * @return Nome do projeto
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Metodo que retorna a descrição do projeto
     * @return Descrição do projeto
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Metodo que retorna o número de participantes do projeto
     * @return Número de participantes do projeto
     */
    @Override
    public int getNumberOfParticipants() {
        return numParticipants;
    }

    /**
     * Metodo que retorna o número de estudantes do projeto
     * @return Número de estudantes do projeto
     */
    @Override
    public int getNumberOfStudents() {
        return numStudents;
    }

    /**
     * Metodo que retorna o número de partners do projeto
     * @return Número de partners do projeto
     */
    @Override
    public int getNumberOfPartners() {
        return numPartners;
    }

    /**
     * Metodo que retorna o número de facilitadores do projeto
     * @return Número de facilitadores do projeto
     */
    @Override
    public int getNumberOfFacilitators() {
        return numFacilitators;
    }

    /**
     * Metodo que retorna o número de tarefas do projeto
     * @return Número de tarefas do projeto
     */
    @Override
    public int getNumberOfTasks() {
        return numTasks;
    }

    /**
     * Metodo que retorna o número máximo de tarefas do projeto
     * @return Número máximo de tarefas do projeto
     */
    @Override
    public int getMaximumNumberOfTasks() {
        return maxTasks;
    }

    /**
     * Metodo que retorna o número máximo de participantes do projeto
     * @return Número máximo de participantes do projeto
     */
    @Override
    public long getMaximumNumberOfParticipants() {
        return maxParticipants;
    }
    
    /**
     * Metodo que retorna os participantes do projeto
     * @return Array de participantes do projeto
     */
    public Participant[] getParticipants(){
        return participants;
    }

    /**
     * Metodo que retorna o número máximo de estudantes do projeto
     * @return Número máximo de estudantes do projeto
     */
    @Override
    public int getMaximumNumberOfStudents() {
        return maxStudents;
    }

    /**
     * Metodo que retorna o número máximo de partners do projeto
     * @return Número máximo de partners do projeto
     */
    @Override
    public int getMaximumNumberOfPartners() {
       return maxPartners;
    }

    /**
     * Metodo que retorna o número máximo de facilitadores do projeto
     * @return Número máximo de facilitadores do projeto
     */
    @Override
    public int getMaximumNumberOfFacilitators() {
        return maxFacilitators;
    }
    
    /**
     * Metodo que adiciona um participante ao projeto
     * @param p Participante a adicionar
     * @throws IllegalNumberOfParticipantType Exceção que indica que o número máximo de participantes foi atingido
     * @throws ParticipantAlreadyInProject Exceção que indica que o participante já se encontra no projeto
     */
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

    /**
     * Metodo que remove um participante do projeto
     * @param string Email do participante a remover
     * @return Participante removido
     * @throws IllegalArgumentException Exceção que indica que o participante não existe
     */
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

    /**
     * Metodo que organiza o array de participantes
     * @param pos Posição do array
     */
    private void organizar_array(int pos){
        
        if(pos > maxParticipants){
            throw new IllegalArgumentException("Posição do array inválida");
        }
        for(int i = pos; i < numParticipants; i++){
            participants[i] = participants[i+1];
        }
    }
    
    /**
     * Metodo que retorna o array de participantes
     * @return Array de participantes
     * @throws IllegalNumberOfParticipantType Exceção que indica que o número máximo de participantes foi atingido
     */
    @Override
    public Participant getParticipant(String string) {
        for(int i = 0; i < numParticipants; i++){
            if(participants[i].getEmail().equals(string)){
                return participants[i];
            }
        }
        throw new IllegalArgumentException("Participante não existe");
    }

    /**
     * Metodo que retorna as tags
     * @return Tags do projeto
     */
    @Override
    public String[] getTags() {
        return tags;
    }

    /**
     * Metodo que verifica se o projeto tem uma determinada tag
     * @param string Tag a verificar
     * @return Booleano que indica se o projeto tem a tag
     */
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

    /**
     * Metodo que adiciona uma tarefa ao projeto
     * @param task Tarefa a adicionar
     * @throws IllegalNumberOfTasks Exceção que indica que o número máximo de tarefas foi atingido
     * @throws TaskAlreadyInProject Exceção que indica que a tarefa já se encontra no projeto
     */
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

    /**
     * Metodo que retorna uma tarefa do projeto com um determinado título
     * @param string Título da tarefa
     * @return Tarefa com o título
     */
    @Override
    public Task getTask(String string) {
        for(int i = 0; i < numTasks; i++){
            if(tasks[i].getTitle().equals(string)){
                return tasks[i];
            }
        }
        return null;
    }

    /**
     * Metodo que retorna o array de tarefas do projeto
     * @return Array de tarefas do projeto
     */
    @Override
    public Task[] getTasks() {
        return tasks;
    }

    /**
     * Metodo que verifica se a tarefa ja está completa
     * @return Booleano que indica se a tarefa está completa
     */
    @Override
    public boolean isCompleted() {
        for(int i = 0; i < numTasks; i++){
            if(tasks[i].getNumberOfSubmissions() > 0){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodo que verifica se o projeto tem um determinado participante
     * @param email Email do participante
     * @return Booleano que indica se o projeto tem o participante
     */
    public boolean hasParticipant(String email){
        for(int i = 0; i < numParticipants; i++){
            if(participants[i].getEmail().equals(email)){
                return true;
            }              
        }
        return false;
    }
    
    /**
     * Metodo que retorna uma String com a informação do projeto
     */
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

   /**
    * Metodo que aumenta o array de Participantes
    */
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

    /**
     * Metodo que define o nome do projeto
     * @param name Nome do projeto
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodo que define a descrição do projeto
     * @param description Descrição do projeto
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Metodo que define o número de participantes do projeto
     * @param numParticipants Número de participantes do projeto
     */
    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    /**
     * Metodo que define o array de participantes do projeto
     * @param participants Array de participantes do projeto
     */
    public void setParticipants(Participante[] participants) {
        this.participants = participants;
    }

    /**
     * Metodo que define o número de estudantes do projeto
     * @param numStudents Número de estudantes do projeto
     */
    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    /**
     * Metodo que define o número de partners do projeto
     * @param numPartners Número de partners do projeto
     */
    public void setNumPartners(int numPartners) {
        this.numPartners = numPartners;
    }

    /**
     * Metodo que define o número de facilitadores do projeto
     * @param numFacilitators Número de facilitadores do projeto
     */
    public void setNumFacilitators(int numFacilitators) {
        this.numFacilitators = numFacilitators;
    }

    /**
     * Metodo que define o número de tarefas do projeto
     * @param numTasks Número de tarefas do projeto
     */
    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }

    /**
     * Metodo que define o número máximo de tarefas do projeto
     * @param maxTasks Número máximo de tarefas do projeto
     */
    public void setTotalTasksCompleted(int totalTasksCompleted) {
        this.totalTasksCompleted = totalTasksCompleted;
    }

    /**
     * Metodo que define o array de tarefas do projeto
     * @param tasks Array de tarefas do projeto
     */
    public void setTasks(Tarefas[] tasks) {
        this.tasks = tasks;
    }

    /**
     * Metodo que define o número máximo de tarefas do projeto
     * @param maxTasks Número máximo de tarefas do projeto
     */
    public void setMaxTasks(int maxTasks) {
        this.maxTasks = maxTasks;
    }

    /**
     * Metodo que define o número máximo de participantes do projeto
     * @param maxParticipants Número máximo de participantes do projeto
     */
    public void setMaxParticipants(long maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    /**
     * Metodo que define o número máximo de parceiros do projeto
     * @param maxPartners Número máximo de parceiros do projeto
     */
    public void setMaxPartners(int maxPartners) {
        this.maxPartners = maxPartners;
    }

    /**
     * Metodo que define o número máximo de estudantes do projeto
     * @param maxStudents Número máximo de estudantes do projeto
     */
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    /**
     * Metodo que define o número máximo de facilitadores do projeto
     * @param maxFacilitators Número máximo de facilitadores do projeto
     */
    public void setMaxFacilitators(int maxFacilitators) {
        this.maxFacilitators = maxFacilitators;
    }

    /**
     * Metodo que define as tags do projeto
     * @param tags Tags do projeto
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    /**
     * Metodo que define se o projeto está completo
     * @param completed Booleano que indica se o projeto está completo
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

