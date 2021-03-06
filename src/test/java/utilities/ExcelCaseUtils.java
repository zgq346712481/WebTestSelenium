package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ExcelCaseUtils {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
    public static String setExcelFile(String Path, String SheetName) throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);//1.读取Excel文档对象
            ExcelWSheet  = ExcelWBook.getSheet(SheetName);//2.获取要解析的表格（SheetName）
            int lastRowNum = ExcelWSheet.getLastRowNum();  //获得最后一行的行号
        } catch (Exception e) {
            throw (e);
        }
        return Path;
    }

    public static String getExcelNextCell(String Path, String SheetName,String ElementName) throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);//1.读取Excel文档对象
            ExcelWSheet  = ExcelWBook.getSheet(SheetName);//2.获取要解析的表格（SheetName）
            int lastRowNum = ExcelWSheet.getLastRowNum();  //获得最后一行的行号
            for (int i = 0; i <= lastRowNum; i++) {//遍历每一行
                XSSFRow rowNow = ExcelWSheet.getRow(i);//3.获得要解析的行
                //4.获得每个单元格中的内容（String）
                for(int j =0;j<=4;j++) {//j 同行中从左-->右的列的序号 根据用例模板表头列数设置
                    String stringCellValueNow = rowNow.getCell(j).getStringCellValue();//cellnum：0 对应行的第一个列单元格内容
                    //遍历判断，如果等于元素名称，输出同行下一个单元格内容（同行右边列对应的列坐标）
//                    System.out.println(stringCellValueNow+"--");
                    if (rowNow.getCell(j).getStringCellValue().equalsIgnoreCase(ElementName)){
                        String ElementValue = rowNow.getCell(j+1).getStringCellValue();
//                       System.out.println("usernameTextNext:"+ElementNameNext);
                        return ElementValue;
                    }
                }
//                System.out.println(stringCellValue0+"--"+stringCellValue1+"--"+stringCellValue2+"--"+stringCellValue3+"--"+stringCellValue4);
            }
        } catch (Exception e) {
            throw (e);
        }
        return Path;
    }


    public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception {
        String[][] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);//读取文件
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);//读取Excel文档对象
            ExcelWSheet = ExcelWBook.getSheet(SheetName);//从文档中获取要解析的表格（按表格名称）
            int startCol = 1;//初始化表头
            int ci = 0;
            int cj = 0;
            int totalRows = 1;//用例行数 定义2行，表头第一行，第二行数据
            int totalCols = 2;//用例表格列数：用例名称第0列，用户名第1列，密码第2列，从第1列开始读取数据
            tabArray = new String[totalRows][totalCols];

            for (int j = startCol; j <= totalCols; j++, cj++) {
                tabArray[ci][cj] = getCellData(iTestCaseRow, j);//遍历用例表格中列的行号
                System.out.println("tabArray:"+tabArray[ci][cj]);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return (tabArray);
    }

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public static String getCellData(int RowNum, int ColNum)
            throws Exception { //根据行列获取单元格数据
        String CellData;
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            String ExceptionPrint= "CellData单元格中内容类型非字符串";
            System.out.println("CellData单元格中内容类型非字符串:");
            return "ExceptionPrint";// excel单元格中数字前面加单引号转换为字符串类型： '18103835542
        }
    }

    public static String getTestCaseName(String sTestCase)
            throws Exception {
        String value = sTestCase; //用例名称
        try {
            int posi = value.indexOf("@");
            value = value.substring(0, posi);
            posi = value.lastIndexOf(".");
            value = value.substring(posi + 1);
            return value;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static int getRowContains(String sTestCaseName, int colNum) throws Exception {
        int i;
        try {
            int rowCount = ExcelCaseUtils.getRowUsed();
            for (i = 0; i < rowCount; i++) {
                if (ExcelCaseUtils.getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {
                    break;
                }
            }
            return i;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static int getRowUsed() throws Exception {
        try {
            int RowCount = ExcelWSheet.getLastRowNum();
            return RowCount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }
}
