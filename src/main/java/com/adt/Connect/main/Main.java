/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.main;

import com.adt.Connect.util.Utils;
import java.io.File;

/**
 *
 * @author 20ala
 */
public class Main {

    public static void main(String[] args) {
        int ele;
        File fich = new File("watchlist.dat");

        do {
            ele = menu();
            switch (ele) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Byeeeee");
                    break;
            }
        } while (ele != 5);

    }

    public static int menu() {
        int ele;
        System.out.println("\n**********************MENU**********************");
        System.out.println("1.\tAlta de empleado. \r\n"
                + "2.\tAlta de categoría. \r\n"
                + "3.\tModificación del departamento de un empleado a partir de su código de empleado. \r\n"
                + "4.\tListado de los departamentos con el número de empleados que hay en cada departamento \r\n"
                + "5.\tListado ordenado por categoría\r\n"
                + "5.\tSalir\r\n");

        System.out.print("Introduce una opcion:");
        ele = Utils.leerInt(1, 5);
        return ele;

    }

}
