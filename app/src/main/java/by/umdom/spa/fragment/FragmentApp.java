package by.umdom.spa.fragment;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import by.umdom.spa.R;


public class FragmentApp extends Fragment {

    public FragmentApp() {
        // Required empty public constructor
    }


    private final static String TAG = by.umdom.spa.MainActivity.class.getSimpleName();
    final String LOG_TAG = "myLogs";

    //Timer
    TextView text1;
    TextView timer;
    int dataTimer = 10;
    int dataTimertt; //установка таймера
    int dataPar = 40;
    int dataIR = 40;
    int dataVibro = 10;
    int vibor;
    CountDownTimer MyTimer;
    //timerTemp
    Timer timerT;
    TimerTask mTimerTask;
    //End Timer
    String dataTempB; //получение температуры String
    String dataVibroSet; //показания вибро
    String dataSvetSet;  //показатель света
    long then = 0; //продолжительное нажатие кнопки света
    char[] sbprintToArray;
    String[] sbprintArrayStr;


    boolean flag = false, flag2 = true, flag5 = false,flag6 = false, flag7 = true, flag8 = true, flag9 = true, flag10 = true, flag11 = true,
            flag12 = true, flag15 = false, flag16 = false, flag18 = false,flag19 = false, flag4 = false, flagVanna = false, flagVanna2 = false, flagVanna3 = false,
            flagVanna4 = false, flagVanna5 = false;



    boolean falgImageButton13 = false;


    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String> pairedDeviceArrayList;
    ListView listViewPairedDevice;
    FrameLayout ButPanel;
    ArrayAdapter<String> pairedDeviceAdapter;
    private UUID myUUID;

    private StringBuilder sb = new StringBuilder();
    public TextView textInfo, temp, temp2, textTemp, textTempIR, textVibro, textSvet, d10, textVishi, d11, d12, d13;

    ImageButton ImageButton1, ImageButton2, ImageButton4, ImageButton5, ImageButton6, ImageButton7, ImageButton8, ImageButton9, ImageButton10, ImageButton11, ImageButton12, ImageButton13,
            ImageButton15, ImageButton16, ImageButton17, ImageButton18, ImageButton19,
            imageButton, imageButton2, imageButton3, imageButton4, imageButton5;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        final String UUID_STRING_WELL_KNOWN_SPP = "00001101-0000-1000-8000-00805F9B34FB";

        textInfo = getActivity().findViewById(R.id.textInfo);
        d10 = getActivity().findViewById(R.id.d10);
        temp = getActivity().findViewById(R.id.temp);
        temp2 = getActivity().findViewById(R.id.temp2);
        textTemp = getActivity().findViewById(R.id.textTemp);
        textTempIR = getActivity().findViewById(R.id.textTempIR);
        textVibro = getActivity().findViewById(R.id.textVibro);
        textVishi = getActivity().findViewById(R.id.textVishi);
        //textSvet = (TextView)findViewById(R.id.textSvet);

        listViewPairedDevice = getActivity().findViewById(R.id.pairedlist);
        ButPanel = getActivity().findViewById(R.id.ButPanel);

