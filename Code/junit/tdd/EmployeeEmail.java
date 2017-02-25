package be.leerstad.junit.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by peterhardeel on 14/11/2016.
 */
public class EmployeeEmail {


    private Map<String, String> emails = new HashMap<String, String>();

    public void addEmployeeEmailId(String key, String value) {
        if (isValidEmailId(value)) {
            emails.put(key, value);
        }
    }

    public String getEmployeeEmailId(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("Object not type of String");
        }
        return emails.get(key);
    }

    public boolean isValidEmailId(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

    public Map<String, String> getEmails() {
        return emails;
    }
}
