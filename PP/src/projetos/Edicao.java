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

/**
 * Nome: Rodrigo Bamdé Chantre Lopes Número: 8210191 Turma: T4
 *
 * Nome: Rui Alexande da Silva Oliveira Número: 8210322 Turma: T3
 */
/**
 * Classe que define uma Edição Implementa a interface Edition
 *
 * @author Rodrigo Lopes
 * @author Rui Oliveira
 */
public class Edicao implements Edition {

    /**
     * Variavel que guarda o nome da edição
     */
    private String name;
    /**
     * Variavel que guarda a data de inicio da edição
     */
    private LocalDate start;
    /**
     * Variavel que guarda o template da edição
     */
    private String template;
    /**
     * Variavel que guarda o estado da edição
     */
    private Status status;
    /**
     * Variavel que guarda o numero de projetos da edição
     */
    private int numProjects;
    /**
     * Variavel que guarda os projetos da edição
     */
    private Projeto[] projects;

    /**
     * Método construtor para o objeto Edição
     *
     * @param name Nome da edição
     * @param start Data de início da edição
     * @param template Template da edição
     */
    public Edicao(String name, LocalDate start, String template) {
        if (start.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data inválida");
        } else if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.name = name;
        this.start = start;
        this.template = template;
        this.status = Status.INACTIVE;
        this.numProjects = 0;
        this.projects = new Projeto[10];
    }

    /**
     * Método que retorna o nome da edição
     *
     * @return Nome da edição
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Método que retorna a data de início da edição
     *
     * @return Data de início da edição
     */
    @Override
    public LocalDate getStart() {
        return start;
    }

    /**
     * Método que retorna o template da edição
     *
     * @return Template da edição
     */
    @Override
    public String getProjectTemplate() {
        return template;
    }

    /**
     * Método que retorna o estado da edição
     *
     * @return Estado da edição
     */
    @Override
    public Status getStatus() {
        return status;
    }

