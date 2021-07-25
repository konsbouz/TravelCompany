package com.travelcompany.eshop.report;

import com.travelcompany.eshop.api.FileWriter;
import com.travelcompany.eshop.domain.Customer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteCustomersWithNoTicketsToFile implements FileWriter<Customer> {
    @Override
    public boolean writeFilesToTxt(List<Customer> list, String filepath) throws FileNotFoundException {

        File F = new File(filepath);

        try (PrintStream fileWriter = new PrintStream(F);) {
            List<String> newlist = new ArrayList<>();
            for (Customer customer : list) {
                newlist.add((String.valueOf(customer)));
            }
            for (String element : newlist) {
                fileWriter.println(element);
            }
        }
        return false;


    }

    @Override
    public boolean writeFilesToExcel(List<Customer> t, String filepath) throws FileNotFoundException {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("CustomersWithNoTickets");

        Row row1 = sheet.createRow(0);
        writeHeaders(row1);
        int rowCount = 0;
        for (Customer customer : t) {
            Row row = sheet.createRow(++rowCount);

            writeItinerary(customer, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeItinerary(Customer customer, Row row) {


        Cell cell = row.createCell(0);
        cell.setCellValue(customer.getName());
        cell = row.createCell(1);
        cell.setCellValue(customer.getEmail());
        cell = row.createCell(2);
        cell.setCellValue(customer.getAddressCity());
        cell = row.createCell(3);
        cell.setCellValue(customer.getNationality());
        cell = row.createCell(4);
        cell.setCellValue(String.valueOf(customer.getCustomerCategory()));


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
    }


}
