package smarthome.byids.com.easybyids;

/**
 * Created by byids on 6/6/16.
 */
interface Send {
    int TCPCommandTypeHDL_BYIDS_SEND = 0;//控制命令发送
    int TCPCommandType_BYIDS_ACCESS=1;//用户验证
    int TCPCommandType_BYIDS_RESTARTSOFTWARE=2;//重启软件
    int TCPCommandType_BYIDS_RESTARTSERVERS=3;//重启主机
    //音乐控制相关
    int TCPCommandTypeUpdateMusicListMoren=4 ;//默认列表
    int TCPCommandTypeUpdateCurrentMusicList = 5;//更新当前列表 **暂时废弃
    int TCPCommandTypeUpdateMusicList=6;//其他模式的列表  **暂时废弃
    int TCPCommandTypeUpdateCurrentMusicSong=7; //当前歌曲信息
    int TCPCommandTypeUpdateMusicModel=8;//当前歌曲模式
    int TCPCommandTypeChangeVolume=9;//改变声音
    int TCPCommandTypePauseMusic = 10; //暂停音乐
    int TCPCommandTypeStartMusic=11;  //取消暂停
    int TCPCommandTypeNextMusic=12;   //下一曲的命令
    int TCPCommandTypePopMusic=13;//上一曲的命令
    int TCPCommandTypeUpdateEffectRooms=14;//更新当前受影响的音乐房间
    int TCPCommandTypeUpdateEffectRoomSingleOne = 15;//更新当前受影响的音乐房间(单房控制)
    //闹钟相关
    int TCPCommandTypeUpdateAlarmClockInfo=16;
    int TCPCommandTypeDeleteAlarmClockInfo=17;
    int TCPCommandTypeStopAlarmClockInfo=18;
    int TCPCommandTypeLittleSleepAlarmClockInfo=19;
    int TCPCommandTYpeUpdateAlarmClockInfoList = 20;//更新闹钟列表
    //系统信息 管家面板上 主机页面
    int TCPCommandTypeUpdateSystemInfo=21;
    int TCPCommandTypeStopSystemInfo=22;
    //提醒 信息,管家面板上提醒
    int TCPCommandTypeUpdateRemindarInfo=23;
    int TCPCommandTypeDeleteRemindarInfo=24;
    int TCPCommandTypeUpdateGraphicsInfo = 25;
    //离家模式
    int TCPCommandType_HomeLeaveModel=26;
    //场景灯相关
    int TCPCommandTypeUpdateEffectRoomLightWaveSingleOne=27;//开关 灯光是否随音乐变化
    int TCPCommandTypeUpdateEffectRoomChannalVolumnAdd=28;//声音+
    int TCPCommandTypeUpdateEffectRoomChannalVolumnMinus=29;//声音-

    int TCPCommandType_HomeBackModel = 30;//归家模式

    int TCPCommandType_HomeRoomResetModel=31;//重启主机 在管家->主机里面

    int TCPCommandTypeHeartBeat=32;

}
interface Receive {
    int TCPCommandTypeHDL_BYIDS_SEND_Success = 0;//控制命令发送
    int TCPCommandType_BYIDS_ACCESS_Success=1;//用户验证
    int TCPCommandType_BYIDS_RESTARTSOFTWARE_Success=2;//重启软件
    int TCPCommandType_BYIDS_RESTARTSERVERS_Success=3;//重启主机

    int TCPCommandTypeUpdateMusicListMorenSuccess=4; //默认列表
    int TCPCommandTypeUpdateCurrentMusicListSuccess=5;//更新当前列表
    int TCPCommandTypeUpdateMusicListSuccess=6; //其他模式的列表
    int TCPCommandTypeUpdateCurrentMusicSongSuccess=7;//当前歌曲下标
    int TCPCommandTypeUpdateMusicModelSuccess=8; //当前歌曲模式
    int TCPCommandTypeChangeVolumeSuccess=9; //改变声音
    int TCPCommandTypePauseMusicSuccess=10;  //暂停音乐
    int TCPCommandTypeStartMusicSuccess=11;    //取消暂停
    int TCPCommandTypeNextMusicSuccess=12;   //下一曲的命令
    int TCPCommandTypePopMusicSuccess=13;//上一曲的命令
    int TCPCommandTypeUpdateEffectRoomsSuccess=14;//更新当前受影响的音乐房间
    int TCPCommandTypeUpdateEffectRoomSingleOneSuccess=15;//更新当前受影响的音乐房间(单房控制)

    int TCPCommandTypeUpdateAlarmClockInfoSuccess=16;//成功更新闹钟信心
    int TCPCommandTypeDeleteAlarmClockInfoSuccess=17;//删除闹钟成功
    int TCPCommandTypeStopAlarmClockInfoSuccess=18;//停止闹钟成功
    int TCPCommandTypeLittleSleepAlarmClockInfoSuccess=19;//小睡成功
    int TCPCommandTYpeUpdateAlarmClockInfoListSuccess=20;//更新闹钟列表成功

    int TCPCommandTypeUpdateSystemInfoSuccess=21;//更新系统信息成功
    int TCPCommandTypeStopSystemInfoSuccess=22;//

    int TCPCommandTypeUpdateRemindarInfoSuccess=23;
    int TCPCommandTypeDeleteRemindarInfoSuccess=24;
    int TCPCommandTypeUpdateGraphicsInfoSuccess=25;

    int TCPCommandType_HomeLeaveModelSuccess=26;

    int TCPCommandTypeUpdateEffectRoomLightWaveSingleOneSuccess=27;
    int TCPCommandTypeUpdateEffectRoomChannalVolumnAddSuccess=28;
    int TCPCommandTypeUpdateEffectRoomChannalVolumnMinusSuccess=29;

    int TCPCommandType_HomeBackModelSuccess=30;

    int TCPCommandType_HomeRoomResetModelSuccess=31;//

}
interface MusicModel{
    int ByidsMusicModelDANQU=0;//单曲循环
    int ByidsMusicModelSHUNXU=1;//顺序循环
    int ByidsMusicModelSUIJI=2;//随机播放
}
interface FamilyModel{
    int ByidsFamilyModel_xiuxian = 0;
    int ByidsFamilyModel_yule = 1;
    int ByidsFamilyModel_juhui = 2;
    int ByidsFamilyModel_likai = 3;
}
interface CurtainType{
    float ByidsCurtainTypeBuLian = 1 << 0;
    float ByidsCurtainTypeShaLian = 1 << 1;
    float ByidsCurtainTypeBaiyeLian = 1 << 2;
    float ByidsCurtainTypeJuanLian = 1 << 3;
}
interface AirConditionType{
    int ByidsAirConditionTypeIce = 12;
    int ByidsAirConditionTypeHot = 13;
    int ByidsAirConditionTypeWet = 10;
    int ByidsAirConditionTypeWind = 11;
}
interface AirConditionControlType{
    int ByidsAirConditionControlSendTypePower = 50;
    int ByidsAirConditionControlSendTypeTemp = 51;
    int ByidsAirConditionControlSendTypeSpeed = 52;
    int ByidsAirConditionControlSendTypeModel = 53;
}
interface AirConditionWindSpeedType{
    int ByidsAirConditionWindSpeedLow = 20;
    int ByidsAirConditionWindSpeedMidium = 21;
    int ByidsAirConditionWindSpeedHigh = 22;
}