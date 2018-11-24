package com.dozor.client.properties;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author IGOR-K
 */
public class NickProperties {
    private static final NickProperties property = new NickProperties();

    public static NickProperties getInstance() {
        return property;
    }

    public static final String HOST = "PROP_HOST";
    public static final String PORT = "PROP_PORT";
    public static final String NICK = "PROP_NICK";

    private final String local_dir = System.getProperty("user.dir");
    private final String file_separator = System.getProperty("file.separator");
    private final String file_path = local_dir + file_separator + "nick.properties";

    Properties properties = new Properties();

    private NickProperties() {
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(NickProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() throws IOException {

        File file = new File(file_path);
        if (!(file).exists()) {
            System.err.println("No Config File!!!");
            System.err.println(file_path);
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        properties.load(br);
        br.close();
    }

    public String getProperty(String propertyName) {
        return (String) properties.get(propertyName);
    }

    public void putProperty(String propertyName, String property) {
        properties.setProperty(propertyName, property);
    }

    public Properties getProperties() {
        return properties;
    }

    public void saveProperties() throws IOException {
        try (FileOutputStream output = new FileOutputStream(file_path)) {
            properties.store(output, null);
        }
    }
}
