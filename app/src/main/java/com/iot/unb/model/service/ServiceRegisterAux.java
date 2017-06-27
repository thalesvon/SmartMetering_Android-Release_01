package com.iot.unb.model.service;

import com.iot.unb.model.domain.register.Service;
import com.iot.unb.model.domain.register.ServiceRegister;

/**
 * Created by aclopesjr on 5/15/17.
 */

public class ServiceRegisterAux {

    public static final String SERVICE_NAME_FOR_LOCATION           = "GetLocation";
    public static final String SERVICE_NAME_FOR_NETWORK_SIGNAL     = "GetNetworkSignal";
    public static final String SERVICE_NAME_FOR_BATTERY            = "GetBatteryLevel";
    //public static final String SERVICE_NAME_FOR_NETWORK_TRAFFIC    = "GetNetworkTraffic";
    //public static final String SERVICE_NAME_FOR_LOSS_PACKAGE       = "GetLossPackage";
    public static final String SERVICE_NAME_FOR_MODEL              = "GetModel";
    public static final String SERVICE_NAME_FOR_BRANCH             = "GetBranch";
    public static final String SERVICE_NAME_FOR_OS                 = "GetOperationSystem";

    public static ServiceRegister servicesWithRoot() {
        ServiceRegister serviceRegister = new ServiceRegister();

        serviceRegister.addService(serviceWithLocation());
        serviceRegister.addService(serviceWithNetworkSignal());
        serviceRegister.addService(serviceWithBatteryLevel());
        serviceRegister.addService(serviceWithModel());
        serviceRegister.addService(serviceWithBranch());
        serviceRegister.addService(serviceWithOS());

        return serviceRegister;
    }

    private static Service serviceWithLocation() {
        Service service = new Service();
        service.setName(SERVICE_NAME_FOR_LOCATION);

        service.addParameter("latitude", "float");
        service.addParameter("longitude", "float");
        service.addParameter("deviceId", "string");

        return service;
    }

    private static Service serviceWithNetworkSignal() {
        Service service = new Service();
        service.setName(SERVICE_NAME_FOR_NETWORK_SIGNAL);

        service.addParameter("carrier", "string");
        service.addParameter("signal", "float");
        service.addParameter("deviceId", "string");

        return service;
    }

    private static Service serviceWithBatteryLevel() {
        Service service = new Service();
        service.setName(SERVICE_NAME_FOR_BATTERY);

        service.addParameter("state", "string");
        service.addParameter("percentage", "float");
        service.addParameter("deviceId", "string");

        return service;
    }

    private static Service serviceWithModel() {
        Service service = new Service();
        service.setName(SERVICE_NAME_FOR_MODEL);

        service.addParameter("model", "string");
        service.addParameter("deviceId", "string");

        return service;
    }

    private static Service serviceWithBranch() {
        Service service = new Service();
        service.setName(SERVICE_NAME_FOR_BRANCH);

        service.addParameter("branch", "string");
        service.addParameter("deviceId", "string");

        return service;
    }

    private static Service serviceWithOS() {
        Service service = new Service();
        service.setName(SERVICE_NAME_FOR_OS);

        service.addParameter("os", "string");
        service.addParameter("deviceId", "string");

        return service;
    }
}
