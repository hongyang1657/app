package smarthome.byids.com.easybyids;

/**
 * Created by byids on 16/6/7.
 */
public class Command {
    public enum commandType{
        //具体的commandData,可以参考/byids/ATModel/BYModel中数据的发送
        TCPCommandTypeHDL_BYIDS_SEND,//控制命令发送 0
        TCPCommandType_BYIDS_ACCESS,//用户验证
        TCPCommandType_BYIDS_RESTARTSOFTWARE,//重启软件
        TCPCommandType_BYIDS_RESTARTSERVERS,//重启主机
        //音乐控制相关
        TCPCommandTypeUpdateMusicListMoren , //默认列表
        TCPCommandTypeUpdateCurrentMusicList ,//更新当前列表 **暂时废弃 5
        TCPCommandTypeUpdateMusicList, //其他模式的列表  **暂时废弃
        TCPCommandTypeUpdateCurrentMusicSong, //当前歌曲信息
        TCPCommandTypeUpdateMusicModel, //当前歌曲模式
        TCPCommandTypeChangeVolume, //改变声音
        TCPCommandTypePauseMusic ,   //暂停音乐 10
        TCPCommandTypeStartMusic,    //取消暂停
        TCPCommandTypeNextMusic,    //下一曲的命令
        TCPCommandTypePopMusic,//上一曲的命令
        TCPCommandTypeUpdateEffectRooms ,//更新当前受影响的音乐房间
        TCPCommandTypeUpdateEffectRoomSingleOne ,//更新当前受影响的音乐房间(单房控制) 15
        //闹钟相关
        TCPCommandTypeUpdateAlarmClockInfo,
        TCPCommandTypeDeleteAlarmClockInfo,
        TCPCommandTypeStopAlarmClockInfo,
        TCPCommandTypeLittleSleepAlarmClockInfo,
        TCPCommandTYpeUpdateAlarmClockInfoList ,//更新闹钟列表 20
        //系统信息 管家面板上 主机页面
        TCPCommandTypeUpdateSystemInfo,
        TCPCommandTypeStopSystemInfo,
        //提醒 信息,管家面板上提醒
        TCPCommandTypeUpdateRemindarInfo,
        TCPCommandTypeDeleteRemindarInfo,
        TCPCommandTypeUpdateGraphicsInfo , //25
        //离家模式
        TCPCommandType_HomeLeaveModel,
        //场景灯相关
        TCPCommandTypeUpdateEffectRoomLightWaveSingleOne ,//开关 灯光是否随音乐变化
        TCPCommandTypeUpdateEffectRoomChannalVolumnAdd,//声音+
        TCPCommandTypeUpdateEffectRoomChannalVolumnMinus,//声音-

        TCPCommandType_HomeBackModel ,//归家模式 30

        TCPCommandType_HomeRoomResetModel,//重启主机 在管家->主机里面

        TCPCommandTypeHeartBeat,

    }
    public enum commandTypeReturn{
        TCPCommandTypeHDL_BYIDS_SEND_Success ,//控制命令发送 -----0------
        TCPCommandType_BYIDS_ACCESS_Success,//用户验证
        TCPCommandType_BYIDS_RESTARTSOFTWARE_Success,//重启软件
        TCPCommandType_BYIDS_RESTARTSERVERS_Success,//重启主机

        TCPCommandTypeUpdateMusicListMorenSuccess, //默认列表
        TCPCommandTypeUpdateCurrentMusicListSuccess,//更新当前列表
        TCPCommandTypeUpdateMusicListSuccess, //其他模式的列表
        TCPCommandTypeUpdateCurrentMusicSongSuccess, //当前歌曲下标
        TCPCommandTypeUpdateMusicModelSuccess, //当前歌曲模式
        TCPCommandTypeChangeVolumeSuccess, //改变声音
        TCPCommandTypePauseMusicSuccess,   //暂停音乐
        TCPCommandTypeStartMusicSuccess,    //取消暂停
        TCPCommandTypeNextMusicSuccess,    //下一曲的命令
        TCPCommandTypePopMusicSuccess,//上一曲的命令
        TCPCommandTypeUpdateEffectRoomsSuccess,//更新当前受影响的音乐房间
        TCPCommandTypeUpdateEffectRoomSingleOneSuccess,//更新当前受影响的音乐房间(单房控制)

        TCPCommandTypeUpdateAlarmClockInfoSuccess,//成功更新闹钟信心
        TCPCommandTypeDeleteAlarmClockInfoSuccess,//删除闹钟成功
        TCPCommandTypeStopAlarmClockInfoSuccess,//停止闹钟成功
        TCPCommandTypeLittleSleepAlarmClockInfoSuccess,//小睡成功
        TCPCommandTYpeUpdateAlarmClockInfoListSuccess,//更新闹钟列表成功

        TCPCommandTypeUpdateSystemInfoSuccess,//更新系统信息成功
        TCPCommandTypeStopSystemInfoSuccess,//

        TCPCommandTypeUpdateRemindarInfoSuccess,
        TCPCommandTypeDeleteRemindarInfoSuccess,
        TCPCommandTypeUpdateGraphicsInfoSuccess,

        TCPCommandType_HomeLeaveModelSuccess,

        TCPCommandTypeUpdateEffectRoomLightWaveSingleOneSuccess,
        TCPCommandTypeUpdateEffectRoomChannalVolumnAddSuccess,
        TCPCommandTypeUpdateEffectRoomChannalVolumnMinusSuccess,

        TCPCommandType_HomeBackModelSuccess,

        TCPCommandType_HomeRoomResetModelSuccess,//

    }
    public enum MusicModel{
        ByidsMusicModelDANQU,//单曲循环
        ByidsMusicModelSHUNXU,//顺序循环
        ByidsMusicModelSUIJI//随机播放
    }
    public enum FamilyModel{
        ByidsFamilyModel_xiuxian ,//----0------
        ByidsFamilyModel_yule,
        ByidsFamilyModel_juhui ,
        ByidsFamilyModel_likai ,
    }
    public enum CurtainType{
        ByidsCurtainTypeBuLian ,//-----1 << 0
        ByidsCurtainTypeShaLian  ,//-----1 << 1
        ByidsCurtainTypeBaiyeLian ,//-----1 << 2
        ByidsCurtainTypeJuanLian   ,//-----1 << 3
    }
    public enum AirConditionControlSendType{
        ByidsAirConditionControlSendTypePower ,//------50---
        ByidsAirConditionControlSendTypeTemp ,//-------51---
        ByidsAirConditionControlSendTypeSpeed ,//-------52---
        ByidsAirConditionControlSendTypeModel ,//-------53---
    }
    public enum AirConditionType{
        ByidsAirConditionTypeIce ,//------12--
        ByidsAirConditionTypeHot ,//-----13
        ByidsAirConditionTypeWet ,//------10
        ByidsAirConditionTypeWind ,//----11
    }
    public enum AirConditionWindSpeedType{
        ByidsAirConditionWindSpeedLow ,//-----20
        ByidsAirConditionWindSpeedMidium,//----21
        ByidsAirConditionWindSpeedHigh,//------22
    }
}
