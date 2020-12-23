package gap.com.smsapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity.NAm";
    List<Sms> smsArrayList = new ArrayList<Sms>();
    private int REQUEST_CODE_ASK_PERMISSIONS = 333;
    private Sms sms;
    private RecyclerView recyclerView;
    private String deposit, harvest = null;
    private List<Result> resultList = new ArrayList<>();
    private static final String CURRENCY_SYMBOLS = "\\p{Sc}\u0024\u060B";
    private String bankName;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] addressList = getResources().getStringArray(R.array.address_number);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasReadSmsPermission = checkSelfPermission(Manifest.permission.READ_SMS);
            if (hasReadSmsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_SMS}, REQUEST_CODE_ASK_PERMISSIONS);
            } else {
              /*  smsArrayList = getAllSms();
                for (int i = 0; i < smsArrayList.size(); i++) {
                    sms = smsArrayList.get(i);
                    for (String address : addressList) {
                        if (sms.getAddress().equals(address)) {
                            String message = sms.getMsg();

                            String bankName = "";

                            switch (sms.getAddress()) {
                                case "+9820000222":
                                    //qavamin
                                    bankName = Constants.qavamin;
                                    resultList = Converter.qavamin(message, bankName, sms.getTime());
                                    break;
                                case "+981000700":
                                    //tosee tavon
                                    bankName = Constants.tosee;
                                    break;
                                case "+981000900":
                                    //pasargad
                                    bankName = Constants.pasargad;
                                    break;
                                case "+9820000":
                                    //saman
                                    bankName = Constants.saman;
                                    break;
                                case "+98300017":
                                    //meli
                                    bankName = Constants.meli;
                                    break;
                                case "+98300014":
                                    //maskan
                                    bankName = Constants.maskan;
                                    break;
                                case "+98100088":
                                    //sarmaye
                                    bankName = Constants.sarmaye;
                                    break;
                                case "+98300066":
                                    //refah
                                    bankName = Constants.refah;
                                    break;
                                case "+98300044":
                                    //refah
                                    bankName = Constants.refah;
                                    break;
                                case "+98300054":
                                    //parsiyan
                                    bankName = Constants.parsiyan;
                                    break;
                                case "+982000911":
                                    //keshavarzi
                                    bankName = Constants.keshavarzi;
                                    break;
                                case "+98200070":
                                    //tejarat
                                    bankName = Constants.tejarat;
                                    break;
                                case "+98200050":
                                    //eq novin
                                    bankName = Constants.novin;
                                    break;
                                case "+98200038":
                                    //sina
                                    bankName = Constants.sina;
                                    break;
                                case "+98200033":
                                    //mellat
                                    bankName = Constants.mellat;
                                    break;
                                case "+9830009419":
                                    //saderat
                                    bankName = Constants.saderat;
                                    resultList = Converter.saderat(message, bankName, sms.getTime());
                                    break;
                                case "+9820004747":
                                    //resalat
                                    bankName = Constants.resalat;
                                    resultList = Converter.resalat(message, bankName, sms.getTime());
                                    break;
                                case "+986715000015":
                                    //sepah
                                    bankName = Constants.sepah;
                                    resultList = Converter.sepah(Util.arabicToDecimal(message), bankName, sms.getTime());
                                    break;

                                case "+989126304496":
                                    //sepah

                                    if (sms.getMsg().contains(Constants.pasargad)) {
                                        bankName = Constants.pasargad;
                                        resultList = Converter.resalat(message, bankName, sms.getTime());
                                    }
                                    if (sms.getMsg().contains(Constants.meli)) {
                                        bankName = Constants.meli;
                                        resultList = Converter.saderat(message, bankName, sms.getTime());
                                    }
                                    if (sms.getMsg().contains(Constants.maskan)) {
                                        bankName = Constants.maskan;
                                    }
                                    if (sms.getMsg().contains(Constants.sepah)) {
                                        bankName = Constants.sepah;
                                        resultList = Converter.resalat(message, bankName, sms.getTime());
                                    }


                                    break;
                            }

                            break;
                        }
                    }
                }

                recyclerView.setAdapter(new SmsListAdapter(resultList, bankName));*/
            }
        } else {
            //smsArrayList = getAllSms();
            for (int i = 0; i < smsArrayList.size(); i++) {
                sms = smsArrayList.get(i);
                for (String address : addressList) {
                    if (sms.getAddress().equals(address)) {
                        String array[] = sms.getAddress().split(" ");
                        for (int j = 0; j < array.length; j++) ;
                        //System.out.println("Separated string==== " + j + ": " + array[j]);
                    }
                }
            }
        }
    }

    public List<Sms> getAllSms() {
        List<Sms> lstSms = new ArrayList<Sms>();
        Sms objSms = new Sms();
        Uri message = Uri.parse("content://sms/");
        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(message, null, null, null, null);
        startManagingCursor(c);
        int totalSMS = c.getCount();

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {

                objSms = new Sms();
                objSms.setId(c.getString(c.getColumnIndexOrThrow("_id")));
                objSms.setAddress(c.getString(c
                        .getColumnIndexOrThrow("address")));
                objSms.setMsg(c.getString(c.getColumnIndexOrThrow("body")));
                objSms.setReadState(c.getString(c.getColumnIndex("read")));
                objSms.setTime(c.getString(c.getColumnIndexOrThrow("date")));
                if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
                    objSms.setFolderName("inbox");
                } else {
                    objSms.setFolderName("sent");
                }

                lstSms.add(objSms);
                c.moveToNext();
            }
        }
        // else {
        // throw new RuntimeException("You have no SMS");
        // }
        c.close();

        return lstSms;
    }
}