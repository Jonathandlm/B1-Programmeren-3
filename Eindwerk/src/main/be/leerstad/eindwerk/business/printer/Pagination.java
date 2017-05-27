package be.leerstad.eindwerk.business.printer;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.property.TextAlignment;

public class Pagination implements IEventHandler {
    protected PdfFormXObject background;

    public Pagination(PdfFormXObject pdfFormXObject) {
        this.background = pdfFormXObject;
    }

    @Override
    public void handleEvent(Event event) {

        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc).addXObject(background, 0, 0);

        Rectangle pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(page.getLastContentStream(), page.getResources(), pdfDoc);
        Canvas canvas = new Canvas(pdfCanvas, pdfDoc, pageSize);
        float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
        float y = pageSize.getBottom() + 15;
        canvas.showTextAligned(String.valueOf(pdfDoc.getPageNumber(page)), x, y, TextAlignment.CENTER);

    }
}
