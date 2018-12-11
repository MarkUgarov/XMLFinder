/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugarov.xml.finder.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author black
 */
public class FilesReader {
   
    private BufferedReader br;
    private FileReader fr;
    
    
    public FilesReader(){
        
        
        
    }
    
    public File[] getChildrenFiles(String directoryPath){
        return new File(directoryPath).listFiles();
    }
    
    public ArrayList<String> getContent(File f){
        try {
            this.fr = new FileReader(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilesReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        ArrayList<String> ret = new ArrayList<>();
        String line;
        this.br = new BufferedReader(fr);
        try {
            while((line = br.readLine())!=null){
                ret.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(FilesReader.class.getName()).log(Level.SEVERE, null, ex);
            return ret;
        }
        return ret;
    }
    
    public ArrayList<String> readXML(File f){
        String seperator = "<";
        ArrayList<String> fileContent = this.getContent(f);
        if (fileContent == null){
            return null;
        }
        StringBuilder bf = new StringBuilder();
        for (String s: fileContent){
            bf.append(s);
        }
        String[] splitContent = bf.toString().split(seperator);
        ArrayList<String> ret = new ArrayList<>();
        for(String s:splitContent){
            ret.add(seperator+s);
        }
        return ret;
    }
    
}
