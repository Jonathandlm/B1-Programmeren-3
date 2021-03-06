package be.leerstad.eindwerk.business.printer;

import be.leerstad.eindwerk.business.report.Chart;
import be.leerstad.eindwerk.util.DateUtil;
import be.leerstad.eindwerk.util.PropertyUtil;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartUtilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Printer {

    private static final Logger LOG = Logger.getLogger(Printer.class.getName());
    private static final String TEMPLATE_FILENAME = PropertyUtil.getFileLocation("PDF_TEMPLATE_LOCATION").getPath();

    public void createPdfFromTemplate(String destination, String title, LinkedHashMap<String, ?> data) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfDocument sourcePdfDocument = new PdfDocument(new PdfReader(TEMPLATE_FILENAME), new PdfWriter(baos));

        PdfAcroForm pdfAcroForm = PdfAcroForm.getAcroForm(sourcePdfDocument, true);
        Map<String, PdfFormField> pdfFormFieldMap = pdfAcroForm.getFormFields();
        pdfFormFieldMap.get("title").setValue(title).setJustification(PdfFormField.ALIGN_CENTER);
        pdfFormFieldMap.get("date").setValue(DateUtil.today()).setJustification(PdfFormField.ALIGN_RIGHT);
        Rectangle bodyRectangle = pdfAcroForm.getField("body").getWidgets().get(0).getRectangle().toRectangle();
        pdfAcroForm.flattenFields();

        sourcePdfDocument.close();
        sourcePdfDocument = new PdfDocument(new PdfReader(new ByteArrayInputStream(baos.toByteArray())));

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(destination));
        Document doc = new Document(pdfDoc);

        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new Pagination(sourcePdfDocument.getFirstPage().copyAsFormXObject(pdfDoc)));

        sourcePdfDocument.close();

        doc.setRenderer(new ColumnDocumentRenderer(doc, new Rectangle[]{bodyRectangle}));

        Table table = getData(data);
        if (table != null) {
            doc.add(table);
        } else {
            LOG.log(Level.ERROR, "Unable to create tables and charts from " + data);
            doc.add(new Paragraph("NO DATA FOUND").setFontSize(36).setBold().setFontColor(Color.RED)
                    .setTextAlignment(TextAlignment.CENTER));
        }

        doc.close();

    }

    private Table getData(LinkedHashMap<String, ?> data) {
        Object object;
        try {
            object = data.values().toArray()[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        if (object instanceof Integer) {
            Color headerColor = new DeviceRgb(230,232,105);
            Color cellColor = new DeviceRgb(247,247,247);
            Table table = new Table(new float[]{3,1});
            table.setWidthPercent(80).setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(new Cell().add("Description").setBackgroundColor(headerColor).setFontSize(14));
            table.addCell(new Cell().add("Quantity").setBackgroundColor(headerColor).setFontSize(14));

            data.forEach((k,v) -> {
                table.addCell(k).setBackgroundColor(cellColor);
                table.addCell(String.valueOf(v)).setBackgroundColor(cellColor);
            });

            Cell chartCell = new Cell(1, 2).add(getChart((LinkedHashMap<String, Number>) data));
            table.addCell(chartCell);

            return table;

        } else if (object instanceof LinkedHashMap) {
            Color titleColor = new DeviceRgb(0,80,91);
            Table table = new Table(new float[]{1});
            table.setWidthPercent(100);

            data.forEach((k,v) -> {
                Cell titleCell = new Cell().setBackgroundColor(titleColor).setFontColor(Color.WHITE).setFontSize(16)
                        .setTextAlignment(TextAlignment.CENTER).setMarginTop(10).setBorder(Border.NO_BORDER);
                table.addCell(titleCell.add(k));
                Cell tableCell = new Cell().setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(10).setBorder(Border.NO_BORDER);
                table.addCell(tableCell.add(getData((LinkedHashMap)v)));
            } );

            Table chartTable = new Table(new float[]{1});
            chartTable.setKeepTogether(true);
            Cell titleCell = new Cell().setBackgroundColor(titleColor).setFontColor(Color.WHITE).setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER).setMarginTop(10).setBorder(Border.NO_BORDER);
            chartTable.addCell(titleCell.add("Overview chart"));
            Cell chartCell = new Cell(1, 2)
                    .add(getCompleteChart((LinkedHashMap<String, LinkedHashMap<String, Number>>) data)).setBorder(Border.NO_BORDER);
            chartTable.addCell(chartCell);

            table.addCell(chartTable);

            return table;
        }
        return null;
    }

    private Image getChart(LinkedHashMap<String, Number> data) {
        byte[] image = null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ChartUtilities.writeChartAsPNG(baos, Chart.getSimpleChart(data), 300, 180);
            image = baos.toByteArray();

        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to create chart ", e);
        }

        return new Image(ImageDataFactory.createPng(image));
    }

    private Image getCompleteChart(LinkedHashMap<String, LinkedHashMap<String, Number>> data) {
        byte[] image = null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ChartUtilities.writeChartAsPNG(baos, Chart.getComplexChart(data), 500, 500);
            image = baos.toByteArray();

        } catch (IOException e) {
            LOG.log(Level.ERROR, "Unable to create chart ", e);
        }

        return new Image(ImageDataFactory.createPng(image));
    }
}
