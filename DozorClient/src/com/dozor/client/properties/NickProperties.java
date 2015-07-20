package com.dozor.client.properties;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
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

    private final String local_dir = System.getProperty("user.dir");
    private final String file_separator = System.getProperty("file.separator");
    private final String file_path = local_dir + file_separator + "server.properties";


    private Map<String, String> properties;

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

        properties = new HashMap();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String eachLine = br.readLine();

        while (eachLine != null) {
            //System.out.println(eachLine);
            String[] arr = eachLine.split("=");
            if (arr.length == 2)
                properties.put(arr[0].trim(), arr[1].trim());
            eachLine = br.readLine();
        }

    }

    public String getProperty(String propertyName) {
        return properties.get(propertyName);
    }
}