        if (!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)){
            Toast.makeText(getActivity(), "BLUETOOTH NOT support", Toast.LENGTH_LONG).show();
            getActivity().finish();
            return;
        }
        myUUID = UUID.fromString(UUID_STRING_WELL_KNOWN_SPP);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getActivity(), "Bluetooth is not supported on this hardware platform", Toast.LENGTH_LONG).show();
            getActivity().finish();
            return;
        }
        String stInfo = bluetoothAdapter.getName() + " " + bluetoothAdapter.getAddress();

        textInfo.setText(String.format("Это устройство: %s", stInfo));
        text1=getActivity().findViewById(R.id.text1);
        timer=getActivity().findViewById(R.id.timer);

        ImageButton1 = getActivity().findViewById(R.id.ImageButton1);
        ImageButton2 = getActivity().findViewById(R.id.ImageButton2);
        ImageButton4 = getActivity().findViewById(R.id.ImageButton4);
        ImageButton7 = getActivity().findViewById(R.id.ImageButton7);
        ImageButton5 = getActivity().findViewById(R.id.ImageButton5);
        ImageButton6 = getActivity().findViewById(R.id.ImageButton6);
        //ImageButton8 = (ImageButton) findViewById(R.id.ImageButton8);
        ImageButton9 = getActivity().findViewById(R.id.ImageButton9);
        //ImageButton10 = (ImageButton) findViewById(R.id.ImageButton10);
        ImageButton11 = getActivity().findViewById(R.id.ImageButton11);
        ImageButton12 = getActivity().findViewById(R.id.ImageButton12);
        ImageButton13 = getActivity().findViewById(R.id.ImageButton13);
        ImageButton15 = getActivity().findViewById(R.id.ImageButton15);
        ImageButton16 = getActivity().findViewById(R.id.ImageButton16);
        ImageButton18 = getActivity().findViewById(R.id.ImageButton18); //душ виши
        ImageButton19 = getActivity().findViewById(R.id.ImageButton19);
        ImageButton17 = getActivity().findViewById(R.id.ImageButton17);

        imageButton = getActivity().findViewById(R.id.imageButton);
        imageButton2 = getActivity().findViewById(R.id.imageButton2);
        imageButton3 = getActivity().findViewById(R.id.imageButton3);
        imageButton4 = getActivity().findViewById(R.id.imageButton4);
        imageButton5 = getActivity().findViewById(R.id.imageButton5);


        ImageButton13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            { //плюс
                switch(vibor) {
                    case 1: //таймер
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = "T".getBytes();
//                            myThreadConnected.write(bytesToSend);
//                        }
//                        break;
//
//                    case 2: //установка температуры пара
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = String.valueOf("P").getBytes();
//                            myThreadConnected.write(bytesToSend); }
//                        break;
//
//                    case 4: //установка температуры IR
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = String.valueOf("X").getBytes();
//                            myThreadConnected.write(bytesToSend); }
//                        break;
//
//                    case 6:
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = "N".getBytes();
//                            myThreadConnected.write(bytesToSend);
//                        }
//                        break;
//
//                    case 7: //установка вибро
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = "v".getBytes();
//                            myThreadConnected.write(bytesToSend); }
//                        break;

                    default:
                        break;
                }
            }});

        ImageButton17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            { //минус
                switch(vibor) {
//                    case 1:
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = "t".getBytes();
//                            myThreadConnected.write(bytesToSend);
//                        }
//                        break;
//
//                    case 2:
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = String.valueOf("p").getBytes();
//                            myThreadConnected.write(bytesToSend); }
//                        break;
//
//                    case 4:
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = String.valueOf("x").getBytes();
//                            myThreadConnected.write(bytesToSend); }
//
//                        break;
//
//                    case 6:
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = "n".getBytes();
//                            myThreadConnected.write(bytesToSend);
//                        }
//                        break;
//
//                    case 7:
//                        if (myThreadConnected != null) {
//                            byte[] bytesToSend = "V".getBytes();
//                            myThreadConnected.write(bytesToSend);
//                        }
//                        break;

                    default:
                        break;
                }
            }});

        //установка таймера
        ImageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag2 == true) {
                    ImageButton2.setImageResource(R.drawable.timerg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    ImageButton9.setImageResource(R.drawable.infared);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton11.setImageResource(R.drawable.vichy);
                    ImageButton12.setImageResource(R.drawable.vibromassaje);
                    ImageButton7.setImageResource(R.drawable.steam);
                    flag2 = false; flag7 = true; flag8 = true; flag9 = true; flag10 = true; flag11 = true; flag12 = true;
                    vibor = 1;
                }
                else {
                    ImageButton2.setImageResource(R.drawable.timer); flag2 = true;vibor = 0;}
            }
        });
        //установка вибромассажа
        ImageButton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag7 == true) {
                    ImageButton7.setImageResource(R.drawable.steamg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    ImageButton9.setImageResource(R.drawable.infared);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton11.setImageResource(R.drawable.vichy);
                    ImageButton12.setImageResource(R.drawable.vibromassaje);
                    ImageButton2.setImageResource(R.drawable.timer);
                    flag7 = false; flag2=true;
                    vibor = 2;
                }
                else {
                    ImageButton7.setImageResource(R.drawable.steam); flag7 = true;vibor = 0; }
            }});

        //установка IR
        ImageButton9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag9 == true) {
                    ImageButton9.setImageResource(R.drawable.infaredg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton11.setImageResource(R.drawable.vichy);
                    ImageButton12.setImageResource(R.drawable.vibromassaje);
                    ImageButton7.setImageResource(R.drawable.steam);
                    ImageButton2.setImageResource(R.drawable.timer);
                    flag9 = false; flag7 = true; flag8 = true; flag2 = true; flag10 = true; flag11 = true; flag12 = true;
                    vibor = 4;
                }
                else {
                    ImageButton9.setImageResource(R.drawable.infared); flag9 = true; vibor = 0;}
            }});

        //установка душа виши
        ImageButton11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag11 == true) {
                    ImageButton11.setImageResource(R.drawable.vichyg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    ImageButton9.setImageResource(R.drawable.infared);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton12.setImageResource(R.drawable.vibromassaje);
                    ImageButton7.setImageResource(R.drawable.steam);
                    ImageButton2.setImageResource(R.drawable.timer);
                    flag11 = false; flag7 = true; flag8 = true; flag9 = true; flag10 = true; flag2 = true; flag12 = true;
                    vibor = 6;
                }
                else {
                    ImageButton11.setImageResource(R.drawable.vichy); flag11 = true; vibor = 0; }
            }});
        //установка вибро
        ImageButton12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag12 == true) {
                    ImageButton12.setImageResource(R.drawable.vibromassajeg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    ImageButton9.setImageResource(R.drawable.infared);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton11.setImageResource(R.drawable.vichy);
                    ImageButton7.setImageResource(R.drawable.steam);
                    ImageButton2.setImageResource(R.drawable.timer);
                    flag12 = false; flag7 = true; flag8 = true; flag9 = true; flag10 = true; flag11 = true; flag2 = true;
                    vibor = 7;
                }
                else {
                    ImageButton12.setImageResource(R.drawable.vibromassaje); flag12 = true;vibor = 0; }
            }});
        //onoff
        ImageButton1.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {
//            if (flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "a".getBytes();
//            myThreadConnected.write(bytesToSend); }
//            ImageButton1.setEnabled(false);
//            ImageButton1.setImageAlpha(100);
//            flag = false;
//            flag6 = false;
//        }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "A".getBytes();
//            myThreadConnected.write(bytesToSend);}
//            ImageButton1.setEnabled(false);
//            ImageButton1.setImageAlpha(100);
//
//            flag = true;
//        }
        }});
        //вентилятор
