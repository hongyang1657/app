package smarthome.byids.com.easybyids.bean;

/**
 * Created by asus on 2016/1/19.
 */
public class Hiddendoor {
    private String active;


    private String protocol;


    private String pwd;


    private String ibeacon_major;


    private String ibeacon_minor;


    private String ibeacon_uuid;


    public void setActive(String active){

        this.active = active;

    }

    public String getActive(){

        return this.active;

    }

    public void setProtocol(String protocol){

        this.protocol = protocol;

    }

    public String getProtocol(){

        return this.protocol;

    }

    public void setPwd(String pwd){

        this.pwd = pwd;

    }

    public String getPwd(){

        return this.pwd;

    }

    public void setIbeacon_major(String ibeacon_major){

        this.ibeacon_major = ibeacon_major;

    }

    public String getIbeacon_major(){

        return this.ibeacon_major;

    }

    public void setIbeacon_minor(String ibeacon_minor){

        this.ibeacon_minor = ibeacon_minor;

    }

    public String getIbeacon_minor(){

        return this.ibeacon_minor;

    }

    public void setIbeacon_uuid(String ibeacon_uuid){

        this.ibeacon_uuid = ibeacon_uuid;

    }

    public String getIbeacon_uuid(){

        return this.ibeacon_uuid;

    }
}
