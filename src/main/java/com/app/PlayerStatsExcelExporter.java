package com.app;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class PlayerStatsExcelExporter {

    public static void exportToExcel(ArrayList<Player> players, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Player Stats");

            // Header
            String[] headers = {"Player Name","Number","Block Errors","Block Assists","Block Solos",
                    "Attack Errors","Attack Kills","Dig Errors","Dig Successes",
                    "Set Errors","Set Assists","Serve Errors","Serve Aces",
                    "Pass Errors","Pass On Target","Pass Off Target"};

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Data rows
            int rowNum = 1;
            for (Player player : players) {
                Row row = sheet.createRow(rowNum++);
                int[][] s = player.getStats().stats;
                int c = 0;
                row.createCell(c++).setCellValue(player.getName());
                row.createCell(c++).setCellValue(player.getNumber());
                row.createCell(c++).setCellValue(s[0][0]); // blockErr
                row.createCell(c++).setCellValue(s[0][1]); // blockAss
                row.createCell(c++).setCellValue(s[0][2]); // blockSol
                row.createCell(c++).setCellValue(s[1][0]); // attackErr
                row.createCell(c++).setCellValue(s[1][1]); // attackKill
                row.createCell(c++).setCellValue(s[2][0]); // digErr
                row.createCell(c++).setCellValue(s[2][1]); // digSucc
                row.createCell(c++).setCellValue(s[3][0]); // setErr
                row.createCell(c++).setCellValue(s[3][1]); // setAss
                row.createCell(c++).setCellValue(s[4][0]); // serveErr
                row.createCell(c++).setCellValue(s[4][1]); // serveAce
                row.createCell(c++).setCellValue(s[5][0]); // passErr
                row.createCell(c++).setCellValue(s[5][1]); // passOnTarget
                row.createCell(c++).setCellValue(s[5][2]); // passOffTarget
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Save to file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            // System.out.println("Exported player stats to Excel: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}