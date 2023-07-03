/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Rui
 */
public class Menus {
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
    
    public void menu() throws IOException{
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
                switch (op) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 0:

                        break;
                    default:

                        break;
                }
            }catch(NumberFormatException e){
                System.out.println(e);
            }
            System.out.println();
        } while (op != 0);
    }
}
