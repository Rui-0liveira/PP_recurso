/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menus;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import ma02_resources.project.*;
import ma02_resources.participants.*;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import participantes.*;
import projetos.*;

/**
 *
 * @author Rui
 */
/*
    NOTAS
    -ao criar task, a data de inicio da task não pode ser antes da do inicio do projeto
    -Como fazer as notas, totalmete separada(prefiro esta), ou junta com o estudante
    -Falta por as avaliação a dar e no menu !!!!!!!!!!!!!!!!!!!!!!!!!
*/
public class Menus {
    
    public boolean existeficheiro(String name){
        File file = new File(name);

        if (file.exists()) {
            return true;
        } 
        return false;
    }
    
    //função de ler input
    public String ler() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        return br.readLine();
    }
    
    public int lerInt() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        try{
            return Integer.parseInt(br.readLine());
        }catch(NumberFormatException e){
            System.out.println("Valor invalido! Insira novamente: ");
            return lerInt();
        }
     
    }
    
    public void menu(CBL cbl) throws IOException, ParseException, IllegalNumberOfParticipantType, ParticipantAlreadyInProject, TaskAlreadyInProject, IllegalNumberOfTasks, org.json.simple.parser.ParseException{
        int op = 0;
        do {
            System.out.println("------ CBL ------");
            System.out.println("1. Criar Edição");
            System.out.println("2. Remover Edição");
            System.out.println("3. Visualizar Edições");
            System.out.println("4. Visualizar Edição");
            System.out.println("5. Definir edição ativa");
            System.out.println("6. Visualizar edição ativa");
            System.out.println("7. Gerir Edição");
            System.out.println("8. Representação textual de projeto");
            System.out.println("9. Representação textual de edição");
            System.out.println("10. Media de tempo tasks");
            System.out.println("11. Top Alunos");
            System.out.println("12. Top Edições");
            System.out.println("13. Exportar dados para JSON");
            System.out.println("14. Carregar CBL");
            System.out.println("15. Exportar dados para CSV");
            System.out.println("0. Exit");
            System.out.print("Insira a opção ");
            try{
                op = Integer.parseInt(ler());
            }catch(NumberFormatException e){
                System.out.println("Opção invalido");
                op=-1;
            }
            switch (op) {
                case 1:
                    criarEdicao(cbl);
                    break;
                case 2:
                    removerEdicao(cbl);
                    break;
                case 3:
                    System.out.println(cbl.toString());
                    break;
                case 4:
                    verEdicao(cbl);
                    break;
                case 5:
                    definirAtiva(cbl);
                    break;
                case 6:
                    verAtiva(cbl);
                    break;
                case 7:
                    menuEdicao(cbl);
                    break;
                case 8:
                    textoProjeto(cbl);
                    break;
                case 9:
                    textoEdicao(cbl);
                    break;
                case 10:
                    mediaTasks(cbl);
                    break;
                case 11:
                    try{
                        cbl.topTresAlunosMaiorMediaNotas();
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 12:
                    try{
                        cbl.topTresEdicoesComMaisPorjetos();
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 13:
                    cbl.gerarJSON(); 
                    break;
                case 14:
                    cbl.lerJSON();
                    break;
                case 15:
                    cbl.gerarCSV();
                    break;
                case 0:
                    //cbl.gerarJSON(); 
                    break;
                default:
                    //cbl.gerarJSON();
                    break;
            }
            System.out.println();
        } while (op != 0);
    }
    
    public void criarEdicao(CBL cbl) throws IOException{
        Edicao edition;
            try{
                System.out.print("Insira o nome da edição ");
                String name = ler();

                System.out.print("Insira a data (ano-mes-dia)");
                String data = ler();

                LocalDate start = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);

                System.out.print("Insira a template");
                String template = ler();
                
                if(!existeficheiro(template)){
                    throw new IllegalArgumentException("Nome de ficheiro inexistente");
                }

                edition = new Edicao(name,start,template);
                cbl.addEdition(edition);
                
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch (DateTimeParseException e) {
                System.out.println("Data inserida inválida");
        }
            
    }

    public void removerEdicao(CBL cbl) throws IOException{
        int op=0;
        if(cbl.getnumEdition()>0){
            System.out.println("\n----Lista de Edições-----");
            for(int i = 0; i < cbl.getnumEdition(); i++){
                System.out.println("\n" + i+1 + "-" + cbl.getEditions()[i].getName());
            }
            System.out.print("Insira a edição que pertende eliminar ");
            try{
                op = Integer.parseInt(ler());
            }catch(NumberFormatException e){
                System.out.println(e);
            }
            if(op > cbl.getnumEdition() || op < 0){
                System.out.println("\nOpção inválida");
            }
            else{
                cbl.removeEdition(cbl.getEditions()[op-1].getName());
            }
        }
        else{
            System.out.println("\nNão existe nenhuma edição no cbl");
        }
    }
    
    public void verEdicao(CBL cbl) throws IOException{
        System.out.println("Insere o nome da edição que deseja visualizar");
        try{
            System.out.println(cbl.getEdition(ler()).toString());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void definirAtiva(CBL cbl) throws IOException{
        System.out.println("Insere o nome da edição que deseja ativar");
        try{
            cbl.Activate(ler());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void verAtiva(CBL cbl){
        try{
            cbl.getEditionActive();
        }catch(IllegalArgumentException e){
             System.out.println(e.getMessage());
        }
    }
    
    public void textoProjeto(CBL cbl) throws IOException{
        Edicao edition;
        Project project;
        try{
            if(cbl.getnumEdition() == 0){
                throw new IllegalArgumentException("Não existe nenhuma edição");
            }
            else{
                for(int i = 0; i < cbl.getnumEdition(); i++){
                    System.out.println("\n" + i + "-" +cbl.getEditions()[i].getName());
                }
                System.out.println("Insira a edição");

                int op = Integer.parseInt(ler());
                if(op < 0 || op > cbl.getnumEdition()){
                    throw new IllegalArgumentException("Edição inválida");
                }
                edition = cbl.getEdition(cbl.getEditions()[op].getName());
                if(edition.getNumberOfProjects() == 0){
                    throw new IllegalArgumentException("Não existe nenhum projeto nesta edição");
                }
                else{
                    for(int i = 0; i < edition.getNumberOfProjects(); i++){
                        System.out.println("\n" + i + "-" +edition.getProjects()[i].getName());
                    }
                    System.out.println("Insira o projeto");
                    int op1 = Integer.parseInt(ler());
                    if(op1 < 0 || op1 > edition.getNumberOfProjects()){
                        throw new IllegalArgumentException("Projeto inválido");
                    }
                    project = edition.getProject(edition.getProjects()[op1].getName());
                    System.out.println(cbl.progressProject(project.getName()));
                }
                System.out.println("Insira o projeto");
            }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void textoEdicao(CBL cbl) throws IOException{
        Edicao edition;
        try{
            if(cbl.getnumEdition() == 0){
                throw new IllegalArgumentException("Não existe nenhuma edição");
            }
            else{
                for(int i = 0; i < cbl.getnumEdition(); i++){
                    System.out.println("\n" + i + "-" +cbl.getEditions()[i].getName());
                }
                System.out.println("Insira a edição");

                    int op = Integer.parseInt(ler());
                    if(op < 0 || op > cbl.getnumEdition()){
                        throw new IllegalArgumentException("Edição inválida");
                    }
                    edition = cbl.getEdition(cbl.getEditions()[op].getName());
                    System.out.print(cbl.progressEdition(edition.getName()));


            }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void mediaTasks(CBL cbl) throws IOException{
        
        Edicao edition;
        Projeto project;
        try{
            if(cbl.getnumEdition() == 0){
                throw new IllegalArgumentException("Não existe nenhuma edição");
            }
            else{
                for(int i = 0; i < cbl.getnumEdition(); i++){
                    System.out.println("\n" + i + "-" +cbl.getEditions()[i].getName());
                }
                System.out.println("Insira a edição");

                int op = Integer.parseInt(ler());
                if(op < 0 || op > cbl.getnumEdition()){
                    throw new IllegalArgumentException("Edição inválida");
                }
                edition = cbl.getEdition(cbl.getEditions()[op].getName());
                if(edition.getNumberOfProjects() == 0){
                    throw new IllegalArgumentException("Não existe nenhum projeto nesta edição");
                }
                else{
                    for(int i = 0; i < edition.getNumberOfProjects(); i++){
                        System.out.println("\n" + i + "-" +edition.getProjects()[i].getName());
                    }
                    System.out.println("Insira o projeto");
                    int op1 = Integer.parseInt(ler());
                    if(op1 < 0 || op1 > edition.getNumberOfProjects()){
                        throw new IllegalArgumentException("Projeto inválido");
                    }
                    project =(Projeto) edition.getProject(edition.getProjects()[op1].getName());
                    project.mediaTempoTasks();
                }
                System.out.println("Insira o projeto");
            }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
    
    public void menuEdicao(CBL cbl) throws IOException, java.text.ParseException, IllegalNumberOfParticipantType, ParticipantAlreadyInProject, TaskAlreadyInProject, IllegalNumberOfTasks{
        Edicao edition;
        System.out.println("Insira o nome da edição que deseja mexer");
        try{
            edition = cbl.getEdition(ler());
            int op = 0;
            do {
                op = -1;
                System.out.println("------ Edição: " + edition.getName() + " ------");
                System.out.println("1. Criar Projeto");
                System.out.println("2. Remover Projeto");
                System.out.println("3. Ver dados");
                System.out.println("4. Ver projeto");
                System.out.println("5. Ver projetos com determinada tag");
                System.out.println("6. Gerir Projeto");
                System.out.println("0. Exit");
                System.out.print("Insira a opção ");
                try{
                    op = Integer.parseInt(ler());
                }catch(NumberFormatException e){
                    System.out.println(e);
                }
                switch (op) {
                    case 1:
                        criarProjeto(edition);
                        break;
                    case 2:
                        removerProjeto(edition);
                        break;
                    case 3:
                        System.out.println(edition.toString());
                        break;
                    case 4:
                        verProjeto(edition);
                        break;
                    case 5:
                        verProjetosPorTag(edition);
                        break;
                    case 6:
                        menuProjeto(edition);
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
                System.out.println();
            } while (op != 0);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public void criarProjeto(Edicao edition) throws IOException, java.text.ParseException{
        int i = 0;
            try{
                String[] tags = new String[0];
                System.out.print("Insira o nome do projeto ");
                String name = ler();

                System.out.print("Insira a descriçao ");
                String descricao = ler();
                do{
                    if(tags.length == i){
                        tags = aumentarArrayString(tags); 
                    }
                    System.out.print("Insira um tag");
                    tags[i]= ler();
                    i++;
                    System.out.print("Se deseja inserir mais tags insira 'sim'");
                }while(ler().equals("sim"));
                

                edition.addProject(name,descricao,tags);
            }
            catch(java.text.ParseException e){
                System.out.println(e.getMessage());
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
    }
    public String[] aumentarArrayString(String[] string) {
        String[] temp = new String[string.length + 1];
        for (int i = 0; i < string.length; i++) {
            temp[i] = string[i];
        }
        return temp;
    }
    
    public void removerProjeto(Edicao edition) throws IOException{
        int op = 0;
        if(edition.getNumberOfProjects()>0){
            System.out.println("\n----Lista de Projetos-----");
            for(int i = 0; i < edition.getNumberOfProjects(); i++){
                System.out.println("\n" + i + "-" + edition.getProjects()[i].getName());
            }
            System.out.print("Insira o projeto que pertende eliminar ");
            try{
                op = Integer.parseInt(ler());
                if(op > edition.getNumberOfProjects() || op < 0){
                    System.out.println("\nOpção inválida");
                }
                else{
                    edition.removeProject(edition.getProjects()[op].getName());
                }
            }catch(NumberFormatException e){
                System.out.println(e);
            }
        }
        else{
            System.out.println("\nNão existe nenhum projeto nesta edição");
        }
    }

    public void verDados(Edicao edition){
        try{
            System.out.println(edition.toString());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void verProjeto(Edicao edition) throws IOException{
        System.out.println("Insere o nome do projeto que deseja visualizar");
        try{
            System.out.println(edition.getProject(ler()).toString());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void verProjetosPorTag(Edicao edition) throws IOException{
        System.out.println("Insere a tag que deseja visualizar");
        try{
            if(edition.getNumberOfProjects()==0){
                System.out.println("Não existe nenhum projeto");
            }
            Project [] temp;
            temp = edition.getProjectsByTag(ler());
            
            for(Project p : temp){
                if(p!=null){
                    System.out.println(p.toString());
                }
            }
            
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    //menu de um projeto (CRUD de Participante e task)
    public void menuProjeto(Edicao edition) throws IOException, ParseException, IllegalNumberOfParticipantType, ParticipantAlreadyInProject, IllegalNumberOfTasks, TaskAlreadyInProject{
        Projeto projeto;
        System.out.println("Insira o nome do projeto que deseja mexer");
        try{
            projeto = (Projeto)edition.getProject(ler());
            int op = 0;
            do {
                op = -1;
                System.out.println("------ Edição: " + edition.getName() + ", Projeto: "+ projeto.getName() + " ------");
                System.out.println("1. Criar Participante");
                System.out.println("2. Remover Participante");
                System.out.println("3. Criar Task");
                System.out.println("4. Ver Participantes");
                System.out.println("5. Ver Participante");
                System.out.println("6. Ver tasks");
                System.out.println("7. Ver task");
                System.out.println("8. Ver tags");
                System.out.println("9. Gerir Task");
                System.out.println("0. Exit");
                System.out.print("Insira a opção ");
                try{
                    op = Integer.parseInt(ler());
                }catch(NumberFormatException e){
                    System.out.println(e);
                }
                switch (op) {
                    case 1:
                        criarParticipante(projeto);
                        break;
                    case 2:
                        removerParticipante(projeto);
                        break;
                    case 3:
                        criarTask(projeto);
                        break;
                    case 4:
                        verParticipante(projeto);
                        break;
                    case 5:
                        verParticipantes(projeto);
                        break;
                    case 6:
                        verTasks(projeto);
                        break;
                    case 7:
                        verTask(projeto);
                        break;
                    case 8:
                        verTags(projeto);
                        break;
                    case 9:
                        menuTask(edition, projeto);
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
                System.out.println();
            } while (op != 0);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    public Contacto criarContacto() throws IOException{
        System.out.println("Nome da rua: ");
        String rua = ler();
            
        System.out.println("Nome da cidade: ");
        String cidade = ler();
            
        System.out.println("Nome de estado: ");
        String estado = ler();
            
        System.out.println("ZIPCODE: ");
        String zip = ler();
            
        System.out.println("Nome do País: ");
        String pais = ler();
            
        System.out.println("Numero de telefone: ");
        String tele = ler();
        
        Contacto newC = new Contacto(rua, cidade, estado, zip, pais, tele);
        
        return newC;
        
    }
    
    public Instituicao criarInstituicao() throws IOException{
        Instituicao newI = null;
        Contacto contact;
        InstituitionType type;
        try{
            System.out.println("Nome da Instituição: ");
            String name = ler();
            
            System.out.println("Email da instituiçaõ: ");
            String email = ler();
            
            System.out.println("Contacto da Instituição: ");
            contact = criarContacto();
            
            System.out.println("WebSite da Instituição: ");
            String web = ler();
            
            System.out.println("Descrição da Instituição: ");
            String description = ler();
            
            System.out.println("1-COMPANY\n2-NGO\n3-UNIVERSITY\n4-OTHER\nTipo da instituição: ");
            int tipo = lerInt();
            if(tipo == 1){
               type = InstituitionType.COMPANY;
            }else if(tipo == 2){
               type = InstituitionType.NGO;
            }else if(tipo == 3){
               type = InstituitionType.UNIVERSITY;
            }else{
               type = InstituitionType.OTHER;
            }
                       
            newI = new Instituicao(name, email, type, contact, web, description);
            
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        
        return newI;
    }
    
    public void criarParticipante(Projeto projeto) throws IOException, IllegalNumberOfParticipantType, ParticipantAlreadyInProject{
        Participante participante;
        Instituicao institut = null;
        Contacto contacto;
        int tipo = 0;
        boolean encontrou = false;
            try{
                System.out.print("Que tipo de participante deseja criar?\n1-Estudante\n2-Parceiro\n3-Facilitador");
                tipo = Integer.parseInt(ler());
                if(tipo < 1 || tipo > 3){
                    throw new IllegalArgumentException("Tipo de participante inválido");
                }
                System.out.print("Insira o nome do participante ");
                String name = ler();

                System.out.println("Insira o email do particioante: ");
                String email = ler();
                
                System.out.println("Insira o contacto do participante: ");
                contacto = criarContacto();
                
                System.out.print("Insira a instituição: ");
                String nome = ler();
                
                for(Participant p: projeto.getParticipants()){
                    if(p != null){
                        if(p.getInstituition().getName().equals(nome)){
                            institut = (Instituicao) p.getInstituition();
                            encontrou = true;
                            break;
                        }  
                    }
                }
                if(encontrou == false){
                    System.out.println("Instituição não encontrada");
                    institut = criarInstituicao();
                }
            switch (tipo) {
                case 1:
                    System.out.print("Insira o numero do estudante");
                    int number = Integer.parseInt(ler());
                    participante = new Estudante(name, email, institut, contacto, number);
                    break;
                case 2:
                    System.out.print("Insira o vat");
                    String vat = ler();
                    
                    System.out.print("Insira o website");
                    String site = ler();
                    
                    participante = new Parceiro(name, email, institut, contacto, vat, site);
                    break;
                default:
                    System.out.print("Insira a area de expecialidade");
                    String area = ler();
                    participante = new Facilitador(name, email, institut, contacto, area);
                    break;
            }
                
                projeto.addParticipant(participante);
            }catch(IllegalArgumentException | IllegalNumberOfParticipantType e){
                System.out.println(e.getMessage());
            }
    }

    public void removerParticipante(Projeto projeto) throws IOException{
        int op = 0;
        if(projeto.getNumberOfParticipants() > 0){
            System.out.println("\n----Lista de Participantes-----");
            for(int i = 0; i < projeto.getNumberOfParticipants(); i++){
                System.out.println( i + "-"+ projeto.getParticipants()[i].getName());
            }
            System.out.print("Insira o participante que pertende eliminar ");
            try{
                op = Integer.parseInt(ler());
                if(op > projeto.getNumberOfParticipants() || op < 0){
                    System.out.println("\nOpção inválida");
                }
                else{
                    projeto.removeParticipant(projeto.getParticipant(projeto.getParticipants()[op].getName()).getEmail());
                }
            }catch(NumberFormatException e){
                System.out.println(e);
            }
        }
        else{
            System.out.println("\nNão existe nenhum participante neste projeto");
        }
    }

    public void criarTask(Projeto projeto) throws IOException, ParseException, IllegalNumberOfTasks, TaskAlreadyInProject{
        Tarefas task;
            try{
                System.out.print("Insira o titulo da task ");
                String title = ler();

                System.out.print("Insira a duração ");
                int duration = lerInt();

                System.out.print("Insira a data de inicio (ano-mes-dia)");
                String data = ler();

                LocalDate start = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);

                System.out.println("Insira a descriçao ");
                String descricao = ler();

                task = new Tarefas(start, duration, title, descricao);
                projeto.addTask(task);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
    }

    public void verParticipante(Projeto projeto) throws IOException{
        System.out.println("Insere o nome do participante que deseja visualizar");
        try{
            Participant p = projeto.getParticipant(ler());
            if(p == null){
                throw new IllegalArgumentException("Task não existe neste projeto");
            }
            System.out.println(p.toString());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void verParticipantes(Projeto projeto){
        for(Participant p : projeto.getParticipants()){
            if(p != null){
                System.out.println(p.toString());
            }
        }
    }
    
    public void verTask(Projeto projeto) throws IOException{
        System.out.println("Insere o titulo da task que deseja visualizar");
        try{
            Task t = projeto.getTask(ler());
            if(t == null){
                throw new IllegalArgumentException("Task não existe neste projeto");
            }
            System.out.println(t.toString());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void verTasks(Projeto projeto){
        for(Task t : projeto.getTasks()){
            if(t != null){
                System.out.println(t.toString());
            }
        }
    }

    public void verTags(Projeto projeto){
        for(String t : projeto.getTags()){
            if(t != null){
                System.out.println(t);
            }
        }
    }
    
    
    public void menuTask(Edicao edition, Projeto projeto) throws IOException{
        Tarefas task;
        System.out.println("Insira o nome da task que deseja mexer");
        try{
            task = (Tarefas) projeto.getTask(ler());
            int op = 0;
            do {
                System.out.println("------ Edição: " + edition.getName() +", Projeto: "+ projeto.getName() + ", Task: "+ task.getTitle() + " ------");
                System.out.println("1. Ver dados");
                System.out.println("2. Criar submissão");
                System.out.println("3. Ver submissões");
                System.out.println("0. Exit");
                System.out.print("Insira a opção ");
                try{
                    op = Integer.parseInt(ler());
                }catch(NumberFormatException e){
                    System.out.println(e);
                }
                switch (op) {
                    case 1:
                        System.out.println(task.toString());
                        break;
                    case 2:
                        criarSubmissao(edition,projeto, task);
                        break;
                    case 3:
                        verSubmissoes(task);
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
                System.out.println();
            } while (op != 0);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }


    public void criarSubmissao(Edicao edition, Projeto projeto, Tarefas task) throws IOException{
        Submissao submissao;
        Estudante estudante = null;
        if(edition.getStatus() != Status.ACTIVE){
            System.out.println("Só pode fazer sumissões á edição ativa");
        }
        else{
            try{
                System.out.println("Insira o email do estudante que fez a submissão");
                String email = ler();


                for(int i = 0; i < projeto.getNumberOfParticipants(); i++){
                    if(projeto.getParticipants()[i].getEmail().equals(email)){
                        estudante = (Estudante) projeto.getParticipants()[i];
                    }
                }

                System.out.println("Insira a data da submissão");
                String data = ler();

                LocalDateTime date = LocalDateTime.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);

                System.out.println("Insira o texto da submissão");
                String texto = ler();

                submissao = new Submissao(date, estudante, texto);

                task.addSubmission(submissao);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            } 
        }
    }

    public void verSubmissoes(Tarefas task){
        for(Submission s : task.getSubmissions()){
            if(s != null){
                System.out.println(s.toString());
            }
        }
    }
}
