/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Student;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import participantes.Estudante;
import participantes.Participante;

/**
 *
 * @author rodri
 */
public class CBL {

    private Edicao[] editions;
    private int numEditions;

    public CBL() {
        numEditions = 0;
        editions = new Edicao[1];
    }

    public void gerarJSON() {
        JSONObject cbl = new JSONObject();
        cbl.put("numEditions", this.numEditions);
        JSONArray editions = new JSONArray();

        for (Edicao e : this.editions) {
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
                            JSONObject submissao = new JSONObject();
                            submissao.put("date", sub.getDate().toString());
                            submissao.put("student", sub.getStudent().toString());
                            submissao.put("text", sub.getText());
                            submissions.add(submissao);
                        }
                    }

                    for (Participant par : ((Projeto) p).getParticipants()) {
                        JSONObject participante = new JSONObject();
                        participante.put("name", par.getName());
                        participante.put("email", par.getEmail());
                        participante.put("instituition", par.getInstituition().toString());
                        participante.put("contact", par.getContact().toString());
                        participants.add(participante);

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

                    for (String tag : p.getTags()) {
                        tags.add(tag);
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
        for(Edicao e : editions) {
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

    public void aumentarEdicoes() {
        Edicao[] temp = new Edicao[getnumEdition() + 5];
        for (int i = 0; i < editions.length; i++) {
            temp[i] = editions[i];
        }
        editions = temp;
    }

    public void topTresAlunosMaiorMediaNotas() {
        Estudante[] temp = new Estudante[3];
        int j = 0;
        for (Edicao e : editions) {
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
        for (Estudante temp1 : temp) {
            System.out.println(temp1.getName() + " " + temp1.getMedia());
        }
    }

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
            System.out.println(temp1.getName() + " " + temp1.getNumberOfProjects());
        }
    }

}
