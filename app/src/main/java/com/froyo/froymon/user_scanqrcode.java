package com.froyo.froymon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.Manifest;


import java.io.IOException;

import eu.livotov.labs.android.camview.ScannerLiveView;
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.VIBRATE;

public class user_scanqrcode extends AppCompatActivity {
    private ScannerLiveView camera;
    private Button homepage;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_scanqrcode);

        camera =  findViewById(R.id.camview);
        homepage = findViewById(R.id.btnhome);

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_scanqrcode.this,user_homepage.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkCameraPermission();
        } else {
            camera.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
                @Override
                public void onScannerStarted(ScannerLiveView scanner) {
                    Toast.makeText(user_scanqrcode.this, "Scanner Started", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onScannerStopped(ScannerLiveView scanner) {
                    Toast.makeText(user_scanqrcode.this, "Scanner Stopped", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onScannerError(Throwable err) {
                    Toast.makeText(user_scanqrcode.this, "Scanner Error: " + err.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCodeScanned(String data) {
                    String[] separatedData = data.split(" ");
                    if (separatedData.length >= 2) {
                        String num = separatedData[0];
                        String lab = separatedData[1];
                        Intent intent = new Intent(user_scanqrcode.this, user_qrcode_joinlobbyform.class);
                        intent.putExtra("computerNumber", num);
                        intent.putExtra("lab", lab);
                        startActivity(intent);
                    } else {
                        Toast.makeText(user_scanqrcode.this, "Invalid QR Code Format", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission();
        } else {
            camera.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
                @Override
                public void onScannerStarted(ScannerLiveView scanner) {
                    Toast.makeText(user_scanqrcode.this, "Scanner Started", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onScannerStopped(ScannerLiveView scanner) {
                    Toast.makeText(user_scanqrcode.this, "Scanner Stopped", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onScannerError(Throwable err) {
                    Toast.makeText(user_scanqrcode.this, "Scanner Error: " + err.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCodeScanned(String data) {
                    String[] separatedData = data.split(" ");
                    if (separatedData.length >= 2) {
                        String num = separatedData[0];
                        final String lab = separatedData[1];

                        // Reference to the "student" node in your Firebase Realtime Database
                        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("student");

                        // Check if the lab exists in the database
                        studentRef.child(lab).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // Lab exists, proceed to the user_qrcode_joinlobbyform
                                    Intent intent = new Intent(user_scanqrcode.this, user_qrcode_joinlobbyform.class);
                                    intent.putExtra("computerNumber", num);
                                    intent.putExtra("lab", lab);
                                    startActivity(intent);
                                } else {
                                    // Lab does not exist, display a toast
                                    Toast.makeText(user_scanqrcode.this, "Lab does not exist", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                // Handle any errors that may occur during the database operation
                                Toast.makeText(user_scanqrcode.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(user_scanqrcode.this, "Invalid QR Code Format", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                CAMERA_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                camera.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
                    @Override
                    public void onScannerStarted(ScannerLiveView scanner) {
                        Toast.makeText(user_scanqrcode.this, "Scanner Started", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onScannerStopped(ScannerLiveView scanner) {
                        Toast.makeText(user_scanqrcode.this, "Scanner Stopped", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onScannerError(Throwable err) {
                        Toast.makeText(user_scanqrcode.this, "Scanner Error: " + err.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeScanned(String data) {
                        String[] separatedData = data.split(" ");
                        if (separatedData.length >= 2) {
                            String num = separatedData[0];
                            final String lab = separatedData[1];

                            // Reference to the "student" node in your Firebase Realtime Database
                            DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("student");

                            // Check if the lab exists in the database
                            studentRef.child(lab).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        // Lab exists, proceed to the user_qrcode_joinlobbyform
                                        Intent intent = new Intent(user_scanqrcode.this, user_qrcode_joinlobbyform.class);
                                        intent.putExtra("computerNumber", num);
                                        intent.putExtra("lab", lab);
                                        startActivity(intent);
                                    } else {
                                        // Lab does not exist, display a toast
                                        Toast.makeText(user_scanqrcode.this, "Lab does not exist", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    // Handle any errors that may occur during the database operation
                                    Toast.makeText(user_scanqrcode.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(user_scanqrcode.this, "Invalid QR Code Format", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZXDecoder decoder = new ZXDecoder();
        decoder.setScanAreaPercent(1);
        camera.setDecoder(decoder);
        camera.startScanner();
    }

    @Override
    protected void onPause() {
        camera.stopScanner();
        super.onPause();
    }

}


