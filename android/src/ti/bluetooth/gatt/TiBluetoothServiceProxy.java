package ti.bluetooth.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import org.appcelerator.kroll.annotations.Kroll;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ti.bluetooth.TiBluetoothModule;

@Kroll.proxy(parentModule = TiBluetoothModule.class)
public class TiBluetoothServiceProxy extends TiBluetoothGattIdentifiable {
  private BluetoothGattService bluetoothGattService;
  private List<TiBluetoothCharacteristicProxy> characteristics;

  public TiBluetoothServiceProxy(BluetoothGattService bluetoothGattService) {
    this.bluetoothGattService = bluetoothGattService;
    this.characteristics =
        mapCharacteristics(bluetoothGattService.getCharacteristics());
  }

  private List<TiBluetoothCharacteristicProxy>
  mapCharacteristics(List<BluetoothGattCharacteristic> characteristics) {
    List<TiBluetoothCharacteristicProxy> characteristicList = new ArrayList<>();

    for (BluetoothGattCharacteristic characteristic : characteristics) {
      characteristicList.add(
          new TiBluetoothCharacteristicProxy(this, characteristic));
    }

    return characteristicList;
  }

  public List<TiBluetoothCharacteristicProxy> getCharacteristicsList() {
    return characteristics;
  }

  @Kroll
      .getProperty
      @Kroll.method
      public Object[] getCharacteristics() {
    return characteristics.toArray();
  }

  @Override
  protected UUID getUUID() {
    return bluetoothGattService.getUuid();
  }

  public TiBluetoothCharacteristicProxy
  findCharacteristic(BluetoothGattCharacteristic gattCharacteristic) {
    for (TiBluetoothCharacteristicProxy characteristicProxy : characteristics) {
      if (characteristicProxy.equals(gattCharacteristic)) {
        return characteristicProxy;
      }
    }

    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof BluetoothGattService) {
      BluetoothGattService service = (BluetoothGattService)o;
      return this.getUuid().equals(convertUuid(service.getUuid()));
    } else {
      return false;
    }
  }
}
