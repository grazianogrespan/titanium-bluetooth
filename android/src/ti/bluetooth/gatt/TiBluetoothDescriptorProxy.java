package ti.bluetooth.gatt;

import android.bluetooth.BluetoothGattDescriptor;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiBlob;

import java.util.UUID;

import ti.bluetooth.TiBluetoothModule;

@Kroll.proxy(parentModule = TiBluetoothModule.class)
public class TiBluetoothDescriptorProxy extends TiBluetoothGattIdentifiable {
  private TiBluetoothCharacteristicProxy characteristicProxy;
  private BluetoothGattDescriptor descriptor;

  public TiBluetoothDescriptorProxy(
      TiBluetoothCharacteristicProxy characteristicProxy,
      BluetoothGattDescriptor descriptor) {
    this.characteristicProxy = characteristicProxy;
    this.descriptor = descriptor;
  }

  @Override
  protected UUID getUUID() {
    return descriptor.getUuid();
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
      public boolean setValue(int value) {
    return descriptor.setValue(mapValue(value));
  }

  private byte[] mapValue(int value) {
    byte[] mappedValue = null;

    switch (value) {
    case TiBluetoothModule.DESCRIPTOR_DISABLE_NOTIFICATION_VALUE:
      mappedValue = BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE;
      break;
    case TiBluetoothModule.DESCRIPTOR_ENABLE_INDICATION_VALUE:
      mappedValue = BluetoothGattDescriptor.ENABLE_INDICATION_VALUE;
      break;
    case TiBluetoothModule.DESCRIPTOR_ENABLE_NOTIFICATION_VALUE:
      mappedValue = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;
      break;
    }

    return mappedValue;
  }

  @Kroll
      .getProperty
      @Kroll.method
      public TiBluetoothCharacteristicProxy getCharacteristic() {
    return characteristicProxy;
  }

  public BluetoothGattDescriptor getDescriptor() { return descriptor; }
}
