/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugarov.xml.finder.model;

import com.mugarov.xml.finder.model.trees.SourceXmlTree;
import com.mugarov.xml.finder.model.trees.WarningXmlTree;
import java.util.ArrayList;

/**
 *
 * @author black
 */
public class TreeComparator {
    
    public TreeComparator(){
        
    }
    
    public ArrayList<PmsInfo> compareTrees(ArrayList<WarningXmlTree> warningTrees, ArrayList<SourceXmlTree> sourceTrees){
        ArrayList<PmsInfo> allWarnings = new ArrayList<>();
        for(WarningXmlTree w:warningTrees){
            allWarnings.addAll(w.getContent());
        }
        ArrayList<PmsInfo> allSources = new ArrayList<>();
        for(SourceXmlTree s:sourceTrees){
            allSources.addAll(s.getContent());
        }
        return this.compareLists(allWarnings, allSources);
        
    }
    
    public ArrayList<PmsInfo> compareLists(ArrayList<PmsInfo> warnings, ArrayList<PmsInfo> sources ){
        ArrayList<PmsInfo> overlaps = new ArrayList<>();
        PmsInfo warningsPms;
        boolean found;
        for(PmsInfo sourcePms:sources){
            found = false;
            for(int index = 0; index<warnings.size() && !found; index++){
                warningsPms = warnings.get(index);
                if (warningsPms.equals(sourcePms)){
                    overlaps.add(sourcePms);
                    found = true;
                }
            }
            
        }
        
        return overlaps;
    }
    
}
