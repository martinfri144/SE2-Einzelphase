package com.se2einzelphase.app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionThread extends Thread {

    String mtnToServer;
    String mtnServerAnswer;

    Socket clientSocket;
    DataOutputStream outToServer;
    BufferedReader inFromServer;

    ConnectionThread (String mtnToServer)
    {
        this.mtnToServer = mtnToServer;
    }

    @Override
    public void run() {
        try {
            clientSocket = new Socket("se2-isys.aau.at", 53212);outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())));

            outToServer.writeBytes(mtnToServer + '\n');
            mtnServerAnswer = inFromServer.readLine();

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String returnMtn()
    {
        return mtnServerAnswer;
    }
}
