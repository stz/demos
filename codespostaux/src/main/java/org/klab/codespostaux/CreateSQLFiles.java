package org.klab.codespostaux;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Crée les fichiers postalcodes.sql et cities.sql à partir du fichier codes_postaux_liste.txt.
 */
public class CreateSQLFiles {
    
    public static void main(String[] args) throws Exception {
        
        Set<String> cps = new TreeSet<String>();
        
        // Read File Line By Line
        BufferedReader in = new BufferedReader(new FileReader("src/main/resources/codes_postaux_liste.txt"));
        String line;
        while ((line = in.readLine()) != null) {
            String cp = line.substring(0, 5);
            try {
                Integer.parseInt(cp);
                cps.add(cp);
            } catch (NumberFormatException e) {
                //System.out.println("ERROR: " + cp);
            }
        }
        

        BufferedWriter out1 = new BufferedWriter(new FileWriter("src/main/resources/postalcodes.sql"));
        
        int i = 1;
        Map<String, String> cp2id = new HashMap<String, String>();
        for (String cp : cps) {
            cp2id.put(cp, i+"");
            out1.write("INSERT INTO PostalCode (id, code) VALUES ('"+i+"', '"+cp+"');\n");
            i++;
        }
        out1.close();


        BufferedWriter out2 = new BufferedWriter(new FileWriter("src/main/resources/cities.sql"));
        
        // Read File Line By Line
        in = new BufferedReader(new FileReader("dumps/codes_postaux_liste.txt"));
        while ((line = in.readLine()) != null) {
            String cp = line.substring(0, 5);
            try {
                Integer.parseInt(cp);
                String city = line.substring(6);
                out2.write("INSERT INTO City (name, postalCode_id) VALUES ('"+city+"', '"+cp2id.get(cp)+"');\n");
            } catch (NumberFormatException e) {
                //System.out.println("ERROR: " + cp);
            }
        }
        out2.close();
    }
}
