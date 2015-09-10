package com.yibu.kuaibu.utils;

/**
 * 常量接口类
 * @author 张界川
 *时间2015年9月9日上午10:24:06
 */
public class Constant {
	
	    //appid和appkey
		public static final String APPID="androidapp_kuaibu3";
		public static final String APPKEY="androidapp_kuaibu3_20150002";
		
		/**手机系统类型 android*/
		public static final String REGISTER_TYPE="3";
		
		//服务器地址
		
		//内网测试环境
		public static final String YB_SERVER= "http://192.168.1.119:8082/kuaibu-appServier/";

		//外网正式环境
		//public static final String YB_SERVER ="";
		
		
		/**提交登录密码的接口*/
		//public static final String COMMIT_PWD_URL="";
				
		//用户登录接口
		public static final String LOGIN_URL=YB_SERVER+"member/memberLogin";
		
		//用户注册接口
		public static final String REGISTER_URL=YB_SERVER+"member/memberRegister";
		
}