//        ImageButton5.setOnClickListener(new View.OnClickListener() {public void onClick(View v) //вентилятор
//        {   if (flag5 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "B".getBytes();
//            myThreadConnected.write(bytesToSend);
//            flag5 = true;
//            // ImageButton5.setImageResource(R.drawable.ventg);
//        } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "b".getBytes();
//            myThreadConnected.write(bytesToSend);
//            flag5 = false;
//            // ImageButton5.setImageResource(R.drawable.vent);
//        } } }});
//        //пар
//        ImageButton6.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {
//            if (flag6 == false && flag == true ) { if (myThreadConnected != null) {
//                byte[] bytesToSend = "E".getBytes();
//                myThreadConnected.write(bytesToSend);}
//                //ImageButton6.setEnabled(false);
//                ImageButton6.setImageAlpha(100);
//                ImageButton6.setImageResource(R.drawable.parg);
//                flag6 = true;
//                ImageButton15.setImageResource(R.drawable.ir);
//                flag15 = false;
//            }
//            else { if (myThreadConnected != null) {
//                byte[] bytesToSend = "e".getBytes();
//                myThreadConnected.write(bytesToSend);}
//                ImageButton6.setImageResource(R.drawable.par);
//                flag6 = false;
//            }
//        }});
//        //IR
//        ImageButton15.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {   if (flag15 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "D".getBytes();
//            myThreadConnected.write(bytesToSend);}
//            ImageButton15.setImageResource(R.drawable.irg);
//            flag15 = true;
//            ImageButton6.setImageResource(R.drawable.par);
//            flag6 = false; }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "d".getBytes();
//            myThreadConnected.write(bytesToSend);}
//            ImageButton15.setImageResource(R.drawable.ir);
//            flag15 = false;}}});
//        //свет
//        ImageButton16.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {   if (flag16 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "C".getBytes();
//            myThreadConnected.write(bytesToSend);
//            flag16 = true;
//            //ImageButton16.setImageResource(R.drawable.svetg);
//        } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "c".getBytes();
//            myThreadConnected.write(bytesToSend);
//            //ImageButton16.setImageResource(R.drawable.svet);
//        }flag16 = false;}}});
//        //душ виши
//        ImageButton18.setOnClickListener(new View.OnClickListener() {public void onClick(View v) //душ виши
//        {   if (flag18 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "G".getBytes();
//            myThreadConnected.write(bytesToSend);
//            ImageButton18.setImageResource(R.drawable.vodag);
//        } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "g".getBytes();
//            myThreadConnected.write(bytesToSend);
//            ImageButton18.setImageResource(R.drawable.voda);
//        } }
//            flag18 = !flag18; }});
//        //вибро
//        ImageButton19.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {   if (flag19 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "H".getBytes();
//            myThreadConnected.write(bytesToSend);
//            ImageButton19.setImageResource(R.drawable.vibrog); } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "h".getBytes();
//            myThreadConnected.write(bytesToSend);
//            ImageButton19.setImageResource(R.drawable.vibro); } }
//            flag19 = !flag19; }});
////ТАЙМЕР
//        ImageButton4.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {   if (flag4 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "I".getBytes();
//            myThreadConnected.write(bytesToSend);
//            ImageButton4.setImageResource(R.drawable.timeg); } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "i".getBytes();
//            myThreadConnected.write(bytesToSend);
//            ImageButton4.setImageResource(R.drawable.time); } }
//            flag4 = !flag4; }});
//
//
////ванна гидро1
//        imageButton.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {   if (flagVanna == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "J".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton.setImageResource(R.drawable.gidro1); } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "j".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton.setImageResource(R.drawable.gidro1g); } }
//            flagVanna = !flagVanna; }});
////ванна гидро2
//        imageButton2.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {   if (flagVanna2 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "K".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton2.setImageResource(R.drawable.gidro2); } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "k".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton2.setImageResource(R.drawable.gidro2g); } }
//            flagVanna2 = !flagVanna2; }});
////ванна гидро3
//        imageButton3.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {   if (flagVanna3 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "L".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton3.setImageResource(R.drawable.gidro3); } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "l".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton3.setImageResource(R.drawable.gidro3g); } }
//            flagVanna3 = !flagVanna3; }});
////ванна гидро4
//        imageButton4.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {   if (flagVanna4 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "M".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton4.setImageResource(R.drawable.gidro4); } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "m".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton4.setImageResource(R.drawable.gidro4g); } }
//            flagVanna4 = !flagVanna4; }});
//
////ванна свет
//        imageButton5.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
//        {   if (flagVanna5 == false && flag == true) { if (myThreadConnected != null) {
//            byte[] bytesToSend = "O".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton5.setImageResource(R.drawable.svetvanna); } }
//        else { if (myThreadConnected != null) {
//            byte[] bytesToSend = "o".getBytes();
//            myThreadConnected.write(bytesToSend);
//            imageButton5.setImageResource(R.drawable.svetvannag); } }
//            flagVanna5 = !flagVanna5; }});
    } // END onCreate


    @Override
    public void onStart() { // Запрос на включение Bluetooth
        super.onStart();

    }

    @Override
    public void onDestroy() { // Закрытие приложения
        super.onDestroy();
    }

    private class ThreadConnected extends Thread {    // Поток - приём и отправка данных

        private final InputStream connectedInputStream;
        private final OutputStream connectedOutputStream;

        private String sbprint;

        public ThreadConnected(BluetoothSocket socket) {

            InputStream in = null;
            OutputStream out = null;

            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            connectedInputStream = in;
            connectedOutputStream = out;
        }

        @Override
        public void run() { // Приём данных

            while (true) {
                try {
                    byte[] buffer = new byte[1];
                    int bytes = connectedInputStream.read(buffer);

                    String strIncom = new String(buffer, 0, bytes);

                    sb.append(strIncom); // собираем символы в строку
                    int endOfLineIndex = sb.indexOf("\r\n"); // определяем конец строки

                    if (endOfLineIndex > 0) {

                        sbprint = sb.substring(0, endOfLineIndex);
                        sb.delete(0, sb.length());
                        //Log.d(LOG_TAG, "***приём: " + sbprint+ "***"  );

                        sbprintArrayStr = sbprint.split(",");
                        Log.d(LOG_TAG, "***приём: " + Arrays.toString(sbprintArrayStr) + "***"  );

                        //sbprintToArray = sbprint.toCharArray();

                        //a = sbprint.substring(0,1);

//                        for(int i = 0; i < sbprintToArray.length; i++) {
//                            Log.d(LOG_TAG, "***приём: " + sbprintToArray[i] + "***"  );
////                        }

                       getActivity().runOnUiThread(new Runnable() { // Вывод данных

                            @Override
                            public void run() {

                                if(sbprintArrayStr[0].equals("A")) {
                                    ImageButton1.setEnabled(true);
                                    ImageButton1.setImageResource(R.drawable.onoffg);
                                    ImageButton1.setImageAlpha(255);
                                    flag = true;
                                    d10.setText("ON");
                                }
                                if(sbprintArrayStr[0].equals("a")) {
                                    ImageButton1.setEnabled(true);
                                    ImageButton1.setImageAlpha(255);
                                    //ImageButton1.setBackgroundColor(Color.parseColor("#ffffff"));
                                    ImageButton1.setImageResource(R.drawable.onoff);
                                    ImageButton5.setImageResource(R.drawable.vent);
                                    ImageButton6.setImageResource(R.drawable.par);
                                    ImageButton15.setImageResource(R.drawable.ir);
                                    ImageButton16.setImageResource(R.drawable.svet);
                                    ImageButton18.setImageResource(R.drawable.voda);
                                    ImageButton19.setImageResource(R.drawable.vibro);
                                    ImageButton4.setImageResource(R.drawable.time);
                                    flag = false;
                                    flag6 = false;
                                    flagVanna = false; flagVanna2 = false; flagVanna3 = false; flagVanna4 = false;
                                    flag4 = false; flag5 = false; flag15 = false; flag18 = false;
                                    flag16 = false; flag6 = false; flag19 = false;
                                    d10.setText("OFF");
                                }

                                if (sbprintArrayStr[1].equals("b")) {// отключение вентилятор
                                    ImageButton5.setImageResource(R.drawable.vent);
                                    flag5 = false;
                                }

                                if (sbprintArrayStr[1].equals("B")) {//включение вентилятор
                                    ImageButton5.setImageResource(R.drawable.ventg);
                                    flag5 = true;}

                                if (sbprintArrayStr[2].equals("c")) {// отключение света
                                    ImageButton16.setImageResource(R.drawable.svet);
                                    flag16 = false; }

                                if (sbprintArrayStr[2].equals("C")) {//включение света
                                    ImageButton16.setImageResource(R.drawable.svetg);
                                    flag16 = true; }

                                if (sbprintArrayStr[3].equals("d")) {//отключение IR
                                    ImageButton15.setImageResource(R.drawable.ir);
                                    flag15 = false;
                                }

                                if (sbprintArrayStr[3].equals("D")){  //включение IR
                                    ImageButton15.setImageResource(R.drawable.irg);
                                    flag15 = true;
                                }

                                if ( (sbprintArrayStr[4].equals("e")) ) {//отключение Пара
                                    ImageButton6.setImageResource(R.drawable.par);
                                    flag6 = false;
                                    //ImageButton6.setEnabled(true);

                                }
                                if (sbprintArrayStr[4].equals("E")) {//включение Пара
                                    ImageButton6.setImageResource(R.drawable.parg);
                                    //ImageButton6.setEnabled(true);
                                    ImageButton6.setImageAlpha(255);
                                    flag6 = true;
                                }

                                if (sbprintArrayStr[7].equals("g")) {// отключение виши
                                    ImageButton18.setImageResource(R.drawable.voda);
                                    flag18 = false;}

                                if (sbprintArrayStr[7].equals("G")) {//включение виши
                                    ImageButton18.setImageResource(R.drawable.vodag);
                                    flag18 = true;}

                                if (sbprintArrayStr[8].equals("h")) {// отключение вибро
                                    ImageButton19.setImageResource(R.drawable.vibro);
                                    flag19 = false;}

                                if (sbprintArrayStr[8].equals("H")) {//включение вибро
                                    ImageButton19.setImageResource(R.drawable.vibrog);
                                    flag19 = true;}

                                if (sbprintArrayStr[9].equals("i")) {//отключение таймера
                                    ImageButton4.setImageResource(R.drawable.time);
                                    flag4 = false;
                                }
                                if (sbprintArrayStr[9].equals("I")){//включение таймера
                                    ImageButton4.setImageResource(R.drawable.timeg);
                                    flag4 = true;}

                                textVishi.setText(sbprintArrayStr[5]); //установка виши
                                temp2.setText(sbprintArrayStr[6]); //температура воды
                                textVibro.setText(sbprintArrayStr[10]); //установка вибро
                                timer.setText(sbprintArrayStr[11]);  //установка таймера
                                text1.setText(sbprintArrayStr[12]);  //работа таймера
                                temp.setText(sbprintArrayStr[13]);  //температура
                                textTemp.setText(sbprintArrayStr[14]);  //установка пара
                                textTempIR.setText(sbprintArrayStr[15]);  //установка IR

                                if (sbprintArrayStr[16].equals("j")) {//ванна хромотерапия
                                    imageButton.setImageResource(R.drawable.gidro1);
                                    flagVanna = false;}

                                if (sbprintArrayStr[16].equals("J")) {
                                    imageButton.setImageResource(R.drawable.gidro1g);
                                    flagVanna = true;}

                                if (sbprintArrayStr[17].equals("k")) {//ванна массаж
                                    imageButton2.setImageResource(R.drawable.gidro2);
                                    flagVanna2 = false;}

                                if (sbprintArrayStr[17].equals("K")) {
                                    imageButton2.setImageResource(R.drawable.gidro2g);
                                    flagVanna2 = true;}

                                if (sbprintArrayStr[18].equals("l")) {//ванна гидромассаж
                                    imageButton3.setImageResource(R.drawable.gidro3);
                                    flagVanna3 = false; }
                                if (sbprintArrayStr[18].equals("L")){
                                    imageButton3.setImageResource(R.drawable.gidro3g);
                                    flagVanna3 = true;}

                                if (sbprintArrayStr[19].equals("m")) {//ванна пустая функция
                                    imageButton4.setImageResource(R.drawable.gidro4);
                                    flagVanna4 = false;
                                }
                                if (sbprintArrayStr[19].equals("M")){
                                    imageButton4.setImageResource(R.drawable.gidro4g);
                                    flagVanna4 = true;}

                                if (sbprintArrayStr[20].equals("o")) {//ванна пустая функция
                                    imageButton5.setImageResource(R.drawable.svetvanna);
                                    flagVanna5 = false;
                                }
                                if (sbprintArrayStr[20].equals("O")){
                                    imageButton5.setImageResource(R.drawable.svetvannag);
                                    flagVanna5 = true;}

                            }
                        });
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }

        public void write(byte[] buffer) {
            try {
                connectedOutputStream.write(buffer);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

} // END
