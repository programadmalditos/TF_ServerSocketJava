/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author luisgillamaignere
 */
public class ServidorHilos {

    ArrayList<String> registro;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Puerto:");
        Scanner entrada=new Scanner(System.in);
        try {
            int puerto = Integer.parseInt(entrada.nextLine());
            new ServidorHilos(puerto);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("El puerto debe ser un numero");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        
    }
    
    public ServidorHilos(int puerto) throws IOException{
        registro=new ArrayList<String>();
        ServerSocket socketServidor = new ServerSocket(puerto);
        System.out.println("El servidor se esta escuchando en el puerto: " + puerto);
         while (true) {
                //llamamos al metodo accept para que cuando se conecte el cliente acepte la peticion
                Socket cliente = socketServidor.accept();
                //se instancia la clase del nuevo cliente y se lanza en un hilo aparte
                Runnable nuevoCliente = new HiloCliente(registro, cliente);
                Thread hilo = new Thread(nuevoCliente);
                hilo.start();
            }
    
    }
    
    
}
