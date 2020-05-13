package ti.bluetooth.broadcastReceiver;

//import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ti.bluetooth.listener.OnBluetoothStateChangedListener;
//import ti.bluetooth.listener.OnPeripheralConnectionStateChangedListener;
//import ti.bluetooth.peripheral.TiBluetoothPeripheralProxy;

public class TiBluetoohBroadcastReceiver extends BroadcastReceiver {
  private OnBluetoothStateChangedListener bluetoothStateChangedListener;
  //private OnPeripheralConnectionStateChangedListener peripheralConnectionStateChangedListener;

  public TiBluetoohBroadcastReceiver() { super(); }

  public TiBluetoohBroadcastReceiver(
          OnBluetoothStateChangedListener bluetoothStateChangedListener/*,
          OnPeripheralConnectionStateChangedListener peripheralConnectionStateChangedListener*/) {
    super();

    this.bluetoothStateChangedListener = bluetoothStateChangedListener;
    //this.peripheralConnectionStateChangedListener = peripheralConnectionStateChangedListener;
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    String action = intent.getAction();

    if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
      if (bluetoothStateChangedListener != null) {
        bluetoothStateChangedListener.onBluetoothStateChanged();
      }
    }
    /*
    if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED) ) {
      if (peripheralConnectionStateChangedListener != null) {
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

        peripheralConnectionStateChangedListener
                .onPeripheralConnectionStateDisconnected(
                        new TiBluetoothPeripheralProxy(device));
      }
    }

    if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED) ) {
      if (peripheralConnectionStateChangedListener != null) {
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

        peripheralConnectionStateChangedListener
                .onPeripheralConnectionStateConnected(
                        new TiBluetoothPeripheralProxy(device));
      }
    }
    */

  }
};