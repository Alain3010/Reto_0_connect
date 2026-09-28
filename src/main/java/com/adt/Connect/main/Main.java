package com.adt.Connect.main;

import com.adt.Connect.util.Utils;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        
        //Buscar documentacion yo no estoy para esto
        File imageFile = new File("src/main/resources/images/dumb-and-dumber.webp"); 

        if (!imageFile.exists()) { 
            imageFile = new File("src/main/java/images/dumb-and-dumber.webp");
        }

        if (imageFile.exists()) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                try {
                    Desktop.getDesktop().open(imageFile);
                } catch (IOException e) {
                    System.err.println("Could not open image viewer: " + e.getMessage());
                }
            } else {
                System.out.println("Desktop operations are not supported on this environment.");
            }
        } else {
            System.out.println("Warning: Image file not found at: " + imageFile.getAbsolutePath());
        }

        int ele;
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
        System.out.println("1.\tAlta de empleado.\r\n"
                + "2.\tAlta de categoría.\r\n"
                + "3.\tModificación del departamento de un empleado a partir de su código de empleado.\r\n"
                + "4.\tListado de los departamentos con el número de empleados que hay en cada departamento\r\n"
                + "5.\tSalir\r\n");

        System.out.print("Introduce una opcion: ");
        ele = Utils.leerInt(1, 5);
        return ele;
    }
}