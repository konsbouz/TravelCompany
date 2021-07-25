package com.travelcompany.eshop.api;

import java.sql.SQLException;
import java.util.List;

public interface DbRepository<T> {

    int[] addToDb(T t) throws Exception;
    T getFromDB(int id) throws Exception;
    T getFromDB1(String name) throws Exception;
    T updateToDb(int id,T t) throws Exception;
    boolean DeleteFromDb(int id);
    List<T> GetAllFromDb() throws Exception;

}
