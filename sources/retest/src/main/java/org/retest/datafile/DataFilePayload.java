/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 29, 2016, 1:14:49 PM
 * ****************************************************************
 */
package org.retest.datafile;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreivo
 */
public class DataFilePayload {

    private String fileName;
    private List<List<Object>> records;

    public DataFilePayload(String fileName) {
        this.fileName = fileName;
        records = new ArrayList<>();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<List<Object>> getRecords() {
        return records;
    }

    public void setRecords(List<List<Object>> records) {
        this.records = records;
    }

    public void addItem(List<Object> item) {
        records.add(item);
    }

    public int getSize() {
        return records.size();
    }

    public List<Object> get(int i) {
        return records.get(i);
    }

}