    /**
     * Método que define o estado da edição
     *
     * @param status Estado da edição
     */
    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Método que adiciona um projeto à edição, com base no template
     *
     * @param string Nome do projeto
     * @param string1 Descrição do projeto
     * @param strings Tags do projeto
     * @throws IOException se o projeto não existir
     * @throws ParseException se o template do projeto não for valido
     */
    @Override
    public void addProject(String string, String string1, String[] strings) throws IOException, ParseException {
        int nStudents;
        int nPartners;
        int nFacilitators;

        try {
            String jsonFilePath = template;
            BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(reader);

            Number n = (Number) obj.get("number_of_facilitors");
            nFacilitators = n.intValue();
            n = (Number) obj.get("number_of_students");
            nStudents = n.intValue();
            n = (Number) obj.get("number_of_partners");
            nPartners = n.intValue();

            Projeto novoProjeto = new Projeto(string, string1, strings, nStudents, nPartners, nFacilitators);

            JSONArray tasks = (JSONArray) obj.get("tasks");
            for (Object o : tasks) {
                if (o instanceof JSONObject) {
                    JSONObject task = (JSONObject) o;
                    String title = (String) task.get("title");
                    String description = (String) task.get("description");
                    n = (Number) task.get("duration");
                    int duration = n.intValue();
                    LocalDate start = this.getStart().plusDays((long) task.get("start_at"));
                    Task novaTask = new Tarefas(start, duration, title, description);
                    novoProjeto.addTask(novaTask);
                }
            }

            if (projects.length == numProjects) {
                aumentarProjetos();
            }

            this.projects[numProjects] = novoProjeto;
            numProjects++;

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(Edicao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalNumberOfTasks ex) {
            Logger.getLogger(Edicao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TaskAlreadyInProject ex) {
            Logger.getLogger(Edicao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que remove um projeto da edição
     *
     * @param string Nome do projeto
     */
    @Override
    public void removeProject(String string) {

        if (!projectExists(string)) {
            throw new IllegalArgumentException("Projeto não existe");
        }
        for (int i = 0; i < numProjects; i++) {
            if (projects[i].getName().equals(string)) {
                for (int j = i; j < numProjects; j++) {

                    projects[j] = projects[j + 1];
                    if (j == numProjects - 1) {
                        projects[numProjects] = null;
                    }
                }
            }
        }
        numProjects--;
    }

    /**
     * Metodo que retorna um projeto da edição
     *
     * @param string nome do Projeto a retornar
     * @return Um projeto da edição
     */
    @Override
    public Projeto getProject(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Nome de projeto inválido");
        }
        for (int i = 0; i < numProjects; i++) {
            if (projects[i] != null && projects[i].getName().equals(string)) {
                return projects[i];
            }
        }
        throw new IllegalArgumentException("Projeto não encontrado na edição");
    }

    /**
     * Método que retorna todos os projetos da edição
     *
     * @return Array de projetos da edição
     */
    @Override
    public Project[] getProjects() {
        return projects;
    }

    /**
     * Método que retorna todos os projetos da edição com uma determinada tag
     *
     * @param string Tag a procurar
     * @return Array de projetos da edição com uma determinada tag
     */
    @Override
    public Project[] getProjectsByTag(String string) {
        Project[] projectsByTag = new Project[numProjects];
        int count = 0;
        for (int i = 0; i < numProjects; i++) {
            if (projects[i].hasTag(string)) {
                projectsByTag[count] = projects[i];
                count++;
            } else {
                throw new IllegalArgumentException("Não existe nenhum projeto com esta tag");
            }
        }
        return projectsByTag;
    }

    /**
     * Método que retorna todos os projetos da edição com um determinado
     * participante
     *
     * @param string Participante a procurar
     * @return Array de projetos da edição com um determinado participante
     */
    @Override
    public Project[] getProjectsOf(String string) {
        Projeto[] temp = null;
        int size = 0;
        for (int i = 0; i < numProjects; i++) {
            if (projects[i].hasParticipant(string)) {
                temp[size] = projects[i];
                size++;
            }
        }
        return temp;
    }

    /**
     * Método que retorna o número de projetos da edição
     *
     * @return Número de projetos da edição
     */
    @Override
    public int getNumberOfProjects() {
        return numProjects;
    }

    /**
     * Método que retorna a data de fim da edição
     *
     * @return Data de fim da edição
     */
    @Override
    public LocalDate getEnd() {
        LocalDate temp = LocalDate.of(1, 1, 1);
        for (Projeto p : projects) {
            if (p != null) {
                for (Task t : p.getTasks()) {
                    if (t != null && t.getEnd().isAfter(temp)) {
                        temp = t.getEnd();
                    }
                }
            }
        }
        return temp;
    }

    /**
     * Método toString da edição
     *
     * @return uma string com todos os dados da edição
     */
    @Override
    public String toString() {
        String string;
        string = " name = " + name
                + "\n start = " + start
                + "\n template = " + template
                + "\n status = " + status
                + "\n numOfProjects = " + numProjects
                + "\n projects: ";
        if (numProjects == 0) {
            string += "null";

        } else {
            for (int i = 0; i < numProjects; i++) {
                if (projects[i] != null) {
                    string += "\n{\n" + projects[i].toString() + "\n}";
                }

            }
        }

        return string;
    }

    /**
     * Metodo que verifica se um projeto existe na edição
     *
     * @param project nome do projeto a verificar
     * @return true se o projeto existir, false se não existir
     */
    public boolean projectExists(String project) {
        for (Projeto p : projects) {
            if (p.getName().equals(project)) {
                return true;
            }
        }
        return false;
    }

    public void autoAvaliacao(Avaliacao av, int nota) {
        if (this.getStatus() == Status.ACTIVE) {
            for (int i = 0; i < this.numProjects; i++) {
                Projeto project = (Projeto) this.getProjects()[i];
                for (int j = 0; j < project.getNumberOfTasks(); j++) {
                    Tarefas task = (Tarefas) project.getTasks()[j];
                    for (int k = 0; k < task.getNumberOfSubmissions(); k++) {
                        if (task.getSubmissions()[k].equals(av.getSubmission())) {
                            if (task.getSubmissions()[k].getStudent().equals(av.getStudent())) {
                                av.setAutoAvaliacao(nota);
                            } else {
                                throw new IllegalArgumentException("Estudante não fez essa submissão....");
                            }
                        } else {
                            throw new IllegalArgumentException("Submissão não encontrada nesta ediçaõ de CBL....");
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Ediçaõ não está ativa....");
        }
    }

    public void heteroAvaliacao(Avaliacao av, int nota) {
        if (this.getStatus() == Status.ACTIVE) {
            for (int i = 0; i < this.numProjects; i++) {
                Projeto project = (Projeto) this.getProjects()[i];
                for (int j = 0; j < project.getNumberOfTasks(); j++) {
                    Tarefas task = (Tarefas) project.getTasks()[j];
                    for (int k = 0; k < task.getNumberOfSubmissions(); k++) {
                        if (task.getSubmissions()[k].equals(av.getSubmission())) {
                            if (task.getSubmissions()[k].getStudent().equals(av.getStudent())) {
                                av.setAutoAvaliacao(nota);
                                av.getStudent().addNota(nota);
                            } else {
                                throw new IllegalArgumentException("Estudante não fez essa submissão....");
                            }
                        } else {
                            throw new IllegalArgumentException("Submissão não encontrada nesta ediçaõ de CBL....");
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Ediçaõ não está ativa....");
        }
    }

    /**
     * Metodo que aumenta tamanho do array de projetos
     */
    public void aumentarProjetos() {
        Projeto[] temp = new Projeto[getNumberOfProjects() + 5];
        for (int i = 0; i < projects.length; i++) {
            temp[i] = projects[i];
        }
        projects = temp;
    }

    /**
     * Metodo que imprime a media de tempo de conclusão das tasks dos projetos
     */
    public void listarMediaDurationTask() {
        for (int i = 0; i < this.getNumberOfProjects(); i++) {
            if (this.getProjects()[i] instanceof Projeto) {
                Projeto project = (Projeto) this.getProjects()[i];
                project.mediaTempoTasks();
            }
        }
    }

    /**
     * Metodo set do nome
     *
     * @param name nome da edição
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodo set da data de inicio
     *
     * @param start data de inicio da edição
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    /**
     * Metodo set da template
     *
     * @param template template da edição
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * Metodo set do numero de projetos
     *
     * @param numProjects numero de projetos
     */
    public void setNumProjects(int numProjects) {
        this.numProjects = numProjects;
    }

    /**
     * ~Metodo set de projetos
     *
     * @param projects aray de projetos
     */
    public void setProjects(Projeto[] projects) {
        this.projects = projects;
    }

    /**
     * Metoo que adiciona projetos lidos pelo json
     *
     * @param project projeto a ser adicionado
     */
    public void addProjectJS(Project project) {
        if (numProjects == projects.length) {
            int newCapacity = projects.length * 2;
            Projeto[] newProjects = new Projeto[newCapacity];

            System.arraycopy(projects, 0, newProjects, 0, numProjects);

            projects = newProjects;
        }

        projects[numProjects] = (Projeto) project;
        numProjects++;
    }

}
