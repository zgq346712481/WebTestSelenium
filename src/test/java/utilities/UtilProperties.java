package utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilProperties {
    public static String getProperties(String name) throws IOException {

        Properties properties=new Properties();
        properties.load(new FileInputStream("E:\\IntelliJ_IDEA_workspace\\WebTestSelenium\\src\\test\\java\\config\\config.properties") );
        String zhi=properties.getProperty(name);
        return zhi;

    }
}