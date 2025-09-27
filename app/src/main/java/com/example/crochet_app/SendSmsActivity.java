package com.example.crochet_app;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SendSmsActivity extends AppCompatActivity {

    private EditText phoneNumberInput, messageInput;
    private Button btnSendSms, btnPlayMusic, btnStopMusic;
    private Button btnShowFragment;

    private static final int SMS_PERMISSION_CODE = 100;
    private static final String CHANNEL_ID = "SMS_CHANNEL_ID"; // Notification Channel ID
    private static final String SHARED_PREFS_NAME = "SmsPrefs";
    private static final String KEY_PHONE_NUMBER = "phoneNumber";
    private static final String KEY_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        // Initialize views
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        messageInput = findViewById(R.id.messageInput);
        btnSendSms = findViewById(R.id.btnSendSms);

        // Load saved phone number and message from Shared Preferences
        loadSavedData();

        // Check SMS permission
        checkSmsPermission();

        // Handle the send SMS button click
        btnSendSms.setOnClickListener(v -> sendSms());

        // Create Notification Channel
        createNotificationChannel();
    }

    private void checkSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS permission granted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendSms() {
        String phoneNumber = phoneNumberInput.getText().toString().trim();
        String message = messageInput.getText().toString().trim();

        if (phoneNumber.isEmpty() || message.isEmpty()) {
            Toast.makeText(SendSmsActivity.this, "Please enter both phone number and message.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(SendSmsActivity.this, "SMS Sent!", Toast.LENGTH_SHORT).show();
            showNotification(phoneNumber); // Show notification after sending SMS

            // Save phone number and message to Shared Preferences
            saveData(phoneNumber, message);
        } catch (Exception e) {
            Toast.makeText(SendSmsActivity.this, "SMS Failed to Send!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Method to show notification after SMS is sent
    private void showNotification(String phoneNumber) {
        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.icn_noti) // Add a suitable icon
                .setContentTitle("SMS Sent")
                .setContentText("Message has been sent to " + phoneNumber)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true); // Dismiss notification on click

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(1, builder.build()); // The first argument is the notification ID
        }
    }

    // Create the Notification Channel (required for Android 8.0+)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "SMS Notifications";
            String description = "Channel for SMS notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    // Save phone number and message to Shared Preferences
    private void saveData(String phoneNumber, String message) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);
        editor.putString(KEY_MESSAGE, message);
        editor.apply();
    }

    // Load saved phone number and message from Shared Preferences
    private void loadSavedData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        String savedPhoneNumber = sharedPreferences.getString(KEY_PHONE_NUMBER, "");
        String savedMessage = sharedPreferences.getString(KEY_MESSAGE, "");

        // Set saved values to the EditText fields
        phoneNumberInput.setText(savedPhoneNumber);
        messageInput.setText(savedMessage);
    }
}
