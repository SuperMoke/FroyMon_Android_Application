package com.froyo.froymon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;


public class admin_qrcode_generator extends AppCompatActivity {

    private ImageView qrCodeIV;
    private EditText computernumber;
    private Spinner laboratory;
    private Button generateQR ,saveQR;
    private ImageButton backbutton;
    Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    private Map<String, String> labIdMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_qrcode_generator);
        qrCodeIV = findViewById(R.id.idIVQrcode);
        computernumber =  findViewById(R.id.computernumbertf);
        laboratory = findViewById(R.id.spinner);
        generateQR = findViewById(R.id.btngenerate);
        backbutton = findViewById(R.id.btnbackbutton);
        saveQR = findViewById(R.id.btnsaveqr);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/QRCode/";
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.lablist,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        laboratory.setAdapter(adapter);

        labIdMap = new HashMap<>();
        labIdMap.put("Computer Laboratory 1", "CLAB1");
        labIdMap.put("Computer Laboratory 2", "CLAB2");
        labIdMap.put("Computer Laboratory 3", "CLAB3");
        labIdMap.put("Computer Laboratory 4", "CLAB4");
        labIdMap.put("Computer Laboratory 5", "CLAB5");
        labIdMap.put("Computer Laboratory 6", "CLAB6");
        labIdMap.put("Cisco Laboratory", "CiscoLab");
        labIdMap.put("Accounting Laboratory" , "AccountingLab");
        labIdMap.put("Hardware Laboratory" , "HardwareLab");
        labIdMap.put("Contact Center Laboratory" , "ContactCenterLab");


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_qrcode_generator.this,admin_homepage.class);
                startActivity(intent);
                finish();
            }
        });


        generateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(computernumber.getText().toString())){
                    Toast.makeText(admin_qrcode_generator.this, "Enter the Computer Number", Toast.LENGTH_SHORT).show();
                } else{
                    String lab = laboratory.getSelectedItem().toString();
                    String labidValue = labIDsetter(lab);
                    String num = computernumber.getText().toString();
                    String combinedData =  num+ " " + labidValue;

                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);

                    int width = point.x;
                    int height = point.y;

                    int dimen = width < height ? width : height;
                    dimen = dimen * 3 / 4;

                    qrgEncoder = new QRGEncoder(combinedData, null, QRGContents.Type.TEXT, dimen);
                    bitmap = qrgEncoder.getBitmap(0);
                    qrCodeIV.setImageBitmap(bitmap);

                }
            }
        });

        saveQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lab = laboratory.getSelectedItem().toString();
                String labidValue = labIDsetter(lab);
                String num = computernumber.getText().toString();

                String filename = labidValue +" Computer Number " + num;
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        boolean save = new QRGSaver().save(savePath, filename, bitmap, QRGContents.ImageType.IMAGE_JPEG);
                        String result = save ? "Image Saved" : "Image Not Saved";
                        Toast.makeText(admin_qrcode_generator.this, result, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(admin_qrcode_generator.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ActivityCompat.requestPermissions(admin_qrcode_generator.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }
            }
        });


    }

    private String labIDsetter(String labnameValue) {
        String labid = labIdMap.get(labnameValue);
        return labid;
    }
}