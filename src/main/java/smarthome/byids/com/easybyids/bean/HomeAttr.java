package smarthome.byids.com.easybyids.bean;

import java.util.List;

/**
 * Created by asus on 2016/1/19.
 */
public class  HomeAttr {

    private Music music;


    private Hiddendoor hiddendoor;


    private Outdoorwaterflow outdoorwaterflow;


    private Camera camera;


    private Lock lock;


    private Alarmclock alarmclock;


    private Cinemaroom cinemaroom;


    private Securityalarm securityalarm;


    private List<Rooms> rooms ;


    public void setMusic(Music music){

        this.music = music;

    }

    public Music getMusic(){

        return this.music;

    }

    public void setHiddendoor(Hiddendoor hiddendoor){

        this.hiddendoor = hiddendoor;

    }

    public Hiddendoor getHiddendoor(){

        return this.hiddendoor;

    }

    public void setOutdoorwaterflow(Outdoorwaterflow outdoorwaterflow){

        this.outdoorwaterflow = outdoorwaterflow;

    }

    public Outdoorwaterflow getOutdoorwaterflow(){

        return this.outdoorwaterflow;

    }

    public void setCamera(Camera camera){

        this.camera = camera;

    }

    public Camera getCamera(){

        return this.camera;

    }

    public void setLock(Lock lock){

        this.lock = lock;

    }

    public Lock getLock(){

        return this.lock;

    }

    public void setAlarmclock(Alarmclock alarmclock){

        this.alarmclock = alarmclock;

    }

    public Alarmclock getAlarmclock(){

        return this.alarmclock;

    }

    public void setCinemaroom(Cinemaroom cinemaroom){

        this.cinemaroom = cinemaroom;

    }

    public Cinemaroom getCinemaroom(){

        return this.cinemaroom;

    }

    public void setSecurityalarm(Securityalarm securityalarm){

        this.securityalarm = securityalarm;

    }

    public Securityalarm getSecurityalarm(){

        return this.securityalarm;

    }

    public void setRooms(List<Rooms> rooms){

        this.rooms = rooms;

    }

    public List<Rooms> getRooms(){

        return this.rooms;

    }
}
