/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import ma02_resources.participants.Student;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import ma02_resources.project.Task;
import participantes.Estudante;

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
    
     /**
     * Método que retorna o número de edições
     * @return Número de edições
     */
    public int getnumEdition() {
        return numEditions;
    }

    /**
     * Metodo que adiciona uma edição ao array de edições
     * @param edition Edição a adicionar
     */
    public void addEdition(Edicao edition) {
        if(getnumEdition() == editions.length){
            aumentarEdicoes();
        }
        editions[numEditions] = edition;
        numEditions++;
    }

    /**
     * Método que remove uma edição do array de edições
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
     * @return Array de edições
     */
    public Edicao[] getEditions() {
        return editions;
    }

    /**
     * Método que retorna uma edição
     * @param name Nome da edição a retornar
     * @return Edição a retornar
     */
    public Edicao getEdition(String name) {
        for (Edicao e : editions) {
            if (e!=null && e.getName().equals(name)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Não existe edição com esse nome");
    }

    /**
     * Método que retorna a edição ativa
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
     * Método que verifica se todas as tarefas de uma edição têm pelo menos uma submissão
     * @return True se todas as tarefas de uma edição têm pelo menos uma submissão, false caso contrário
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
     * Metodo que retorna um array de projetos incompletos de uma edição, os projetos incompletos são aqueles que não tem todas as tarefas com pelo menos uma submissão
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
     * @param name
     * @return
     */
    public int numProject(String name) {
        return getEdition(name).getNumberOfProjects();
    }

    /**
     * Metodo que retorna o progresso de um projeto bem com o numero de submissões desse projeto
     * @param name Nome do projeto
     * @return String com o progresso de um projeto bem com o numero de submissões desse projeto
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
     * Metodo que retorna o progresso de uma edição bem com o numero de submissões dessa edição
     * @param name Nome da edição
     * @return String com o progresso de uma edição bem com o numero de submissões dessa edição
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
     * Metodo que ativa uma edição, desativando a edição ativa, caso a edição ativa ainda não tenha começado, 
     * a edição ativa é cancelada, caso a edição ativa já tenha acabado, a edição ativa é fechada
     * @param name Nome da edição a ativar
     */
    public void Activate(String name) {
        try{
            if (getEditionActive().getStart().isAfter(LocalDate.now())) {
                getEditionActive().setStatus(Status.CANCELED);
            } else if (getEditionActive().getEnd().isBefore(LocalDate.now())) {
                getEditionActive().setStatus(Status.CLOSED);
            } else {
                throw new IllegalArgumentException("A edição atual não pode ser desativada");
            }
        }catch(IllegalArgumentException e){
            
        } 
        getEdition(name).setStatus(Status.ACTIVE);
    }

    /**
     * Metodo que verifica se uma edição existe no array de edições
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
    
}
