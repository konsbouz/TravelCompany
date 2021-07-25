package com.travelcompany.eshop.util;

import java.io.File;

    public enum Directory {
        FILE_DIRECTORY(System.getProperty("user.home") + File.separator + "travelcompany"+ File.separator ),
        BACK_UP_DIRECTORY(System.getProperty("user.home") + File.separator + "travelcompany" + File.separator + "dbbackup" + File.separator + "a"+ File.separator + "b" + File.separator);
        ;


        private String path;

        Directory(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
}
