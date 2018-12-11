/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugarov.xml.finder.control;

import com.mugarov.xml.finder.model.FilesReader;
import com.mugarov.xml.finder.model.PmsInfo;
import com.mugarov.xml.finder.model.TreeComparator;
import com.mugarov.xml.finder.model.trees.SourceXmlTree;
import com.mugarov.xml.finder.model.trees.WarningXmlTree;
import com.mugarov.xml.finder.model.trees.XmlTree;
import com.mugarov.xml.finder.view.Messenger;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author black
 */
public class Initiator {
    
    private ArrayList<WarningXmlTree> warningLists;
    private ArrayList<SourceXmlTree> sources;
    private FilesReader freader;
    private String pathWarningDir;
    private String pathSourcesDir;
    
    /**
     * 
     * @param args Path to Directories [WarningLists Sources]
     */
    public Initiator(String[] args){
        
        if ( args.length < 2){
            Messenger.println("Wrong number of args - quit");
        }
        else{
            this.pathWarningDir = args[0];
            this.pathSourcesDir = args[1];
            this.init();
            Messenger.println("----------------------------");
            this.compare();
            Messenger.println("----------------------------");
        }  
    }

    private void init() {
        this.freader = new FilesReader();
        this.warningLists = new ArrayList<>();
        this.sources = new ArrayList<>();
        File[] childs;
        childs = this.freader.getChildrenFiles(this.pathWarningDir);
        WarningXmlTree currentWarningTree;
        if(childs == null){
            Messenger.println(this.pathWarningDir +" is not a valid directory path");
        }
        else{
            for(File f:childs){
                currentWarningTree = new WarningXmlTree(f, freader.readXML(f));
                currentWarningTree.parseContent();
                this.warningLists.add(currentWarningTree);
            }
        }
        SourceXmlTree currentSourceTree;
        childs = this.freader.getChildrenFiles(this.pathSourcesDir);
        if(childs == null){
            Messenger.println(this.pathSourcesDir +" is not a valid directory path");
        }
        else{
            for(File f:childs){
                currentSourceTree = new SourceXmlTree(f, freader.readXML(f));
                currentSourceTree.parseContent();
                this.sources.add(currentSourceTree);
            }
        }
    }
    
    private ArrayList<PmsInfo> compare(){
        TreeComparator comparator = new TreeComparator();
        ArrayList<PmsInfo> ret = comparator.compareTrees(this.warningLists, this.sources);
        for(PmsInfo info:ret){
            Messenger.println("WARNING : "+info.getFile().getName()+" on line "+ info.getLineNumber()+" is refering to "+ info.getName());
        }
        return ret;
    }
    
    
    
    public void update(){
        this.init();
    }
}
