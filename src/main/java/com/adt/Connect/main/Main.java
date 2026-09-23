/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.main;

import com.adt.Connect.util.Utils;

/**
 *
 * @author 20ala
 */
public class Main {

    public static void main(String[] args) {
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
                    System.out.println("See you next time!");
                    break;
            }
        } while (ele != 5);

    }

    public static int menu() {
        int ele;
        System.out.println("""
            **********************MENU**********************
            1.\tMovie list.\r
            2.\tUser list.\r
            3.\tView user's watchlist.\r
            4.\tCreate new watchlist.\r
            5.\tAdd movie to watchlist.\r
            5.\tExit\r
            """);
        System.out.print("Write an option: ");
        ele = Utils.leerInt(1, 5);
        return ele;

    }

}
