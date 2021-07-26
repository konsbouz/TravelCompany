package com.travelcompany.eshop.repositories;

import com.travelcompany.eshop.api.DbRepository;
import com.travelcompany.eshop.domain.Category;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.database.DBConnectionService;
import com.travelcompany.eshop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements DbRepository<Customer> {
    Logger logger = LoggerFactory.getLogger(CustomerRepository.class.getName());

    @Override
    public int[] addToDb(Customer customer) throws Exception {

        String SqlQuery = "Insert into customers (Id ,FullName, Email, AddressCity, Nationality,Category) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            st.setInt(1, customer.getId());
            st.setString(2, customer.getName());
            st.setString(3, customer.getEmail());
            st.setString(4, customer.getAddressCity());
            st.setString(5, customer.getNationality());
            st.setString(6, String.valueOf(customer.getCustomerCategory()));
            st.addBatch();
            int[] rowsAffected = st.executeBatch();
            logger.info("Insert command was successful with {} row(s) affected.", rowsAffected);
            return rowsAffected;
        } catch (Exception e) {
            logger.error("insert customer failed", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();

            }
            DBConnectionService.getConnection().close();
        }
    }

    @Override
    public Customer getFromDB(int id) throws SQLException, ServiceException {
        String SqlQuery = "select * from customers where id = ? ";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            Customer customer = new Customer();
            customer.setId(resultSet.getInt(1));
            customer.setName(resultSet.getString(2));
            customer.setEmail(resultSet.getString(3));
            customer.setAddressCity(resultSet.getString(4));
            customer.setNationality(resultSet.getString(5));
            customer.setCustomerCategory(Category.valueOf(resultSet.getString(6)));
            return customer;
        } catch (Exception e) {
            logger.error("retrieve customer from db failed", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();

            }

            DBConnectionService.getConnection().close();
        }
    }

    @Override
    public Customer getFromDB1(String name) throws SQLException, ServiceException {

        String SqlQuery = "select * from customers where Fullname = ? ";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            Customer customer = new Customer();
            customer.setId(resultSet.getInt(1));
            customer.setName(resultSet.getString(2));
            customer.setEmail(resultSet.getString(3));
            customer.setAddressCity(resultSet.getString(4));
            customer.setNationality(resultSet.getString(5));
            customer.setCustomerCategory(Category.valueOf(resultSet.getString(6)));
            return customer;
        } catch (Exception e) {
            logger.error("retrieve customer from db with name failed", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();
        }
    }

    @Override
    public Customer updateToDb(int id, Customer customer) throws SQLException, ServiceException {

        String SqlQuery = "UPDATE customers SET  FullName = ? ,Email = ? ,AddressCity = ? " +
                ",Nationality = ?, Category = ? where id= ? ";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            st.setString(1, customer.getName());
            st.setString(2, customer.getEmail());
            st.setString(3, customer.getAddressCity());
            st.setString(4, customer.getNationality());
            st.setString(5, String.valueOf(customer.getCustomerCategory()));
            st.setInt(6, id);
            st.executeUpdate();
            st.close();

            logger.info("The update was successful ");
            return customer;
        } catch (Exception e) {
            logger.error("update customer to db failed", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();
        }
    }

    @Override
    public boolean DeleteFromDb(int id) {
        return false;
    }

    @Override
    public List<Customer> GetAllFromDb() throws SQLException, ServiceException {
        String SqlQuery = "select * from customers";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            ResultSet resultSet = st.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setEmail(resultSet.getString(3));
                customer.setAddressCity(resultSet.getString(4));
                customer.setNationality(resultSet.getString(5));
                customer.setCustomerCategory(Category.valueOf(resultSet.getString(6)));
                customers.add(customer);
            }
            return customers;
        } catch (Exception e) {
            logger.error("retrieve all customers from db failed", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();

        }
    }
}
