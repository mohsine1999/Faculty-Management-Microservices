package com.example.securityoauth2.Configuration;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GenerateKeyPairs {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, FileNotFoundException {
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        var  keyPair =keyPairGenerator.generateKeyPair();
        byte[] publickey= keyPair.getPublic().getEncoded();
        byte[] privatekey = keyPair.getPrivate().getEncoded();
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream("public.pem")));
        PemObject pemObject=new PemObject("PUBLIC KEY", publickey);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        PemWriter pemWriter2 = new PemWriter(new OutputStreamWriter(new FileOutputStream("private.pem")));
        PemObject pemObject2=new PemObject("PRIVATE KEY", privatekey);
        pemWriter2.writeObject(pemObject2);
        pemWriter2.close();

    }
}
