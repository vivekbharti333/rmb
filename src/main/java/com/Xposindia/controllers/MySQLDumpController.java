package com.Xposindia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MySQLDumpController {

    @Value("${dataSource.user}")
    private String dbUser;

    @Value("${dataSource.password}")
    private String dbPassword;

    @Value("${dataSource.databaseName}")
    private String dbName;

    @RequestMapping(path = "/downloadDump", method = RequestMethod.GET)
    public void downloadDump(HttpServletResponse response) throws IOException {
        try {
            // Run mysqldump command
            ProcessBuilder pb = new ProcessBuilder(
                "mysqldump",
                "-u", dbUser,
                "-p" + dbPassword,  // Correct way to pass password
                dbName
            );
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Set response headers
            response.setContentType("application/sql");
            response.setHeader("Content-Disposition", "attachment; filename=\"data.sql\"");

            // Stream mysqldump output directly to HTTP response
            try (InputStream in = process.getInputStream();
                 OutputStream out = response.getOutputStream()) {
                in.transferTo(out);
            }

            process.waitFor();

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dump failed: " + e.getMessage());
        }
    }
}
