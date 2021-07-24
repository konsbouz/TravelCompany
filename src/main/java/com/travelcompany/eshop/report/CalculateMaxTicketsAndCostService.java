package com.travelcompany.eshop.report;
import com.travelcompany.eshop.domain.OrderedTicket;
import com.travelcompany.eshop.service.DBConnectionService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalculateMaxTicketsAndCostService {
    List<OrderedTicket> totalTicketsAndSum = new ArrayList<>();

    public List<OrderedTicket> totalTicketsAndSumForAll() throws SQLException {
        String query1 = "select count(*) from orderedtickets;";
        String query2 = "select sum(AmountPaid) from orderedtickets;";
        OrderedTicket orderedTicket = new OrderedTicket(CountTickets(query1),SumForTickets(query2));
        totalTicketsAndSum.add(orderedTicket);

        return totalTicketsAndSum;

    }
    public int CountTickets(String query) throws SQLException {

        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(query);
        ResultSet resultSet = st.executeQuery();
        resultSet.next();
        int totaltickets;
        totaltickets = resultSet.getInt(1);
        return totaltickets;

    }

    public double SumForTickets(String query) throws SQLException {

        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(query);
        ResultSet resultSet = st.executeQuery();
        resultSet.next();
        double sum;
        sum = resultSet.getDouble(1);
        return sum;

    }

}
