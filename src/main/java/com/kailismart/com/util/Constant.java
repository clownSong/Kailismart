package com.kailismart.com.util;

/**
 * Created by Administrator on 2016-08-15.
 */
public class Constant {
    /**
     * 采购申请单状态
     */
    public final static int STATE_NO_APPLY = 0;    //未采购
    public final static int STATE_PART_APPLY = 1;    //部分采购
    public final static int STATE_ALL_APPLY = 2;    //完全采购

    /**
     * 申请单申请数量和已采购数量
     */
    public final static String APPLY_SUM_KEY = "applySum";   //申请单申请数量Key
    public final static String APPLY_YSUM_KEY = "ySum";   //申请单材料已采购数量key

    /**
     * 主表id
     */
    public final static String FRAME_COLUMN = "pm01301";                //采购订单主表id

    /**
     * 窗体代号
     */
    public final static String FRAME_CODING_PROCUREMENT = "15306";      //采购订单窗体代号

    /**
     * 兼容pm2原装程序的sql语句，作用应该是pm2所用，与本程序无关，只为我这边添加的单子兼容pm2打开
     */
    public final static String PM2SQL_15306 = "Select * From ( \n" +
            "select  b.*,\n" +
            "pf00302=isnull(pf00302,''),\n" +
            "pf00343=isnull(pf00343,''),pd00452=isnull(pd00452,''),\n" +
            "pd00402=isnull(pd00402,''),\n" +
            "pj00402=isnull(pj00402,''),pm01902=isnull(pm01902,''),\n" +
            "pa00101=IsNull(pa00101,''), \n" +
            "pa00102=IsNull(pa00102,''),pa00140=IsNull(pa00140,''),\n" +
            "pm02603=isnull((select top 1 pm02603 from sdpm026 where\n" +
            "pm02612=pm01301),''),\n" +
            "YW_pm01303=isnull((select top 1 a.pm01303 from sdpm013\n" +
            "a where a.pm01301=b.pm01319),''),\n" +
            "pf00308,pf00309,pf00310,pf00345,pm01907,pm01908,pm01909,\n" +
            "/*发起OA流程的审批状态*/\n" +
            "OAState=isnull((select top 1 po00308 from sdpo003 where po00307=pm01301),0),\n" +
            "OwerAtt=case when exists( select top 1 pm013fj01 from sdpm013fj \n" +
            "where pm013fj01=pm01301) then 1 else 0 end,\n" +
            "CheckMan=Isnull((select top 1 pj00402 from sdpj004 where pj00421=pm01313),''),\n" +
            "BuildMan=Isnull((select top 1 pj00402 from sdpj004 where pj00421=pm01310),''),\n" +
            "pm01415Sum=isnull((select sum(pm01415) from sdpm014 where pm01401=pm01301),0)\n" +
            "from sdpm013 b  \n" +
            "left join sdpj004 on b.pm01320 = pj00401\n" +
            "left join sdpf003 on b.pm01304 = pf00301\n" +
            "left join sdpd004 on pm01305 = pd00401\n" +
            "Left Join sdpd018 on pd00401=pd01801\n" +
            "Left Join sdpa001 on pa00101=pd01802\n" +
            "left join sdpm019 on pm01306 = pm01901 \n" +
            ")#sdpm013 Where 1=1 ";

    /**
     * 审批人员类型常亮
     */
    public final static int APPROVE_SECTION = 0;    //部门
    public final static int APPROVE_ZHIWU = 1;      //职务
    public final static int APPROVE_PERSON = 2;     //人员
    public final static int APPROVE_JS = 3;         //角色
    public final static int APPROVE_LEADER = 4;     //上级领导
    public final static int APPROVE_SECTION_LEADER = 5;    //部门主管
    public final static int APPROVE_FAQIREN = 6;          //发起人

    /**
     * 用户session Key
     */
    public final static String SESSION_KEY = "user";
    /**
     * 订单入库状态
     */
    public final static byte STATE_0 = 0;   //未入库
    public final static byte STATE_3 = 3;   //部分入库
    public final static byte STATE_4 = 4;   //完全入库
    public final static byte STATE_1 = 1;
    public final static byte STATE_2 = 2;

    /**
     * 采购订单材料总数key
     */
    public final static String YSUM = "ySum";
    public final static String PUTSUM = "putSum";

}
