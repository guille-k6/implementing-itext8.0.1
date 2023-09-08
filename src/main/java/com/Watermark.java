package com;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;

import java.io.File;

public class Watermark {
	
    public static final String DEST = "C:\\Users\\guill\\Downloads\\pdf\\ITEXT7.pdf";
    public static final String IMG = "C:\\Users\\guill\\Downloads\\pdf\\Idear_logo.png";
    public static final String SRC = "C:\\Users\\guill\\Downloads\\pdf\\Desarrollo ISSLP.pdf";

    public static void main(String[] args) throws Exception {
    	try {
            File file = new File(DEST);
            file.getParentFile().mkdirs();

            new Watermark().manipulatePdf("C:\\Users\\guill\\Downloads\\pdf\\Desarrollo ISSLP.pdf",
            							  "C:\\Users\\guill\\Downloads\\pdf\\ITEXT7.pdf",
            							  "C:\\Users\\guill\\Downloads\\pdf\\Idear_logo.png");    		
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}

    }


    protected void manipulatePdf(String src, String dest, String watermarksrc) throws Exception {
    	/*
    	 * src = ruta del pdf al que se le va a aplicar la marca de agua
    	 * dest = ruta en la cual se guarda el nuevo pdf estampado
    	 * watermarksrc = ruta de la marca de agua
    	 * */
    	
    	float OPACITY = 0.5f;
    	float SCALE_FACTOR = 0.4f;
    	
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        
        ImageData img = ImageDataFactory.create(watermarksrc);

        float w = img.getWidth() * SCALE_FACTOR;
        float h = img.getHeight() * SCALE_FACTOR;

        PdfExtGState gs1 = new PdfExtGState().setFillOpacity(OPACITY);

        for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {

            PdfPage pdfPage = pdfDoc.getPage(i);
            Rectangle pageSize = pdfPage.getPageSizeWithRotation();

            pdfPage.setIgnorePageRotationForContent(true);

            float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
            float y = (pageSize.getTop() + pageSize.getBottom()) / 2;
            PdfCanvas over = new PdfCanvas(pdfDoc.getPage(i));
            over.saveState();
            over.setExtGState(gs1);
            over.addImageWithTransformationMatrix(img, w, 0, 0, h, x - (w / 2), y - (h / 2), false);
            over.restoreState();
        }

        doc.close();
    }
}
