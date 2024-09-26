package com.eggplanters.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.*;
public class AppStoreReader {
    public AppStoreReader(File jsonFile) throws NotJSONException {
        if (jsonFile.getName().endsWith(".json")) {
            file = jsonFile;
        } else {
            throw new NotJSONException();
        }
    }

    private final File file;

    public AppEntry[] parseJSON() {
        try {
            Reader reader = new FileReader(file.getAbsolutePath());

            Gson gson = new Gson();
            return gson.fromJson(reader,AppEntry[].class);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return new AppEntry[0];
    }
}
