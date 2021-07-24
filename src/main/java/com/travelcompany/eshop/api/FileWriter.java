package com.travelcompany.eshop.api;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileWriter<T> {

    boolean writeFilesToTxt(List<T> t,String filename) throws FileNotFoundException;
    boolean writeFilesToExcel(List<T> t,String filename) throws FileNotFoundException;
}
