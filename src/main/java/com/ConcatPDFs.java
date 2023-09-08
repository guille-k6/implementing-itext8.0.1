package com;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConcatPDFs {
	
    public static final String DEST = "C:\\Users\\guill\\Downloads\\pdf\\documentosmergeados.pdf";

    public static final String SRC1 = "C:\\Users\\guill\\Downloads\\pdf\\vacaciones.pdf";
    public static final String SRC2 = "C:\\Users\\guill\\Downloads\\pdf\\ConcatArchivoFoto.pdf";
    public static final String SRC3 = "C:\\Users\\guill\\Downloads\\pdf\\ITEXT7.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new ConcatPDFs().manipulatePdf(Arrays.asList(SRC1, SRC2, SRC3), DEST);
    }

    protected void manipulatePdf(List<String> sourcesList, String dest) throws IOException {
    	
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        
        List<PdfDocument> pdfDocumentList = new LinkedList<>();
        
        for(String sources : sourcesList) {
        	pdfDocumentList.add(new PdfDocument(new PdfReader(sources)));
        }

        PdfMerger merger = new PdfMerger(pdfDoc);
        
        for(PdfDocument pdfDocument : pdfDocumentList) {
        	merger.setCloseSourceDocuments(true).merge(pdfDocument, 1, pdfDocument.getNumberOfPages());
        }
        
        pdfDoc.close();
    }
}