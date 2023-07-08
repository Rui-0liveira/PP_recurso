/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import ma02_resources.participants.Contact;
import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Partner;
import ma02_resources.participants.Student;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import participantes.Estudante;
import participantes.Facilitador;
import participantes.Parceiro;
import participantes.Participante;
/**
 * Nome: Rodrigo Bamdé Chantre Lopes
 * Número: 8210191
 * Turma: T4
 * 
 * Nome: Rui Alexande da Silva Oliveira
 * Número: 8210322
 * Turma: T3
 */

public class CBL {

    private Edicao[] editions;
    private int numEditions;

    /**
     * Metodo construtor do CBL
     */
    public CBL() {
        numEditions = 0;
        editions = new Edicao[1];
    }

    public void gerarJSON() {
        JSONObject cbl = new JSONObject();
        cbl.put("numEditions", this.numEditions);
        JSONArray editions = new JSONArray();

        for (Edicao e : this.editions) {
            if(e != null){
                JSONObject edicao = new JSONObject();
                edicao.put("name", e.getName());
                edicao.put("start", e.getStart().toString());
                edicao.put("template", e.getProjectTemplate());
                edicao.put("status", e.getStatus().toString());
                edicao.put("numOfProjects", e.getNumberOfProjects());
                JSONArray projects = new JSONArray();

                for (Project p : e.getProjects()) {
                    if (p != null) {
                        JSONObject projeto = new JSONObject();
                        projeto.put("Nome ", p.getName());
                        projeto.put("Descrição ", p.getDescription());
                        projeto.put("Tags ", p.getTags());
                        projeto.put("Número de participantes ", p.getNumberOfParticipants());
                        projeto.put("Número de estudantes ", p.getNumberOfStudents());
                        projeto.put("Número de partners ", p.getNumberOfPartners());
                        projeto.put("Número de facilitadores ", p.getNumberOfFacilitators());
                        projeto.put("Número de tarefas ", p.getNumberOfTasks());
                        projeto.put("Número máximo de tarefas ", p.getMaximumNumberOfTasks());
                        projeto.put("Número máximo de participantes ", p.getMaximumNumberOfParticipants());
                        projeto.put("Número máximo de estudantes ", p.getMaximumNumberOfStudents());
                        projeto.put("Número máximo de partners ", p.getMaximumNumberOfPartners());
                        projeto.put("Número máximo de facilitadores ", p.getMaximumNumberOfFacilitators());
                        JSONArray tasks = new JSONArray();
                        JSONArray participants = new JSONArray();
                        JSONArray tags = new JSONArray();

                        for (Task t : p.getTasks()) {
                            if(t != null){
                                JSONObject task = new JSONObject();
                                task.put("title", t.getTitle());
                                task.put("description", t.getDescription());
                                task.put("start", t.getStart().toString());
                                task.put("end", t.getEnd().toString());
                                task.put("duration", t.getDuration());
                                task.put("completed", ((Tarefas) t).isCompleted());
                                task.put("numberOfSubmissions", t.getNumberOfSubmissions());
                                JSONArray submissions = new JSONArray();

                                for (Submission sub : t.getSubmissions()) {
                                    if(sub!=null){
                                        JSONObject submissao = new JSONObject();
                                        submissao.put("date", sub.getDate().toString());
                                        submissao.put("student", sub.getStudent().toString());
                                        submissao.put("text", sub.getText());
                                        submissions.add(submissao);
                                    }
                                }
                                for (Participant par : ((Projeto) p).getParticipants()) {
                                    if(par != null){
                                        JSONObject participante = new JSONObject();
                                        participante.put("name", par.getName());
                                        participante.put("email", par.getEmail());
                                        participante.put("instituition", par.getInstituition().toString());
                                        participante.put("contact", par.getContact().toString());
                                        participants.add(participante);
                                        if (par instanceof Student) {
                                            Estudante student = (Estudante) par;
                                            participante.put("numero", student.getNumber());
                                        } else if (par instanceof Facilitator) {
                                            Facilitador facilitator = (Facilitador) par;
                                            participante.put("Area de Especialização", facilitator.getAreaOfExpertise());
                                        } else if (par instanceof Partner) {
                                            Parceiro partner = (Parceiro) par;
                                            participante.put("VAT", partner.getVat());
                                            participante.put("WebSite", partner.getWebsite());
                                        }
                                        JSONObject contacto = new JSONObject();
                                        contacto.put("street", ((Participante) par).getContact().getStreet());
                                        contacto.put("city", ((Participante) par).getContact().getCity());
                                        contacto.put("state", ((Participante) par).getContact().getState());
                                        contacto.put("zipcode", ((Participante) par).getContact().getZipCode());
                                        contacto.put("country", ((Participante) par).getContact().getCountry());
                                        contacto.put("phone", ((Participante) par).getContact().getPhone());

                                        JSONObject instituicao = new JSONObject();
                                        instituicao.put("name", ((Participante) par).getInstituition().getName());
                                        instituicao.put("email", ((Participante) par).getInstituition().getEmail());
                                        instituicao.put("type", ((Participante) par).getInstituition().getType().toString());
                                        instituicao.put("contact", contacto);
                                        instituicao.put("website", ((Participante) par).getInstituition().getWebsite());
                                        instituicao.put("description", ((Participante) par).getInstituition().getDescription());
                                    }
                                }
                            }
                        }
                        for (String tag : p.getTags()) {
                            tags.add(tag);
                        }
                    }
                }
            }
        }
        try {

            FileWriter file = new FileWriter("data.json");
            file.write(cbl.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerJSON() throws org.json.simple.parser.ParseException, IllegalNumberOfTasks, TaskAlreadyInProject, IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        JSONParser parser = new JSONParser();

        try {
            FileReader file = new FileReader("data.json");
            JSONObject cbl = (JSONObject) parser.parse(file);
            int numEditions = ((Long) cbl.get("numEditions")).intValue();
            JSONArray editions = (JSONArray) cbl.get("editions");

            for (Object obj : editions) {
                JSONObject edicao = (JSONObject) obj;
                String name = (String) edicao.get("name");
                String start = (String) edicao.get("start");
                String template = (String) edicao.get("template");
                String status = (String) edicao.get("status");
                int numOfProjects = ((Long) edicao.get("numOfProjects")).intValue();
                JSONArray projects = (JSONArray) edicao.get("projects");

                Edicao edicaoObj = new Edicao(name, LocalDate.parse(start), template);
                edicaoObj.setStatus(Status.valueOf(status));
                ((Edicao) edicaoObj).setNumProjects(numOfProjects);

                for (Object objProjeto : projects) {
                    JSONObject projeto = (JSONObject) objProjeto;
                    String nome = (String) projeto.get("Nome");
                    String descricao = (String) projeto.get("Descrição");
                    JSONArray tagsArray = (JSONArray) projeto.get("Tags");
                    String[] tags = new String[tagsArray.size()];
                    for (int i = 0; i < tagsArray.size(); i++) {
                        tags[i] = (String) tagsArray.get(i);
                    }
                    int numParticipantes = ((Long) projeto.get("Número de participantes")).intValue();
                    int numEstudantes = ((Long) projeto.get("Número de estudantes")).intValue();
                    int numPartners = ((Long) projeto.get("Número de partners")).intValue();
                    int numFacilitadores = ((Long) projeto.get("Número de facilitadores")).intValue();
                    int numTarefas = ((Long) projeto.get("Número de tarefas")).intValue();
                    int maxNumTarefas = ((Long) projeto.get("Número máximo de tarefas")).intValue();
                    int maxNumParticipantes = ((Long) projeto.get("Número máximo de participantes")).intValue();
                    int maxNumEstudantes = ((Long) projeto.get("Número máximo de estudantes")).intValue();
                    int maxNumPartners = ((Long) projeto.get("Número máximo de partners")).intValue();
                    int maxNumFacilitadores = ((Long) projeto.get("Número máximo de facilitadores")).intValue();
                    JSONArray tasks = (JSONArray) projeto.get("tasks");
                    JSONArray participants = (JSONArray) projeto.get("participants");

                    Projeto projetoObj = new Projeto(nome, descricao, tags, maxNumEstudantes, maxNumPartners, maxNumFacilitadores);
                    projetoObj.setNumParticipants(numParticipantes);
                    projetoObj.setNumStudents(numEstudantes);
                    projetoObj.setNumPartners(numPartners);
                    projetoObj.setNumFacilitators(numFacilitadores);
                    projetoObj.setNumTasks(numTarefas);
                    projetoObj.setMaxTasks(maxNumTarefas);
                    projetoObj.setMaxParticipants(maxNumParticipantes);

                    for (Object objTarefa : tasks) {
                        JSONObject task = (JSONObject) objTarefa;
                        String title = (String) task.get("title");
                        String description = (String) task.get("description");
                        String taskStart = (String) task.get("start");
                        String taskEnd = (String) task.get("end");
                        int duration = ((Long) task.get("duration")).intValue();
                        boolean completed = (boolean) task.get("completed");
                        int numberOfSubmissions = ((Long) task.get("numberOfSubmissions")).intValue();
                        JSONArray submissions = (JSONArray) task.get("submissions");

                        Tarefas taskObj = new Tarefas(LocalDate.parse(taskStart), duration, title, description);
                        taskObj.setEnd(LocalDate.parse(taskEnd));
                        taskObj.setCompleted(completed);
                        taskObj.setNumSubmissions(numberOfSubmissions);
                        projetoObj.addTask(taskObj);

                        for (Object objSubmissao : submissions) {
                            JSONObject submissao = (JSONObject) objSubmissao;
                            String date = (String) submissao.get("date");
                            Estudante student = (Estudante) submissao.get("student");
                            String text = (String) submissao.get("text");

                            Submissao submissaoObj = new Submissao(LocalDateTime.parse(date), student, text);

                            taskObj.addSubmission(submissaoObj);
                        }

                        projetoObj.addTask(taskObj);
                    }

                    for (Object objParticipante : participants) {
                        JSONObject participante = (JSONObject) objParticipante;
                        String nameParticipant = (String) participante.get("name");
                        String email = (String) participante.get("email");
                        Instituition instituition = (Instituition) participante.get("instituition");
                        Contact contact = (Contact) participante.get("contact");

                        Participant participanteObj;

                        if (participante.containsKey("numero")) {
                            int numero = ((Long) participante.get("numero")).intValue();
                            participanteObj = new Estudante(name, email, instituition, contact, numero);
                        } else if (participante.containsKey("Area de Especialização")) {
                            String areaEspecializacao = (String) participante.get("Area de Especialização");
                            participanteObj = new Facilitador(name, email, instituition, contact,  areaEspecializacao);
                        } else if (participante.containsKey("VAT")) {
                            String vat = (String) participante.get("VAT");
                            String website = (String) participante.get("WebSite");
                            participanteObj = new Parceiro(name, email, instituition, contact, vat, website);
                        } else {
                            throw new IllegalArgumentException("Tipo de participante desconhecido");
                        }

                        participanteObj.setInstituition(instituition);
                        participanteObj.setContact(contact);

                        projetoObj.addParticipant(participanteObj);
                    }

                    edicaoObj.addProjectJS(projetoObj);
                }

                this.addEdition(edicaoObj);
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que retorna o número de edições
     *
     * @return Número de edições
     */
    public int getnumEdition() {
        return numEditions;
    }

    /**
     * Metodo que adiciona uma edição ao array de edições
     *
     * @param edition Edição a adicionar
     */
    public void addEdition(Edicao edition) {
        if (getnumEdition() == editions.length) {
            aumentarEdicoes();
        }
        editions[numEditions] = edition;
        numEditions++;
    }

    /**
     * Método que remove uma edição do array de edições
     *
     * @param name Nome da edição a remover
     */
    public void removeEdition(String name) {
        if (!hasEdition(name)) {
            throw new IllegalArgumentException("Não existe edição com esse nome");
        } else {
            for (int i = 0; i < numEditions; i++) {
                if (editions[i] != null && editions[i].getName().equals(name)) {
                    for (int j = i; j < numEditions; j++) {
                        editions[j] = editions[j + 1];
                        if (j == numEditions - 1) {
                            editions[numEditions] = null;
                        }
                    }
                }
            }
            numEditions--;
        }
    }

    /**
     * Método que retorna o array de edições
     *
     * @return Array de edições
     */
    public Edicao[] getEditions() {
        return editions;
    }

    /**
     * Método que retorna uma edição
     *
     * @param name Nome da edição a retornar
     * @return Edição a retornar
     */
    public Edicao getEdition(String name) {
        for (Edicao e : editions) {
            if (e != null && e.getName().equals(name)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Não existe edição com esse nome");
    }

    /**
     * Método que retorna a edição ativa
     *
     * @return Edição ativa
     */
    public Edicao getEditionActive() {
        for (Edicao e : editions) {
            if (e != null && e.getStatus() == Status.ACTIVE) {
                return e;
            }
        }
        throw new IllegalArgumentException("Não existe nenhuma edição ativa");
    }

    //ver se todas as tasks tem pelo menos uma submissão
    /**
     * Método que verifica se todas as tarefas de uma edição têm pelo menos uma
     * submissão
     *
     * @return True se todas as tarefas de uma edição têm pelo menos uma
     * submissão, false caso contrário
     */
    public Edicao[] incompleteEdition() {
        Edicao[] temp = null;
        boolean encontrou = false;
        int j = 0;
        for (int i = 0; i < numEditions; i++) {
            for (Project p : editions[i].getProjects()) {
                encontrou = !p.isCompleted();
            }
            if (encontrou == true) {
                temp[j] = editions[i];
                j++;
                encontrou = false;
            }
        }
        return temp;
    }

    /**
     * Metodo que retorna um array de projetos incompletos de uma edição, os
     * projetos incompletos são aqueles que não tem todas as tarefas com pelo
     * menos uma submissão
     *
     * @param name Nome da edição
     * @return Array de projetos incompletos de uma edição
     */
    public Projeto[] imcompleteProject(String name) {
        Projeto[] temp = null;
        int j = 0;
        if (!getEditionActive().getName().equals(name)) {
            for (Edicao e : editions) {
                if (e.getName().equals(name)) {
                    for (int i = 0; i < e.getNumberOfProjects(); i++) {
                        if (!e.getProjects()[i].isCompleted()) {
                            temp[j] = (Projeto) e.getProjects()[i];
                            j++;
                        }
                    }
                }
            }
        }
        for (Project p : getEdition(name).getProjects()) {
            if (!p.isCompleted()) {
                temp[j] = (Projeto) p;
                j++;
            }
        }
        return temp;
    }

    /**
     * Metodo que retrona o num de projetos de uma edição
     *
     * @param name
     * @return
     */
    public int numProject(String name) {
        return getEdition(name).getNumberOfProjects();
    }

    /**
     * Metodo que retorna o progresso de um projeto bem com o numero de
     * submissões desse projeto
     *
     * @param name Nome do projeto
     * @return String com o progresso de um projeto bem com o numero de
     * submissões desse projeto
     */
    public String progressProject(String name) {
        double progress = 0;
        int numSubmissions = 0;

        for (Edicao e : editions) {
            if (e.projectExists(name) == true) {
                if (e.getProject(name) instanceof Projeto) {
                    Projeto project = (Projeto) e.getProject(name);
                    progress = project.getProjectProgress();
                    numSubmissions = project.getNumSubmissionTask();
                }
            } else {
                throw new IllegalArgumentException("Projeto não existe nesta Edição");
            }
        }
        return "O projeto " + name + " está com um progresso de " + progress + "% e " + numSubmissions + " submissões";
    }

    /**
     * Metodo que retorna o progresso de uma edição bem com o numero de
     * submissões dessa edição
     *
     * @param name Nome da edição
     * @return String com o progresso de uma edição bem com o numero de
     * submissões dessa edição
     */
    public String progressEdition(String name) {
        int completedProjects = 0;
        double progress = 0;
        int numSubmissions = 0;

        for (Edicao e : editions) {
            if (e.getName().equals(name)) {
                for (int i = 0; i < e.getNumberOfProjects(); i++) {
                    if (e.getProjects()[i].isCompleted()) {
                        completedProjects++;

                        if (e.getProjects()[i] instanceof Projeto) {
                            Projeto project = (Projeto) e.getProjects()[i];
                            numSubmissions += project.getNumSubmissionTask();
                        }
                    }
                    progress = completedProjects / e.getNumberOfProjects() * 100;
                }
            } else {
                throw new IllegalArgumentException("Edição não existe neste no CBL");
            }

        }
        return "A Edição " + name + " está com um progresso de " + progress + "% e " + numSubmissions + " submissões";
    }

    //adicionar submissão a projeto apenas por estudantes que pertencem aquele projeto
    //chama task.submissao e verifica se o participante for aluno e estiver no projeto
    /**
     * Metodo que adiciona uma submissão a uma tarefa de um projeto
     *
     * @param project Nome do projeto
     * @param task Nome da tarefa
     * @param studentEmail Email do estudante
     * @param text Texto da submissão
     */
    public void addSubmissao(String project, String task, String studentEmail, String text) {
        //verificar se o student é participante no project
        Student student;
        for (Edicao e : editions) {
            if (e.projectExists(project)) {
                if (e.getProject(project).getParticipant(studentEmail) != null) {
                    student = (Student) e.getProject(project).getParticipant(studentEmail);
                    //construir submissão
                    Submissao s = new Submissao(LocalDateTime.now(), (Estudante) student, text);
                    //enviar a submissão
                    for (Task t : e.getProject(project).getTasks()) {
                        if (t.getTitle().equals(task)) {
                            t.addSubmission(s);
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo que ativa uma edição, desativando a edição ativa, caso a edição
     * ativa ainda não tenha começado, a edição ativa é cancelada, caso a edição
     * ativa já tenha acabado, a edição ativa é fechada
     *
     * @param name Nome da edição a ativar
     */
    public void Activate(String name) {
        try {
            if (getEditionActive().getStart().isAfter(LocalDate.now())) {
                getEditionActive().setStatus(Status.CANCELED);
            } else if (getEditionActive().getEnd().isBefore(LocalDate.now())) {
                getEditionActive().setStatus(Status.CLOSED);
            } else {
                throw new IllegalArgumentException("A edição atual não pode ser desativada");
            }
        } catch (IllegalArgumentException e) {

        }
        getEdition(name).setStatus(Status.ACTIVE);
    }

    /**
     * Metodo que verifica se uma edição existe no array de edições
     *
     * @param name Nome da edição
     * @return True se a edição existe no array de edições, false caso contrário
     */
    public boolean hasEdition(String name) {
        for (Edicao e : editions) {
            if (e.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que retorna uma representação textual do objeto CBL
     */
    @Override
    public String toString() {
        String string;
        string = "numEditions = " + numEditions
                + "\neditions: ";
        if (numEditions == 0) {
            string += "null";
        } else {
            for (int i = 0; i < numEditions; i++) {
                string += "\n{\n" + editions[i].toString() + "}\n";
            }
        }

        return string;
    }

    /**
     * Metodo que aumenta o tamanho do array de edições
     */
    public void aumentarEdicoes() {
        Edicao[] temp = new Edicao[getnumEdition() + 5];
        for (int i = 0; i < editions.length; i++) {
            temp[i] = editions[i];
        }
        editions = temp;
    }

    /**
     * Metodo que imprime os 3 melhores alunos consuante as notas
     */
    public void topTresAlunosMaiorMediaNotas() {
        Estudante[] temp = new Estudante[3];
        int j = 0;
        for (Edicao e : editions) {
            if (e != null) {
                for (Project p : e.getProjects()) {
                    for (Task t : p.getTasks()) {
                        for (Submission s : t.getSubmissions()) {
                            if (s.getStudent() instanceof Estudante student) {
                                if (j < 3) {
                                    temp[j] = student;
                                    j++;
                                } else {
                                    for (int i = 0; i < temp.length; i++) {
                                        if (student.getMedia() > temp[i].getMedia()) {
                                            temp[i] = student;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (Estudante temp1 : temp) {
            if (temp1 == null) {
                throw new IllegalArgumentException("Não há notas suficientes para um top 3");
            }
            System.out.println(temp1.getName() + " " + temp1.getMedia());

        }
    }

    /**
     * Metodo que imprime as 3 edições com maior numero de projetos
     */
    public void topTresEdicoesComMaisPorjetos() {
        Edicao[] temp = new Edicao[3];
        int j = 0;
        for (Edicao e : editions) {
            if (j < 3) {
                temp[j] = e;
                j++;
            } else {
                for (int i = 0; i < temp.length; i++) {
                    if (e.getNumberOfProjects() > temp[i].getNumberOfProjects()) {
                        temp[i] = e;
                    }
                }
            }
        }
        for (Edicao temp1 : temp) {
            if (temp1 == null) {
                throw new IllegalArgumentException("Não há edições com projetos suficientes para um top 3");
            }
            System.out.println(temp1.getName() + " " + temp1.getNumberOfProjects());
        }
    }
}
