/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.util;

/**
 *
 * @author 20ala
 */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream{
		 //Sobrescribimos el método que crea la cabecera
                 @Override
		 protected void writeStreamHeader() throws IOException
		 {
			 	reset();
		 }
		 //Constructores
		 public MyObjectOutputStream () throws IOException{
			 super();
		 }
		 public MyObjectOutputStream(OutputStream out) throws IOException
		 {
			 super(out);
		 }
}
