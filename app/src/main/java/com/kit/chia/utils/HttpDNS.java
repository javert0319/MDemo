package com.kit.chia.utils;

import okhttp3.Dns;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HttpDNS
 * @Description: HttpDNS
 * @Author: CHIA
 * @CreateDate: 2019/12/4
 */
public class HttpDNS implements Dns {
    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        if (hostname == null) {
            throw new UnknownHostException("hostname == null");
        } else {
            try {
                List<InetAddress> inetAddressList = new ArrayList<>();
                InetAddress[] inetAddresses = InetAddress.getAllByName(hostname);
                for (InetAddress inetAddress:inetAddresses) {
                    if (inetAddress instanceof Inet4Address){
                        inetAddressList.add(0,inetAddress);
                    }else{
                        inetAddressList.add(inetAddress);
                    }
                }
                return inetAddressList;
            }catch (NullPointerException exception){
                UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour");
                unknownHostException.initCause(exception);
                throw unknownHostException;
            }
        }
    }
}
