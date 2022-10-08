package unibs.baratto.authentication;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class CredentialsFileReader extends BufferedReader {
    public CredentialsFileReader(Reader in) {
        super(in);
    }

    public Map<String , String> getCredentials() {
        Map<String, String> map = new HashMap<>();
        for (String line : lines().toList()) {
            if (line.length() > 3 && !line.startsWith("//")) {
                var cred = line.split(" ");
                map.put(cred[0], cred[1]);
            }
        }
        return map;
    }

}
