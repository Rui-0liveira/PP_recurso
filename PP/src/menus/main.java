/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menus;

import java.io.IOException;
import java.text.ParseException;
import projetos.CBL;

/**
 *
 * @author Rui
 */
public class main {

    
    
    public static void main(String[] args) throws IOException, ParseException {
        Menus menu = new Menus();
        CBL cbl = new CBL();
        menu.menu(cbl);
    }
    
    
}
