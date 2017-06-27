package com.iot.unb.model.service;

import com.iot.unb.model.domain.register.Data;
import com.iot.unb.model.domain.register.DataRegister;
import com.iot.unb.model.domain.result.ServiceRegisterResult;
import com.iot.unb.model.domain.result.ServiceResult;

/**
 * Created by aclopesjr on 5/17/17.
 */

public class DataRegisterAux {

    public static DataRegister datasWithRoot() {

        DataRegister dataRegister = new DataRegister();

        dataRegister.addData(dataForLocation());
        dataRegister.addData(dataForSignalLevel());
        dataRegister.addData(dataForBatteryLevel());
        dataRegister.addData(dataForModel());
        dataRegister.addData(dataForBranch());
        dataRegister.addData(dataForOS());

        return dataRegister;
    }

    private static Data dataForLocation() {
        int serviceId = getServiceId(ServiceRegisterAux.SERVICE_NAME_FOR_LOCATION);
        if (serviceId == 0) {
            return null;
        }

        Data data = new Data();
        data.setServiceId(serviceId);
        data.addData("latitude", Device.getLatitude());
        data.addData("longitude", Device.getLongitude());
        data.addData("deviceId", Device.getUniqueIdentifier());

        return data;
    }

    private static Data dataForSignalLevel() {
        int serviceId = getServiceId(ServiceRegisterAux.SERVICE_NAME_FOR_NETWORK_SIGNAL);
        if (serviceId == 0) {
            return null;
        }

        Data data = new Data();
        data.setServiceId(serviceId);
        data.addData("carrier", Device.getCarrierName());
        data.addData("signal", Device.getSignalStrenght());
        data.addData("deviceId", Device.getUniqueIdentifier());

        return data;
    }

    private static Data dataForBatteryLevel() {
        int serviceId = getServiceId(ServiceRegisterAux.SERVICE_NAME_FOR_BATTERY);
        if (serviceId == 0) {
            return null;
        }

        Data data = new Data();
        data.setServiceId(serviceId);
        data.addData("state", Device.getBatteryState());
        data.addData("percentage", Device.getBatteryPercentage());
        data.addData("deviceId", Device.getUniqueIdentifier());

        return data;
    }

    private static Data dataForModel() {
        int serviceId = getServiceId(ServiceRegisterAux.SERVICE_NAME_FOR_MODEL);
        if (serviceId == 0) {
            return null;
        }

        Data data = new Data();
        data.setServiceId(serviceId);
        data.addData("model", Device.getModel());
        data.addData("deviceId", Device.getUniqueIdentifier());

        return data;
    }

    private static Data dataForBranch() {
        int serviceId = getServiceId(ServiceRegisterAux.SERVICE_NAME_FOR_BRANCH);
        if (serviceId == 0) {
            return null;
        }

        Data data = new Data();
        data.setServiceId(serviceId);
        data.addData("branch", Device.getBrand());
        data.addData("deviceId", Device.getUniqueIdentifier());

        return data;
    }

    private static Data dataForOS() {
        int serviceId = getServiceId(ServiceRegisterAux.SERVICE_NAME_FOR_OS);
        if (serviceId == 0) {
            return null;
        }

        Data data = new Data();
        data.setServiceId(serviceId);
        data.addData("os", Device.getOperationSystem());
        data.addData("deviceId", Device.getUniqueIdentifier());

        return data;
    }

    private static int getServiceId(final String serviceName) {

        //Loads registered services
        ServiceRegisterResult serviceRegisterResult = Storage.load(ServiceRegisterResult.fileName, ServiceRegisterResult.class);
        if (null != serviceRegisterResult) {

            for (ServiceResult service : serviceRegisterResult.getServices()) {
                if (service.getName().equals(serviceName)) {
                    return (int)service.getServiceId();
                }
            }
        }

        return 0;
    }
}
