package com.nikhil_suwalka.encryptiondecryptionusingaes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button decrypt_b, encrypt_b;
    EditText editText;
    TextView textView, secret_ky_tv;
    SecretKey secretKey;
    String encrypted, strSecretKey;
    SecretKey originalSecretKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decrypt_b = findViewById(R.id.decrypt_button);
        encrypt_b = findViewById(R.id.encrypt_button);
        editText = findViewById(R.id.password_et);
        textView = findViewById(R.id.textview);
        secret_ky_tv = findViewById(R.id.secret_key_tv);

        try {
            KeyGenerator keyGenerator;
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
            strSecretKey = Encryption_and_Decryption_Functions.encoderfun(secretKey.getEncoded());
            originalSecretKey = Encryption_and_Decryption_Functions.getOriginalSecretKeyFromString(strSecretKey);

            encrypt_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        encrypted = Encryption_and_Decryption_Functions.encoderfun(Encryption_and_Decryption_Functions.encrypt(editText.getText().toString().trim().getBytes(), originalSecretKey));

                        Log.i(TAG, "onCreate: Secret Key: " + strSecretKey);
                        secret_ky_tv.setText(strSecretKey);
                        Log.i(TAG, "onCreate: Encrypted: " + encrypted);
                        textView.setText(encrypted);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            decrypt_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        SecretKey originalSecretKey = Encryption_and_Decryption_Functions.getOriginalSecretKeyFromString(strSecretKey);

                        String decrypted = Encryption_and_Decryption_Functions.decrypt(Encryption_and_Decryption_Functions.decoderfun(encrypted), originalSecretKey);
                        Log.i(TAG, "onCreate: Decrypted: " + secretKey);
                        textView.setText(decrypted);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
