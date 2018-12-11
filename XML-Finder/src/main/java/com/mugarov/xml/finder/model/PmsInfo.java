/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugarov.xml.finder.model;

import java.io.File;

/**
 *
 * @author black
 */
public final class PmsInfo {

    private final File file;
    private final int lineNumber;
    private final String line;
    private final String name;
    
    public PmsInfo(File f, int lineNumber, String line, String name){
        this.file = f;
        this.lineNumber = lineNumber;
        this.line = line;
        this.name = name;
    }
    
    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return the lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * @return the line
     */
    public String getLine() {
        return line;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    public boolean equals(PmsInfo pmsInfo){
        return this.getName().equals(pmsInfo.getName());
    }
}
