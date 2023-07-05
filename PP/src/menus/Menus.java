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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import ma02_resources.project.Project;
import projetos.*;

/**
 *
 * @author Rui
 */
/*
    NOTAS
    -ao adicionar posição a um array sempre que acabar(falta as tasks e tags)
    -ver como funciona a ativa (só pode ser feito submissoes á ativa)
    -ao criar projeto o array das tags tem 10 posições !!! nao pode!!!
    -ver como funciona o maxtasks de um projeto
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
    
    public void menu(CBL cbl) throws IOException, ParseException{
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
    //String name, LocalDate start, String template
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
    
    
    public void menuEdicao(CBL cbl) throws IOException, java.text.ParseException{
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
                        //menuProjeto(edition);
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
                String[] tags = new String[10];
                System.out.print("Insira o nome do projeto ");
                String name = ler();

                System.out.print("Insira a descriçao ");
                String descricao = ler();
                do{
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
    
}
