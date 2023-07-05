/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma02_resources.project.*;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import participantes.Estudante;
import participantes.Participante;

/**
 *
 * @author Rui
 */
public class Edicao implements Edition{

    private String name;
    private LocalDate start;
    private String template;
    private Status status;
    private int numProjects;
    private Projeto[] projects;

    public Edicao(String name, LocalDate start, String template) {
        if(start.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Data inválida");
        }
        else if(name == null || name.equals("")){
            throw new IllegalArgumentException("Nome inválido");
        }
        this.name = name;
        this.start = start;
        this.template = template;
        this.status = Status.INACTIVE;
        this.numProjects = 0;
        this.projects = new Projeto[numProjects];
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public String getProjectTemplate() {
        return template;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void addProject(String string, String string1, String[] strings) throws IOException, ParseException {
        int nStudents;
        int nPartners;
        int nFacilitators;
        
        try{
            String jsonFilePath = template;
            BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(reader);
            
            Number n =(Number)obj.get("number_of_facilitors");
            nFacilitators = n.intValue();
            n =(Number)obj.get("number_of_students");
            nStudents = n.intValue();
            n =(Number)obj.get("number_of_partners");
            nPartners = n.intValue();

            Projeto novoProjeto = new Projeto(string, string1, strings, nStudents, nPartners, nFacilitators);

            JSONArray tasks = (JSONArray) obj.get("tasks");
            for(Object o : tasks){
                if(o instanceof JSONObject){
                    JSONObject task = (JSONObject)o;
                    String title = (String)task.get("title");
                    String description = (String)task.get("description");
                    n =(Number)task.get("duration");
                    int duration = n.intValue();
                    LocalDate start = this.getStart().plusDays((long)task.get("start_at"));
                    Task novaTask = new Tarefas(start, duration, title, description);
                    novoProjeto.addTask(novaTask);
                }
                
            }

            this.projects[numProjects] = novoProjeto;
            numProjects++;

            reader.close();
            
        }catch(IOException e){
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(Edicao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalNumberOfTasks ex) {
            Logger.getLogger(Edicao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TaskAlreadyInProject ex) {
            Logger.getLogger(Edicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(numProjects == projects.length){
            Projeto[] temp = new Projeto [numProjects+5]; 
            for(int i = 0; i < numProjects; i++){
                temp[i] = projects[i];
            }
            projects = temp;
        }
    }

    @Override
    public void removeProject(String string) {
        for(int i = 0; i < numProjects; i++){
            if(projects[i].getName().equals(string)){
                for(int j = i; j < numProjects; j++){
                    projects[j] = projects[j + 1];
                    if(j == numProjects - 1){
                        projects[numProjects] = null;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Projeto não existe");
    }

    @Override
    public Project getProject(String string) {
        if(string == null){
            throw new IllegalArgumentException("Nome de projeto invalido");  
        }
        for (int i = 0; i < numProjects; i++) {
            if (projects[i].getName().equals(string)) {
                return projects[i];
            }
        }
        throw new IllegalArgumentException("Projeto não encontrado na edição");
    }

    @Override
    public Project[] getProjects() {
        return projects;
    }

    @Override
    public Project[] getProjectsByTag(String string) {
        Project[] projectsByTag = null;
        int count = 0;
        for(int i = 0; i < numProjects; i++){
            if(projects[i].hasTag(string)){
                projectsByTag[count] = projects[i];
                count++;
            }
        }
        return projectsByTag;
    }

    @Override
    public Project[] getProjectsOf(String string) {
        Projeto[] temp = null;
        int size = 0; 
        for(int i = 0; i < numProjects; i++){
            if(projects[i].hasParticipant(string)){
                temp[size] = projects[i];
                size++;
            }
        }
        return temp;
    }

    @Override
    public int getNumberOfProjects() {
        return numProjects;
    }

    @Override
    public LocalDate getEnd() {
        LocalDate  temp = LocalDate.of(1, 1, 1);
        for(Projeto p: projects){
            if(p!= null){
                for(Task t: p.getTasks()){
                    if(t!=null && t.getEnd().isAfter(temp)){
                        temp = t.getEnd();
                    }
                }
            }
        }
        return temp;
    }
    
    @Override
    public String toString() {
        String string;
        string = " name = " + name + 
                "\n start = " + start +
                "\n template = " + template  +
                "\n status = " + status +
                "\n numOfProjects = " + numProjects +
                "\n projects: "; 
        if(numProjects == 0){
            string += "null";
        }
        else{
            for(int i = 0; i < numProjects; i++){
                string+= "\n{\n" + projects[i].toString() + "\n}";
            }
        }
        
        return string;
    }
 
     /**
     * Metodo que verifica se um projeto existe na edição
     * @param project nome do projeto a verificar
     * @return true se o projeto existir, false se não existir
     */
    public boolean projectExists(String project){
        for(Projeto p: projects){
            if(p.getName().equals(project)){
                return true;
            }
        }
        return false;
    }
    
    public void autoAvaliacao(Avaliacao av, int nota){
        if(this.getStatus() == Status.ACTIVE){
            Projeto projeto = (Projeto) this.getProject(av.getProjectAv().getName());
            if (projeto != null) {
                Estudante estudante = (Estudante) projeto.getParticipant(av.getStudent().getEmail()); 
                if (estudante != null) {
                    av.setAutoAvaliacao(nota);
                }else{
                    throw new IllegalArgumentException("Estudante não se encontra no Projeto....");
                }
            }else{
                throw new IllegalArgumentException("Projeto não se enconta na Edição....");
            }
        }else{
            throw new IllegalArgumentException("Ediçaõ não está ativa....");
        }
    }
    
    public void heteroAvaliacao(Avaliacao av, int nota){
        if(this.getStatus().equals(Status.ACTIVE)){
            Projeto projeto = (Projeto) this.getProject(av.getProjectAv().getName());
            if (projeto != null) {
                Estudante estudante = (Estudante) projeto.getParticipant(av.getStudent().getEmail()); 
                if (estudante != null) {
                    av.setHeteroAvaliacao(nota);
                    estudante.addNota(nota);
                }else{
                    throw new IllegalArgumentException("Estudante não se encontra no Projeto....");
                }
            }else{
                throw new IllegalArgumentException("Projeto não se enconta na Edição....");
            }
        }else{
            throw new IllegalArgumentException("Ediçaõ não está ativa....");
        }
    }
    
}

