package com.example.worldlineusecase.Utility;
import java.io.IOException;
import java.util.List;


import com.example.worldlineusecase.Entity.Employee;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

public class PdfGenerator {
    private List<Employee> employeeList;
    public void generate(HttpServletResponse response, List<Employee> employeeList) throws DocumentException, IOException {

        // Setting the employee list
       this.employeeList = employeeList;
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph paragraph = new Paragraph("List Of Employees", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(5); // 5 columns: FirstName, LastName, Email, ModuleNames, Status
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3, 3, 3, 3, 3}); // Adjust column widths as needed
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.MAGENTA);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("FirstName", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("LastName", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("ModuleNames", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);

        for (Employee e : employeeList) {
            table.addCell(e.getFirstName());
            table.addCell(e.getLastName());
            table.addCell(e.getEmail());
            table.addCell(String.join("\n", e.getModuleName())); // Join module names with comma
            table.addCell(String.join("\n", e.getStatus())); // Join status with comma
        }

        document.add(table);
        document.close();
    }
}





