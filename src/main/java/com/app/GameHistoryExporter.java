package com.app;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GameHistoryExporter {

    public static void saveGame(List<Player> players, String filePath, String name) throws IOException {
        File file = new File(filePath);
        Workbook workbook;

        // If the file exists, open it — otherwise create a new one
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
            }
        } else {
            workbook = new XSSFWorkbook();
        }

        // Create a unique sheet name based on date/time
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
        Sheet sheet = workbook.createSheet(name + "_" + timestamp);

        // Header row
        Row header = sheet.createRow(0);
        String[] columns = {
            "Name", "Number", "Position",
            "Block Errors", "Block Assists", "Block Solos",
            "Attack Errors", "Attack Kills",
            "Dig Errors", "Dig Success",
            "Set Errors", "Set Assists",
            "Serve Errors", "Serve Aces",
            "Pass Errors", "Pass On Target", "Pass Off Target"
        };

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        // Create header cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        // Fill player stats
        int rowNum = 1;
        for (Player p : players) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(p.getName());
            row.createCell(1).setCellValue(p.getNumber());
            row.createCell(2).setCellValue(p.getPosition());

            int[][] stats = p.getStats().stats; // existing 6x3 array for stats
            int col = 3;
            // Flatten and write stats
            for (int[] statRow : stats) {
                for (int stat : statRow) {
                    if (stat != -1) { // Skip unused stats
                        row.createCell(col++).setCellValue(stat);
                    }
                }
            }
        }
        
        // Auto-size columns
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Save the workbook back to the file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }

        workbook.close();
        // System.out.println("Saved game to sheet: " + sheet.getSheetName());
    }
}