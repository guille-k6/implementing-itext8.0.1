package com;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;



public class PageNumbering {
	
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\guill\\Downloads\\pdf\\documentosmergeados.pdf");
        file.getParentFile().mkdirs();
        try {
        	PageNumbering.manipulatePdfAddPages("C:\\Users\\guill\\Downloads\\pdf\\pdfWatermarked.pdf",
        			     				"C:\\Users\\guill\\Downloads\\pdf\\pdfWatermarkedNumerado.pdf", 6);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
	
    public static void manipulatePdf(String src, String dest) throws IOException {
    	/*
    	 * src = fuente; dest = destino; 	
    	 */
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        int numberOfPages = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {

            // Write aligned text to the specified by parameters point
            doc.showTextAligned(new Paragraph(String.format("pág %s de %s", i, numberOfPages)),
                    559, 806, i, TextAlignment.RIGHT, VerticalAlignment.TOP, 0);
        }

        doc.close();
        
    }
    
    public static void manipulatePdf(String src, String dest, int offset) throws IOException, Exception {
    	/*
    	 * src = fuente; dest = destino; offset = pagina a partir de la cual arranco a numerar. 	
    	 */
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        int numberOfPages = pdfDoc.getNumberOfPages();
        
        if(offset < 1) {
        	doc.close();
        	throw new Exception("Numero de página no valido. Empezar a partir del 1");
        }
        if(offset > numberOfPages) {
        	doc.close();
        	throw new Exception("Offset mayor al numero total de páginas");
        }
        
        
        for (int i = offset; i <= numberOfPages; i++) {

            // Write aligned text to the specified by parameters point
            doc.showTextAligned(new Paragraph(String.format("pág %s de %s", i, numberOfPages)),
                    559, 806, i, TextAlignment.RIGHT, VerticalAlignment.TOP, 0);
        }

        doc.close();   
    }
    
    public static void manipulatePdfAddPages(String src, String dest, int fromPage) throws IOException {
    	/*
    	 * src = fuente; dest = destino;
    	 * fromPage = numero de pagina anterior de la que empiezo a contar, ej: 5 (el doc tiene 3 paginas)
    	 * quedaría: pag 6/8, pag 7/8, pag 8/8;  
    	 */
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        int numberOfPages = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {

            // Write aligned text to the specified by parameters point
            doc.showTextAligned(new Paragraph(String.format("pág %s de %s", i + fromPage, numberOfPages + fromPage)),
                    559, 806, i, TextAlignment.RIGHT, VerticalAlignment.TOP, 0);
        }

        doc.close();
        
    }
}
