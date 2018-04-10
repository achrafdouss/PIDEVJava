/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.bonplan.entities.Voyage;
import com.bonplan.entities.Reservation;
import com.bonplan.entities.User;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.itextpdf.text.pdf.XfaXpathConstructor.XdpPackage.Pdf;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author achref
 */
public class pdf {

    public static final String dest = "pdf/";
    public static final String IMAGE = "pdf/background_image.jpg";
    public static final Font BLUE_BOLD = new Font(FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLUE);
    public static final Chunk titre = new Chunk("Reservation", BLUE_BOLD);
    public static final Chunk aa = new Chunk("Achat", BLUE_BOLD);

    public static void getPdf(Reservation r, User u) {

        try {

            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest + r.getId_resv() + ".pdf"));
            document.open();
            Paragraph p1 = new Paragraph(titre);
            p1.setAlignment(Element.ALIGN_CENTER);
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image ima;
            try {
                ima = Image.getInstance("pdf/logo.png");
                ima.scalePercent(30);
                document.add(ima);
            } catch (BadElementException ex) {
                Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
            }

            document.add(p1);
            try {
                Image image = Image.getInstance(IMAGE);
                image.scaleAbsolute(PageSize.A4);
                image.setAbsolutePosition(0, 0);
                canvas.addImage(image);
            } catch (BadElementException ex) {
                Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
            }
            Paragraph p2 = new Paragraph("Achat N " + r.getId_voy());
            Paragraph p3 = new Paragraph("Pour Mr/Madame : " + u.getNom() + " " + u.getPrenom());
            Paragraph p4 = new Paragraph("Passer Le : " + r.getId_voy());
            p4.setAlignment(Element.ALIGN_RIGHT);
            Paragraph p5 = new Paragraph("Etat de L'achat : " + r.getId_voy());
            Paragraph p6 = new Paragraph("Adresse du Client : " + u.getAddresse());
            Paragraph p7 = new Paragraph("Numero Telephone : " + u.getTelephone());
            PdfPTable table = new PdfPTable(3);
            PdfPCell cell = new PdfPCell(new Paragraph(aa));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            table.addCell("Libelle");
            table.addCell("Quantite");
            table.addCell("Prix Unitaire");
            

            
            Paragraph p8 = new Paragraph(" ");
            Paragraph p9 = new Paragraph(" ");

            PdfPTable table2 = new PdfPTable(2);
            table2.addCell("Livrer le : ");
            table2.addCell("Signature Du Client");
            table2.addCell("           ");
            table2.addCell("           ");
           
          
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(pdf.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

