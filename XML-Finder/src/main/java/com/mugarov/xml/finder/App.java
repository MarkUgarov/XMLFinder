/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugarov.xml.finder;

import com.mugarov.xml.finder.control.Initiator;

/**
 *
 * @author black
 */
public final class App {
    
    public static void main(String[] args){
        String[] argus;
        if (args.length == 0){
            argus = new String[]{"WarningSources", "ExportSources"};
        }
        else {
            argus = args;
        }
        new Initiator(argus);
        
    }
    
}
