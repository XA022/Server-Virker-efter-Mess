package model.calendar;

import java.security.MessageDigest;

/**
 * Created by jesperbruun on 23/09/14.
 */

public class EncryptUserID {

    /**
     * Constant cipher seed - DO NOT CHANGE.
     * http://www.miraclesalad.com/webtools/md5.php - Du kan her saette userid foerst og derefter hashkey for at teste
     */
    private static final String HASHKEY = "v.eRyzeKretW0r_t";
    private static String userId;
    private String key;
    private static MessageDigest digester;
    
    public EncryptUserID(String userID) {
        if (userID == null || userID.isEmpty() ) {
            throw new IllegalArgumentException("Error");
        }
    	EncryptUserID.userId = userID;
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.key = crypt(userId,HASHKEY);
    }

    public static String crypt(String userId) {
    	return crypt(userId,HASHKEY);
    }
    // Enkryptere en tekst streng som bliver parset til funktionen
    public static String crypt(String str, String hashKey) {
    	String inputString = str + hashKey;
        if (inputString == null || inputString.length() == 0) {
            throw new IllegalArgumentException("Error");
        }

        digester.update(inputString.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }

//    //Quick example of how to get the hash.
//    public static void main(String[] args) {
//
//        System.out.print("Secret key: " + crypt(userId, HASHKEY));
//        String key = crypt(userId, HASHKEY);
//
//    }
    

	public String getKey() {
		return key;
	}
    
}

