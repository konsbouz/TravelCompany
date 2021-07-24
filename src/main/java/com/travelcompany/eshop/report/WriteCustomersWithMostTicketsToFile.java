package com.travelcompany.eshop.report;
import com.travelcompany.eshop.api.FileWriter;
import com.travelcompany.eshop.domain.Purchase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteCustomersWithMostTicketsToFile implements FileWriter<Purchase> {

    @Override
    public boolean writeFilesToTxt(List<Purchase> list, String filepath) throws FileNotFoundException {

        File F = new File(filepath);

        try (PrintStream fileWriter = new PrintStream(F);) {

            List<String> newlist = new ArrayList<>();
            for (Purchase purchase : list) {
                newlist.add((String.valueOf(purchase)));
            }
            for (String element : newlist) {
                fileWriter.println(element);

            }
        }
        return false;

    }

    @Override
    public boolean writeFilesToExcel(List<Purchase> list, String filepath) throws FileNotFoundException {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("CusstomersAndTickets");

        Row row1 = sheet.createRow(0);
        writeHeaders(row1);

        int rowCount = 0;
        for (Purchase purchase : list) {
            Row row = sheet.createRow(++rowCount);

            writeItinerary(purchase, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeItinerary(Purchase purchase, Row row) {


        Cell cell = row.createCell(5);
        cell.setCellValue(purchase.getNumberOfPurchases());
        cell = row.createCell(0);
        cell.setCellValue(String.valueOf(purchase.getCustomer().getName()));
        cell = row.createCell(1);
        cell.setCellValue(String.valueOf(purchase.getCustomer().getEmail()));
        cell = row.createCell(2);
        cell.setCellValue(String.valueOf(purchase.getCustomer().getAddressCity()));
        cell = row.createCell(3);
        cell.setCellValue(String.valueOf(purchase.getCustomer().getNationality()));
        cell = row.createCell(4);
        cell.setCellValue(String.valueOf(purchase.getCustomer().getCustomerCategory()));

    }

    private void writeHeaders(Row row) {

        Cell cell = row.createCell(0);
        cell.setCellValue("Name");
        cell = row.createCell(1);
        cell.setCellValue("Email");
        cell = row.createCell(2);
        cell.setCellValue("AddressCity");
        cell = row.createCell(3);
        cell.setCellValue("Nationality");
        cell = row.createCell(4);
        cell.setCellValue("CustomerCategory");
        cell = row.createCell(5);
        cell.setCellValue("Total Purchased Tickets");
    }

}

