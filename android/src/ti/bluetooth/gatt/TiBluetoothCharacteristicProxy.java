package ti.bluetooth.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiBlob;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ti.bluetooth.TiBluetoothModule;

@Kroll.proxy(parentModule = TiBluetoothModule.class)
public class TiBluetoothCharacteristicProxy
    extends TiBluetoothGattIdentifiable {
  private BluetoothGattCharacteristic characteristic;
  private TiBluetoothServiceProxy serviceProxy;
  private List<TiBluetoothDescriptorProxy> descriptors;

  public TiBluetoothCharacteristicProxy(
      TiBluetoothServiceProxy serviceProxy,
      BluetoothGattCharacteristic characteristic) {
    this.serviceProxy = serviceProxy;
    this.characteristic = characteristic;
    this.descriptors = mapDescriptors(characteristic.getDescriptors());
  }

  private List<TiBluetoothDescriptorProxy>
  mapDescriptors(List<BluetoothGattDescriptor> descriptors) {
    List<TiBluetoothDescriptorProxy> mappedDescriptors = new ArrayList<>();

    for (BluetoothGattDescriptor descriptor : descriptors) {
      mappedDescriptors.add(new TiBluetoothDescriptorProxy(this, descriptor));
    }

    return mappedDescriptors;
  }

  @Kroll
      .getProperty
      @Kroll.method
      public TiBluetoothServiceProxy getService() {
    return serviceProxy;
  }

  @Override
  protected UUID getUUID() {
    return characteristic.getUuid();
  }

  @Kroll
      .getProperty
      @Kroll.method
      public TiBlob getValue() {
    return TiBlob.blobFromData(characteristic.getValue());
  }

  @Kroll
      .getProperty
      @Kroll.method
      public Object[] getDescriptors() {
    return descriptors.toArray();
  }

  public BluetoothGattCharacteristic getCharacteristic() {
    return characteristic;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof BluetoothGattCharacteristic) {
      BluetoothGattCharacteristic characteristic =
          (BluetoothGattCharacteristic)o;
      return this.getUuid().equals(convertUuid(characteristic.getUuid()));
    } else {
      return false;
    }
  }
}
