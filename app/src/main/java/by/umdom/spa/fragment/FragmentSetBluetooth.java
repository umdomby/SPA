package by.umdom.spa.fragment;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import by.umdom.spa.R;
import by.umdom.spa.service.BluetoothService;

import static android.R.layout.simple_list_item_1;

/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentSetBluetooth extends Fragment {

    public FragmentSetBluetooth() {
        // Required empty public constructor
    }

    private Button buttonControl;
    final String LOG_TAG = "myLogs";
    static String MAC;
    public TextView textInfo;
    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String> pairedDeviceArrayList;
    ListView listViewPairedDevice;
    FrameLayout ButPanel;
    ArrayAdapter<String> pairedDeviceAdapter;
    private UUID myUUID;
    private StringBuilder sb = new StringBuilder();
    String[] sbprintArrayStr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_set_bluetooth, container, false);
    }

    @Override
    public void onStart() { // Запрос на включение Bluetooth
        super.onStart();
        buttonControl = getActivity().findViewById(R.id.btnControl);
        textInfo = getActivity().findViewById(R.id.textInfo);
        final String UUID_STRING_WELL_KNOWN_SPP = "00001101-0000-1000-8000-00805F9B34FB";

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

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
        setup();


        buttonControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().stopService(new Intent(getActivity(), BluetoothService.class));
                ButPanel.setVisibility(View.GONE); // открываем панель с кнопками
                listViewPairedDevice.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setup() { // Создание списка сопряжённых Bluetooth-устройств
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) { // Если есть сопряжённые устройства
            pairedDeviceArrayList = new ArrayList<>();
            for (BluetoothDevice device : pairedDevices) { // Добавляем сопряжённые устройства - Имя + MAC-адресс
                pairedDeviceArrayList.add(device.getName() + "\n" + device.getAddress());
            }
            pairedDeviceAdapter = new ArrayAdapter<>(getActivity(), simple_list_item_1, pairedDeviceArrayList);
            listViewPairedDevice.setAdapter(pairedDeviceAdapter);
            listViewPairedDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() { // Клик по нужному устройству
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listViewPairedDevice.setVisibility(View.GONE); // После клика скрываем список
                    String  itemValue = (String) listViewPairedDevice.getItemAtPosition(position);
                    MAC = itemValue.substring(itemValue.length() - 17); // Вычленяем MAC-адрес

                    //EventBus.getDefault().postSticky(new BluetoothEvent(MAC));

                    ButPanel.setVisibility(View.VISIBLE); // открываем панель с кнопками
                    startService();

                    //BluetoothDevice device2 = bluetoothAdapter.getRemoteDevice(MAC);
                    //myThreadConnectBTdevice = new ThreadConnectBTdevice(device2);
                    //myThreadConnectBTdevice.start();  // Запускаем поток для подключения Bluetooth
                }
            });
        }
    }

    public static String dataBluetooth()
    {
        return MAC;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent dataTimer) {
        if(requestCode == REQUEST_ENABLE_BT){ // Если разрешили включить Bluetooth, тогда void setup()
            if(resultCode == Activity.RESULT_OK) {
                setup();
            }
            else { // Если не разрешили, тогда закрываем приложение
                Toast.makeText(getActivity(), "BlueTooth не включён", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    }

    @Override
    public void onDestroy() { // Закрытие приложения
        super.onDestroy();
        getActivity().stopService(new Intent(getActivity(), BluetoothService.class));
    }

    public void startService(){

        getActivity().startService(new Intent(getActivity(), BluetoothService.class));
    }

}
