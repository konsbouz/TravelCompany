package com.travelcompany.eshop.report;

import com.travelcompany.eshop.api.FileWriter;
import com.travelcompany.eshop.domain.Itinerary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class WriteToFileItineraries implements FileWriter<Itinerary> {

    @Override
    public boolean writeFilesToExcel(List<Itinerary> list, String filepath) throws FileNotFoundException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Offered Itineraries");

        Row row1 = sheet.createRow(0);
        writeHeaders(row1);

        int rowCount = 0;
        for (Itinerary itinerary : list) {
            Row row = sheet.createRow(++rowCount);

            writeItinerary(itinerary, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeItinerary(Itinerary itinerary, Row row) {

        Cell cell = row.createCell(0);
        cell.setCellValue(itinerary.getDepartureAirportId());
        cell = row.createCell(1);
        cell.setCellValue(itinerary.getDestinationAirportId());
        cell = row.createCell(2);
        cell.setCellValue(itinerary.getDepartureDate());
        cell = row.createCell(3);
        cell.setCellValue(itinerary.getAirlines());
        cell = row.createCell(4);
        cell.setCellValue(itinerary.getPrice());

    }

    private void writeHeaders(Row row) {

        Cell cell = row.createCell(0);
        cell.setCellValue("DepartureAirportId");
        cell = row.createCell(1);
        cell.setCellValue("DestinationAirportId");
        cell = row.createCell(2);
        cell.setCellValue("DepartureDate");
        cell = row.createCell(3);
        cell.setCellValue("Airlines");
        cell = row.createCell(4);
        cell.setCellValue("Price");
    }

    @Override
    public boolean writeFilesToTxt(List<Itinerary> list, String filepath) throws FileNotFoundException {

        File F = new File(filepath);

        try (PrintStream fileWriter = new PrintStream(F);) {
            List<String> newlist = new ArrayList<>();
            for (Itinerary itinerary : list) {
                newlist.add((String.valueOf(itinerary)));
            }
            for (String element : newlist) {
                fileWriter.println(element);
            }
        }
        return false;
    }
}



