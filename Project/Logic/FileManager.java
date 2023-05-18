package Project.Logic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FileManager {
    String path;
    FileInputStream file;
    XSSFWorkbook workbook;

    public FileManager(String path) {
        this.path = path;
    }

    private static String removeFloatingPoint(String n) {
        int floatingPointIndex = n.indexOf('.');

        if (floatingPointIndex >= 0) {
            return n.substring(0, floatingPointIndex);
        }   else {
            return n;
        }
    }

    public static String parseTeamID(Object[] cell) {
        return (String) cell[0];
    }

    public static String parseTeamName(Object[] cell) {
        return (String) cell[1];
    }

    public static String parseLocation(Object[] cell) {
        return (String) cell[2];
    }

    public static String parsePlayerID(Object[] cell) {
        return (String) cell[3];
    }

    public static String parsePlayerName(Object[] cell) {
        return (String) cell[4];
    }

    public static String parseSalary(Object[] cell) {
        String salary = removeFloatingPoint(BigDecimal.valueOf((Double) cell[5]).toPlainString());
        StringBuilder formattedSalary = new StringBuilder();
        int salaryLength = salary.length();

        for (int i = salaryLength - 1, j = 1; i >= 0; i--, j++) {
            formattedSalary.insert(0, salary.charAt(i));
            if (j % 3 == 0 && i != 0) {
                formattedSalary.insert(0, ",");
            }
        }
        return "$ " + formattedSalary;
    }

    public void deleteRows(String columnName, String matchValue) throws IOException {
        file = new FileInputStream(this.path);
        workbook = new XSSFWorkbook(file);
        int sheetIndex = 0; // assume we always use the first sheet

        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

        int columnIndex = -1;
        XSSFRow headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            XSSFCell cell = headerRow.getCell(i);
            if (cell != null && cell.getStringCellValue().equals(columnName)) {
                columnIndex = i;
                break;
            }
        }

        for (int i = sheet.getLastRowNum(); i >= 1; i--) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                XSSFCell cell = row.getCell(columnIndex);
                if (cell != null && cell.getStringCellValue().equals(matchValue)) {
                    sheet.removeRow(row);
                }
            }
        }

        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
        outputStream.close();
        file.close();
        workbook.close();
    }

    public Object[] getRow(String columnName, String searchValue) {
        try {
            file = new FileInputStream(this.path);
            workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int columnIndex = -1;

            Row headerRow = sheet.getRow(0);
            Iterator<Cell> headerIterator = headerRow.cellIterator();
            while (headerIterator.hasNext()) {
                Cell headerCell = headerIterator.next();
                if (headerCell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = headerCell.getColumnIndex();
                    break;
                }
            }

            if (columnIndex == -1) {
                System.out.println("Column not found: " + columnName);
                return null;
            }

            for (Row row : sheet) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null && cell.getStringCellValue().equalsIgnoreCase(searchValue)) {
                    Object[] rowData = new Object[row.getLastCellNum()];
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        Cell currentCell = row.getCell(i);
                        switch (currentCell.getCellTypeEnum()) {
                            case STRING -> rowData[i] = currentCell.getStringCellValue();
                            case NUMERIC -> rowData[i] = currentCell.getNumericCellValue();
                            case BOOLEAN -> rowData[i] = currentCell.getBooleanCellValue();
                            default -> rowData[i] = null;
                        }
                    }
                    return rowData;
                }
            }
            file.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public Object[] getAllRows(String columnName, String searchValue) {
        try {
            file = new FileInputStream(this.path);
            workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int columnIndex = -1;

            Row headerRow = sheet.getRow(0);
            Iterator<Cell> headerIterator = headerRow.cellIterator();
            while (headerIterator.hasNext()) {
                Cell headerCell = headerIterator.next();
                if (headerCell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = headerCell.getColumnIndex();
                    break;
                }
            }

            if (columnIndex == -1) {
                System.out.println("Column not found: " + columnName);
                return null;
            }

            List<Object[]> rows = new ArrayList<>();
            for (Row row : sheet) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null && cell.getStringCellValue().equalsIgnoreCase(searchValue)) {
                    Object[] rowData = new Object[row.getLastCellNum()];
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        Cell currentCell = row.getCell(i);
                        switch (currentCell.getCellTypeEnum()) {
                            case STRING -> rowData[i] = currentCell.getStringCellValue();
                            case NUMERIC -> {
                                if (DateUtil.isCellDateFormatted(currentCell)) {
                                    rowData[i] = currentCell.getDateCellValue();
                                } else {
                                    rowData[i] = currentCell.getNumericCellValue();
                                }
                            }
                            case BOOLEAN -> rowData[i] = currentCell.getBooleanCellValue();
                            default -> rowData[i] = null;
                        }
                    }
                    rows.add(rowData);
                }
            }
            file.close();
            return rows.toArray();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public List<String> getUniqueValues(String columnName) {
        List<String> uniqueValues = new ArrayList<>();
        try {
            file = new FileInputStream(this.path);
            workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int columnIndex = -1;

            Row headerRow = sheet.getRow(0);
            Iterator<Cell> headerIterator = headerRow.cellIterator();
            while (headerIterator.hasNext()) {
                Cell headerCell = headerIterator.next();
                if (headerCell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = headerCell.getColumnIndex();
                    break;
                }
            }

            if (columnIndex == -1) {
                System.out.println("Column not found: " + columnName);
                return null;
            }

            for (Row row : sheet) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null && !uniqueValues.contains(cell.getStringCellValue())) {
                    uniqueValues.add(cell.getStringCellValue());
                }
            }
            file.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return uniqueValues;
    }

    public void addRow(String[] rowData) {
        try {
            FileInputStream inputStream = new FileInputStream(this.path);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            for (int i = 0; i < rowData.length; i++) {
                Cell newCell = newRow.createCell(i);
                if (i == rowData.length - 1) { // check if last column
                    try {
                        double numericValue = Double.parseDouble(rowData[i]);
                        newCell.setCellValue(numericValue);
                        newCell.setCellType(CellType.NUMERIC);
                    } catch (NumberFormatException e) {
                        newCell.setCellValue(rowData[i]);
                    }
                } else {
                    newCell.setCellValue(rowData[i]);
                }
            }

            FileOutputStream outputStream = new FileOutputStream(this.path);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] teamData(String teamID, String teamName, String teamLocation) {
        return new String[]{teamID, teamName, teamLocation};
    }

    public String[] footballerData(String footballerID, String footballerName, String footballerSalary) {
        return new String[]{footballerID, footballerName, footballerSalary};
    }

    public void addDataRow(String[] a, String[] b) {
        String[] result = new String[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        addRow(result);
    }

    public static void main(String[] args) {
        FileManager fm = new FileManager("./Project/Data.xlsx");
        List<String> teams = fm.getUniqueValues("location");
        System.out.println(teams);
        System.out.println("LINE BREAK");

        Object[] team = fm.getRow("team_name", "Broncos");
        System.out.println(parseTeamName(team));
        System.out.println(parseSalary(team));
    }
}
