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
public class SourceXmlTree extends XmlTree{
    
    private final ArrayList<PmsInfo> pmsInfo;
    private final File file;
    private final ArrayList<String> fileContent;
    
    private final String regex = "(?<=pms:/).*";
    
    public SourceXmlTree(File f, ArrayList<String> content) {
        super(f, content);
        this.file = f;
        this.pmsInfo = new ArrayList<>();
        this.fileContent=content;
    }
    
    @Override
    public void parseContent(){
        boolean success = false;
        String line="";
        int i=0;
        boolean currSuccess;
        for(i=0; i<this.fileContent.size(); i++){
            line = this.fileContent.get(i);
            currSuccess =this.parseLine(line, i);
            success = success || currSuccess;
        }
        
        if (!success){
            Messenger.println("No PMS-Strings have been found in File " +this.file.getName());
        }
        else{
           Messenger.println("PMS-Infos of "+this.file.getName());
            for(PmsInfo info: this.pmsInfo){
                Messenger.println("   "+info.getName());
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
            group = elements[elements.length-1];
            elements = group.split("/");
            if (elements.length > 2){
                group = elements[elements.length-2];
                this.pmsInfo.add(new PmsInfo(this.file, index, line, group));
            }
            else if (elements.length ==1) {
                group = elements[elements.length-1];
                this.pmsInfo.add(new PmsInfo(this.file, index, line, group));
            }
            else {
                success = false;
            }
        }
        return success;
    }
    
    @Override
    public ArrayList<PmsInfo> getContent(){
        return this.pmsInfo;
    }
}
