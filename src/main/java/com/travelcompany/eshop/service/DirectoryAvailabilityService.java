package com.travelcompany.eshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryAvailabilityService {
    Logger logger = LoggerFactory.getLogger(DirectoryAvailabilityService.class.getName());

    public boolean isAvailable(Path filepath) {
        if (Files.exists(filepath) && Files.isDirectory(filepath)) {

            return true;
        } else {

            logger.error("System requires the proper configuration for database backup");
            return false;
        }
    }
}
