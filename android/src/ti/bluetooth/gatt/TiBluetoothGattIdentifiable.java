package ti.bluetooth.gatt;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import java.util.UUID;

import ti.bluetooth.TiBluetoothModule;

@Kroll.proxy(parentModule = TiBluetoothModule.class)
public abstract class TiBluetoothGattIdentifiable extends KrollProxy {

  @Kroll
      .getProperty
      @Kroll.method
      public String getUuid() {
    return convertUuid(getUUID());
  }

  protected String convertUuid(UUID uuid) {
    return uuid.toString().toUpperCase();
  }

  protected abstract UUID getUUID();
}
