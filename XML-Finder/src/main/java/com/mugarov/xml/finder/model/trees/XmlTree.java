/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugarov.xml.finder.model.trees;

import com.mugarov.xml.finder.model.PmsInfo;
import com.mugarov.xml.finder.view.Messenger;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author black
 */
public class XmlTree {
    
    private File file;
    private ArrayList<String> fileContent;
    
    public XmlTree(File f,ArrayList<String> content){

        //Messenger.println(f.getName() +" added as XmlTree");
    }
    
    public void parseContent(){
        Messenger.println("Error: no valid parsing-function for "+ this.file.getName());
    }
    
    public ArrayList<PmsInfo> getContent(){
        Messenger.println("Error:  no parsed Content for "+ this.file.getName());
        return null;
    }
    
}
