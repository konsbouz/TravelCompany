package com.travelcompany.eshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryAvailabilityChecker {
    Logger logger = LoggerFactory.getLogger(DirectoryAvailabilityChecker.class.getName());

    public boolean isOK(Path filepath) {
        if (Files.exists(filepath) && Files.isDirectory(filepath)) {



            return true;
        } else {

            logger.error("System requires the proper configuration for database backup");
            return false;
        }

    }


}
