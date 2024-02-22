package com.udilov.it.tests;

import com.codeborne.xlstest.XLS;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class XlsxParsingFromZipFileTest {
    
    private final ClassLoader cl = XlsxParsingFromZipFileTest.class.getClassLoader();
    private final String zipName = "filesForTesting.zip";

    private InputStream getFileStreamFromZip (String zipName, String fileName) throws IOException
    {
        URL file = cl.getResource(zipName);
        ZipFile zipFile = new ZipFile(file.getPath());
        return zipFile.getInputStream(zipFile.getEntry(fileName));
    }

    @Test
    @DisplayName("Проверка XLS-файла")
    void xlsParsingTest() throws Exception {
        try (InputStream is = getFileStreamFromZip(zipName,"forTesting.xls"))
        {
            XLS xls = new XLS(is);
            Assertions.assertEquals(
                    "Snail Repairing Toner",
                    xls.excel.getSheet("sheet_1")
                            .getRow(6)
                            .getCell(1)
                            .getStringCellValue()
            );
        }
    }
}
