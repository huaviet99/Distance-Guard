package com.thesis.distanceguard.ble_module.core

import android.bluetooth.le.ScanResult
import android.os.Build
import android.os.ParcelUuid
import com.thesis.distanceguard.ble_module.BLEController
import com.thesis.distanceguard.ble_module.dao.Device
import com.thesis.distanceguard.ble_module.util.Constants


object ScanCallbackImpl {
    val mScanResults = HashMap<String, Device>()

     fun addScanResult(result: ScanResult) {
        synchronized(this) {
            val deviceAddress = result.device.address
            var uuid: ParcelUuid? = result.scanRecord?.serviceUuids?.get(0)



            if ((uuid.toString().startsWith(Constants.SERVICE_PREFIX))) {
                var rssi: Int = result.rssi
                var txPower: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    result.txPower
                } else {
                    -1
                }

                var timeStampNanos: Long = result.timestampNanos
                val timeStamp: Long = System.currentTimeMillis()
                var sessionId = deviceAddress

                var newDevice = Device(
                    uuid.toString(),
                    rssi,
                    txPower,
                    timeStampNanos,
                    timeStamp,
                    sessionId,
                    BLEController.isTeamMember(uuid.toString())
                )


                if (mScanResults.containsKey(deviceAddress)) {
                    newDevice.rssi = (newDevice.rssi + mScanResults[deviceAddress]!!.rssi).div(2)
                    newDevice.txPower =
                        (newDevice.txPower + mScanResults[deviceAddress]!!.txPower).div(2)
                }
                mScanResults[deviceAddress] = newDevice
            }
        }
    }
}