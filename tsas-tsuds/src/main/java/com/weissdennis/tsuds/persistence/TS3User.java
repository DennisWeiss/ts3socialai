package com.weissdennis.tsuds.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weissdennis.tsuds.model.ClientIpInfo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Entity
public class TS3User {

    @Id
    private String uniqueId;

    private Integer clientId;

    private String nickName;

    private String ip;

    private String hostName;

    private String city;

    private String region;

    private String country;

    private Double longitude;

    private Double latitude;

    private String postalCode;

    private String org;

    public TS3User() {
    }

    private TS3User withExtendedUserInfo(ClientIpInfo clientIpInfo) {
        TS3User ts3User = new TS3User();
        ts3User.setUniqueId(uniqueId);
        ts3User.setClientId(clientId);
        ts3User.setNickName(nickName);
        ts3User.setIp(ip);
        ts3User.setHostName(clientIpInfo.getHostname());
        ts3User.setCity(clientIpInfo.getCity());
        ts3User.setRegion(clientIpInfo.getRegion());
        ts3User.setCountry(clientIpInfo.getCountry());
        ts3User.setLatitude(clientIpInfo.getLatitude());
        ts3User.setLongitude(clientIpInfo.getLongitude());
        ts3User.setPostalCode(clientIpInfo.getPostal());
        ts3User.setOrg(clientIpInfo.getOrg());
        return ts3User;
    }

    public TS3User withExtendedUserInfo() {
        if (ip != null && !ip.equals("")) {
            try {
                return withExtendedUserInfo(new GsonBuilder().create().fromJson(new BufferedReader(new InputStreamReader(
                                new URL("http://ipinfo.io/" + ip + "/json").openConnection().getInputStream())),
                        ClientIpInfo.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }
}
