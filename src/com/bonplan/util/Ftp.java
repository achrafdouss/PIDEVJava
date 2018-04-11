/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.util;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
 

/**
 *
 * @author dell
 */
public class Ftp {
    final FTPClient client = new FTPClient();
    final String server = "127.0.0.1";
    final String user = "pidev";
    final String password = "pidev";
    final int port = 21;
    
    public Ftp()
    {
        try {
            client.connect(server, port);
            showServerReply(client);
            int replyCode = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return;
            }
            boolean success = client.login(user, password);
            showServerReply(client);
            if (!success) {
                System.out.println("Could not login to the server");
                return;
            } else {
                System.out.println("LOGGED IN SERVER");
            }
        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
        }
    }
    public static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
    public void sendFile(String path) throws IOException
    {
        try{ 
         client.enterLocalPassiveMode();
         client.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(path);
            
            int index = path.lastIndexOf("\\");
            String firstRemoteFile = path.substring(index + 1);
            InputStream inputStream = new FileInputStream(firstLocalFile);
            System.out.println("Started uploading first file");
            boolean done = client.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file was uploaded successfully.");
            }
        }catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (client.isConnected()) {
                    client.logout();
                    client.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
 
        
        
}
    
