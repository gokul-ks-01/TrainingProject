package Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static String getProperty(String propertyName, String defaultValue) throws IOException {
        String filePath = System.getProperty("user.dir")+"/Properties.properties";
        FileReader fileReader = new FileReader(filePath);
        Properties properties = new Properties();
        properties.load(fileReader);
        return properties.getProperty(propertyName,defaultValue);
    }
}
