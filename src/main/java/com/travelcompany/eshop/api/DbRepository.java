package com.travelcompany.eshop.api;

import java.sql.SQLException;
import java.util.List;

public interface DbRepository<T> {

    int[] addToDb(T t) throws Exception;
    T getFromDB(int id) throws SQLException;
    T getFromDB1(String name) throws SQLException;
    T updateToDb(int id,T t) throws SQLException;
    boolean DeleteFromDb(int id);
    List<T> GetAllFromDb() throws SQLException;

}
