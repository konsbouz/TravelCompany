package com.travelcompany.eshop.service;
import com.travelcompany.eshop.util.Directory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParserService {
    Logger logger = LoggerFactory.getLogger(ParserService.class.getName());
    private static final CSVFormat format = CSVFormat.EXCEL ;
    private static final String directory = Directory.FILE_DIRECTORY.getPath();


    public static CSVParser CsvParser(String filename) throws IOException {
        Logger logger = LoggerFactory.getLogger(CSVParser.class.getName());


        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(directory+ filename));
            CSVParser records = CSVParser.parse(lineReader, format.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            logger.info("CSV file has been read successfully ");

            return records;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}