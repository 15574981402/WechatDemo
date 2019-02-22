package com.it.boot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
 
public class WeiXinUtil {  
	 private WeiXinUtil(){}
     private static String  token= "xxxx";
    
	 public static boolean defaultTokenSignature(String signature,
             String timestamp,String nonce){
		return checkSignature(token,signature,timestamp,nonce);
	 }
	  /**
      * 传入三个参数以及微信的token（静态自己设定）验证，
      * @param signature 签名用来核实最后的结果是否一致        
      * @param timestamp 时间标记
      * @param nonce 随机数字标记
      * @return 一个布尔值确定最后加密得到的是否与signature一致
      */
     public static boolean checkSignature(String token,String signature,
             String timestamp,String nonce){
		 // 认证
         //① 将token、timestamp、nonce三个参数进行字典序排序(有序数组)
         String[] arr=new String[]{token,timestamp,nonce};
         Arrays.sort(arr);
         //② 将三个参数字符串拼接成一个字符串进行sha1加密 
         StringBuilder sb=new StringBuilder();
         for (String param : arr) {
        	 sb.append(param);
		}
		 String content = sb.toString();
         String securityContent = shal(content);
		//③ 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
         return securityContent!=null?securityContent.equals(signature):false;
         
     }
	private static String shal(String content) {
         try {
        	//启动sha1加密法的工具
        	 MessageDigest digest = MessageDigest.getInstance("SHA-1");
			 digest.update(content.getBytes()); // 放入加密字符串
             byte[] digestContent = digest.digest(); // 加密
             StringBuilder sb=new StringBuilder();
             // byte转换16进制
 			for (byte b : digestContent) {
 				sb.append(String.format("%02x", b));
 			}
             return sb.toString(); // 返回加密后字符串
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
		return content;
	}
 }