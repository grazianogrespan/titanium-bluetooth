package ti.bluetooth.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiBlob;

import ti.bluetooth.TiBluetoothModule;

@Kroll.proxy(parentModule = TiBluetoothModule.class)
public class TiBluetoothDescriptorProxy extends KrollProxy {
  private TiBluetoothCharacteristicProxy characteristicProxy;
  private BluetoothGattDescriptor descriptor;

  public TiBluetoothDescriptorProxy(
      TiBluetoothCharacteristicProxy characteristicProxy,
      BluetoothGattDescriptor descriptor) {
    this.characteristicProxy = characteristicProxy;
    this.descriptor = descriptor;
  }

  @Kroll
      .getProperty
      @Kroll.method
      public String getUuid() {
    return descriptor.getUuid().toString().toUpperCase();
  }

  @Kroll
      .getProperty
      @Kroll.method
      public TiBlob getValue() {
    return TiBlob.blobFromData(descriptor.getValue());
  }

  @Kroll
      .setProperty
      @Kroll.method
      public boolean setValue(TiBlob value) {
    return descriptor.setValue(
        BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
  }

  @Kroll
      .getProperty
      @Kroll.method
      public TiBluetoothCharacteristicProxy getCharacteristic() {
    return characteristicProxy;
  }

  public BluetoothGattDescriptor getDescriptor() { return descriptor; }
}
