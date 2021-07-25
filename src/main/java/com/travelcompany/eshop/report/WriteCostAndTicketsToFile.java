package com.travelcompany.eshop.report;
import com.travelcompany.eshop.api.FileWriter;
import com.travelcompany.eshop.domain.OrderedTicket;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteCostAndTicketsToFile implements FileWriter<OrderedTicket> {


    @Override
    public boolean writeFilesToTxt(List<OrderedTicket> list, String filepath) throws FileNotFoundException {
        File F = new File(filepath);

        try (PrintStream fileWriter = new PrintStream(F);) {

            List<String> newlist = new ArrayList<>();
            for(OrderedTicket orderedTicket : list) {

                newlist.add((String.valueOf(orderedTicket)));
            }

            for (String element : newlist) {
                fileWriter.println(element);

            }
        }
        return false;
    }

    @Override
    public boolean writeFilesToExcel(List<OrderedTicket> list, String filepath) throws FileNotFoundException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("OrderedTicketsAndSum");

        Row row1 = sheet.createRow(0);
        writeHeaders(row1);
        int rowCount = 0;
        for (OrderedTicket orderedTicket : list) {
            Row row = sheet.createRow(++rowCount);

            writeOrderedTicket(orderedTicket, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeOrderedTicket(OrderedTicket orderedTicket, Row row) {
        Cell cell = row.createCell( 0);
        cell.setCellValue(orderedTicket.getTickets());
        cell = row.createCell(1);
        cell.setCellValue(orderedTicket.getSum());
        cell = row.createCell(2);

    }
    private void writeHeaders(Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue("Total Tickets");
        cell = row.createCell(1);
        cell.setCellValue("Total Amount");
        cell = row.createCell(2);

    }
}
