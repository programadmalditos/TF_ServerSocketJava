/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author luisgillamaignere
 */
public class HiloCliente implements Runnable {

//defino charla que es donde almaceno la charla en un ArrayList
    private ArrayList<String> charla;
//defino el Socket para poder recibir y enviar flujos de datos por la red
    private Socket socket;

    //defino el flujo de datos de entrada
    private DataInputStream dataInput;

//defino el flujo de datos de salida
    private DataOutputStream dataOutput;

    //clase q recibe y guarda como parametros la charla y el socket
    public HiloCliente(ArrayList<String> charla, Socket socket) {
        this.charla = charla;
        this.socket = socket;
        try {
            //creo flujo de datos de entrada y salida
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());

        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
    }

//lo que hace el hilo para atender al cliente
    public void run() {
        try {
//mientras se cumpla el while agrega el texto a la charla
            while (true) {
                String texto = dataInput.readUTF();
                synchronized (charla) {
                    charla.add(texto);
                    System.out.println(texto);
                    dataOutput.writeUTF("Recibido " + texto);
                    
                }
            }
        } catch (Exception e) {
            System.out.println("Fin del cliente ");
        }
    }
}

