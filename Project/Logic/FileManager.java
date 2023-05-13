package Project.Logic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FileManager {
    String path;
    FileInputStream file;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public FileManager(String path) {
        this.path = path;
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
        return BigDecimal.valueOf((Double) cell[5]).toPlainString();
    }

    public void readFile() {
        try {
            file = new FileInputStream(this.path);
            workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellTypeEnum()) {
                        case NUMERIC -> System.out.print(cell.getNumericCellValue() + "\t");
                        case STRING -> System.out.print(cell.getStringCellValue() + "\t");
                    }
                }
                System.out.println();
            }
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) {
        FileManager fm = new FileManager("./Project/Data.xlsx");
        List<String> teams = fm.getUniqueValues("location");
        System.out.println(teams);
    }
}
