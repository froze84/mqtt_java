package listener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Excel_export {
   public static void main() throws IOException{
   try
   {
       FileInputStream input = new FileInputStream("/home/leepeng/Desktop/Writesheet.xlsx");
       XSSFWorkbook workbook = new XSSFWorkbook(input);
       XSSFSheet worksheet = workbook.getSheetAt(0);
       int lastRow=worksheet.getLastRowNum();
       System.out.println("last row is " + lastRow);
       Row row = worksheet.createRow(++lastRow);
       row.createCell(0).setCellValue(Listener.getClientid());
       row.createCell(1).setCellValue(Listener.getTopic());
       row.createCell(2).setCellValue(Listener.getMessage());
       input.close();
       FileOutputStream output =new FileOutputStream(new File("/home/leepeng/Desktop/Writesheet.xlsx"));  
       //write changes
       workbook.write(output);
       output.close();
       System.out.println(" is successfully written");
    }   
    catch (FileNotFoundException ex) 
    {
            Logger.getLogger(Excel_export.class.getName()).log(Level.SEVERE, null, ex);
    } 
    catch (IOException e) 
    {
            e.printStackTrace();
    }
   }
}
