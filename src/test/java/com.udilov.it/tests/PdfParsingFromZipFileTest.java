package com.udilov.it.tests;

import com.codeborne.pdftest.PDF;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PdfParsingFromZipFileTest {
    private final ClassLoader cl = PdfParsingFromZipFileTest.class.getClassLoader();
    private final String zipName = "filesForTesting.zip";
    private InputStream getFileStreamFromZip (String zipName, String fileName) throws IOException
    {
        URL file = cl.getResource(zipName);
        ZipFile zipFile = new ZipFile(file.getPath());
        return zipFile.getInputStream(zipFile.getEntry(fileName));
    }

    @Test
    @DisplayName("Проверка PDF-файла")
    void pdfParsingTest() throws Exception {
        try (InputStream is = getFileStreamFromZip(zipName,"rule_book.pdf"))
        {
            PDF pdf = new PDF(is);
            Assertions.assertEquals("Adobe InDesign 19.0 (Macintosh)", pdf.creator);
        }
    }
}
