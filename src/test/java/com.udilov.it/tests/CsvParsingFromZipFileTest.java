package com.udilov.it.tests;

import com.opencsv.CSVReader;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class CsvParsingFromZipFileTest {
    private final ClassLoader cl = CsvParsingFromZipFileTest.class.getClassLoader();
    private final String zipName = "filesForTesting.zip";
    private InputStream getFileStreamFromZip (String zipName, String fileName) throws IOException
    {
        URL file = cl.getResource(zipName);
        ZipFile zipFile = new ZipFile(file.getPath());
        return zipFile.getInputStream(zipFile.getEntry(fileName));
    }

    @Test
    @DisplayName("Проверка CSV-файла")
    void csvParsingTest() throws Exception {
        try (InputStream is = getFileStreamFromZip(zipName,"petNames.csv");
             InputStreamReader isr = new InputStreamReader(is);
             CSVReader csvReader = new CSVReader(isr))
        {
            List<String[]> content = csvReader.readAll();
            Assertions.assertArrayEquals(
                    new String[] {"dog", "Sharik"}, content.get(0)
            );
        }
    }
}
