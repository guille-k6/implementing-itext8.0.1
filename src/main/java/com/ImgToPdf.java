package com;

import java.io.File;
import java.io.IOException;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Image;

public class ImgToPdf {
	
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\guill\\Downloads\\pdf\\documentosmergeados.pdf");
        file.getParentFile().mkdirs();
        try {
        ImgToPdf.convertA4("C:\\Users\\guill\\Downloads\\pdf\\Idear_logo.png",
        			       "C:\\Users\\guill\\Downloads\\pdf\\imageToPdf.pdf");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
	
	public static void convert(String imgsrc, String imgdest) throws IOException{
		
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(imgdest));
        
        ImageData data = ImageDataFactory.create(imgsrc);
        Image img = new Image(data);
        img.setBorder(Border.NO_BORDER);
        
        float imgWidth = img.getImageWidth();
        float imgHeight = img.getImageHeight();
        img.setFixedPosition(0, 0);  
        
        // Creo un pdf del tamaño de la imagen... Se puede modificar
        Rectangle rect = new Rectangle(imgWidth, imgHeight);
        PageSize pagesize = new PageSize(rect);
        pdfDoc.setDefaultPageSize(pagesize);
        // Creating a Document Document
        Document document = new Document(pdfDoc, pagesize);
        document.setMargins(0, 0, 0, 0);
        document.add(img);
        document.close();
	}
	
	public static void convertA4(String imgsrc, String imgdest) throws IOException{
		
		/*
		 * This method rescales the image to a maximum size of A4.
		 */
		
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(imgdest));
        
        ImageData data = ImageDataFactory.create(imgsrc);
        Image img = new Image(data);
        img.setBorder(Border.NO_BORDER);
        
        float w = img.getImageWidth();
        float h = img.getImageHeight();
        
        float wratio = w / PageSize.A4.getWidth();
        float hratio = h / PageSize.A4.getHeight();
        
        float higherratio = 0;
        
        if(wratio > 1 || hratio > 1) {
        	higherratio = Math.max(wratio, hratio);	
        	img.setWidth(w/higherratio);
        	img.setHeight(h/higherratio);
        }
        // Creating a Document Document
        Document document = new Document(pdfDoc, PageSize.A4);
        document.setMargins(0, 0, 0, 0);
        document.add(img);
        document.close();
	}

}
