package net.bc100dev.firebos.auth;

import net.bc100dev.commons.utils.io.FileEncryption;
import net.bc100dev.commons.utils.io.FileUtil;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public record UserData(String email, String password) {

    public String getHashedPassword(String salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 100000, 512);
            SecretKeyFactory fact = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = fact.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ignore) {
            try {
                String data = password + salt;

                File file = File.createTempFile("pass", ".dat");
                FileUtil.write(file.getAbsolutePath(), data);

                byte[] enc = FileEncryption.toEncrypted(File.createTempFile("pass", ".dat"), data);
                FileUtil.delete(file.getAbsolutePath());

                return Base64.getEncoder().encodeToString(enc);
            } catch (IOException | GeneralSecurityException ignore0) {
                return Base64.getEncoder().encodeToString((password + salt).getBytes());
            }
        }
    }

}
