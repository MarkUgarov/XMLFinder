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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author black
 */
public class WarningXmlTree extends XmlTree{
    
    private final ArrayList<PmsInfo> foundPms;
    private final File file;
    private final ArrayList<String> fileContent;
    
    private final String regex = "(?<=Prozessname:).*";
    
    public WarningXmlTree(File f, ArrayList<String> content) {
        super(f, content);
        this.file = f;
        this.foundPms = new ArrayList<>();
        this.fileContent=content;
    }
    
    @Override
    public void parseContent(){
        boolean success = false;
        String line;
        int index;
        boolean currSuccess;
        for(index=0; index<this.fileContent.size(); index++){
            line = this.fileContent.get(index);
            currSuccess =this.parseLine(line, index);
            success = success || currSuccess;
        }
        if (!success){
            Messenger.println("No data have been found in File " +this.file.getName());
        }
        else{
            Messenger.println("PMS-Infos of "+this.file.getName());
            for(PmsInfo s: this.foundPms){
                Messenger.println("   "+s.getName());
            }
        }
    }
    
    private boolean parseLine(String line, int index){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        String group;
        String[] elements;
        boolean success = false;
        while(m.find()){
            success = true;
            group= m.group();
            elements = group.split("\\s+");
            group = elements[elements.length-1].trim();
            if (group.startsWith("=20")){
                group = group.replaceFirst("=20", "");
            }
            if (group.startsWith("=")){
                group = group.replaceFirst("=", "");
            }
            this.foundPms.add(new PmsInfo(this.file, index, line, group));
            //Messenger.println("Crucial warning added:"+group);
                    
        }
        return success;
    }
    
    @Override
    public ArrayList<PmsInfo> getContent(){
        return this.foundPms;
    }
    
}
