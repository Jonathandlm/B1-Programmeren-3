package be.leerstad.eindwerk.business.printer;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.filter.TextRegionEventFilter;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredEventListener;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PrinterTest {

    @Test
    public void testCreatePdfFromTemplate() throws Exception {
        String emptyDestination = "./test/testEmpty.pdf";
        String faultDestination = "./test/testFault.pdf";
        String smallDestination = "./test/testSmall.pdf";
        String nestedDestination = "./test/testNested.pdf";
        Map<String, Integer> emptyMap = new HashMap<>();
        Map<String, String> faultMap = new HashMap<>();
        Map<String, Integer> smallMap = new HashMap<>();
        Map<String, Map<String, Integer>> nestedMap = new HashMap<>();
        faultMap.put("Fault","Fault");
        smallMap.put("Small 1", 1);
        smallMap.put("Small 2", 2);
        smallMap.put("Small 3", 3);
        nestedMap.put("Nested 1", smallMap);
        nestedMap.put("Nested 2", smallMap);
        nestedMap.put("Nested 3", smallMap);

        Printer printer = new Printer();
        printer.createPdfFromTemplate(emptyDestination, "TEST EMPTY", emptyMap);
        printer.createPdfFromTemplate(faultDestination, "TEST FAULT", faultMap);
        printer.createPdfFromTemplate(smallDestination, "TEST SMALL", smallMap);
        printer.createPdfFromTemplate(nestedDestination, "TEST NEST", nestedMap);

        File emptyFile = new File(emptyDestination);
        File faultFile = new File(faultDestination);
        File smallFile = new File(smallDestination);
        File nestedFile = new File(nestedDestination);

        assertNotNull(emptyFile);
        assertNotNull(faultFile);
        assertNotNull(smallFile);
        assertNotNull(nestedFile);
        assertTrue(emptyFile.isFile());
        assertTrue(faultFile.isFile());
        assertTrue(smallFile.isFile());
        assertTrue(nestedFile.isFile());
        assertTrue(emptyFile.getName().endsWith(".pdf"));
        assertTrue(faultFile.getName().endsWith(".pdf"));
        assertTrue(smallFile.getName().endsWith(".pdf"));
        assertTrue(nestedFile.getName().endsWith(".pdf"));
        assertTrue(containsText(emptyDestination,"TEST EMPTY"));
        assertTrue(containsText(faultDestination,"TEST FAULT"));
        assertTrue(containsText(smallDestination,"TEST SMALL"));
        assertTrue(containsText(nestedDestination,"TEST NEST"));
    }

    private boolean containsText(String fileName, String text) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(fileName));
        Rectangle rect = new Rectangle(36, 700, 523, 100);

        TextRegionEventFilter fontFilter = new TextRegionEventFilter(rect);
        FilteredEventListener listener = new FilteredEventListener();
        LocationTextExtractionStrategy extractionStrategy = listener.attachEventListener(new LocationTextExtractionStrategy(), fontFilter);
        new PdfCanvasProcessor(listener).processPageContent(pdfDoc.getFirstPage());

        String actualText = extractionStrategy.getResultantText();

        pdfDoc.close();

        return actualText.contains(text);
    }

}