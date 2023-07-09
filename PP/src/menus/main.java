/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menus;

import java.io.IOException;
import java.text.ParseException;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import projetos.CBL;

/**
 * Nome: Rodrigo Bamdé Chantre Lopes
 * Número: 8210191
 * Turma: T4
 * 
 * Nome: Rui Alexande da Silva Oliveira
 * Número: 8210322
 * Turma: T3
 */

public class main {

    public static void main(String[] args) throws IOException, ParseException, IllegalNumberOfParticipantType, ParticipantAlreadyInProject, TaskAlreadyInProject, IllegalNumberOfTasks, org.json.simple.parser.ParseException {
        Menus menu = new Menus();
        CBL cbl = new CBL();
        menu.menu(cbl);
    }
}
