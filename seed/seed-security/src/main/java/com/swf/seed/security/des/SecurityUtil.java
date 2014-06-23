package com.swf.seed.security.des;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SecurityUtil {
	  private static final Log logger = LogFactory.getLog(SecurityUtil.class);
	  
	  public static String THREE_DES = "THREE_DES";
	  public static String NONE = "NONE";
	  public static String Three_deskey = "123456788765432112345678";
	  
	  public static void changeKey(String three_des){
		  SecurityUtil.Three_deskey = three_des;
		  logger.info("修改3deskey为："+three_des);
	  }
	  
	  public static boolean decrypt(String filename,String new_fileName){
		  byte[] keybytes = build3DesKey(Three_deskey);
		  
		  boolean result=false;
		  File file = new File(filename);
		  File newfile =  new File(new_fileName);
		  try {
			  FileOutputStream fo= new FileOutputStream(newfile);
			  FileInputStream fs = new FileInputStream(file);
			  try {
				  byte[] filedata = IOUtils.toByteArray(fs);
				  
				  byte[] decrypted_fildata = decryptBy3DES(filedata, keybytes);
				  IOUtils.write(decrypted_fildata,fo);
				  logger.info("使用3DES解密文件："+filename+"成功！解密后的文件为："+new_fileName);
				  result = true;
			  } finally {
				  try {
					  fo.flush();
					  fo.close();
					  fs.close();
				  } catch (IOException e) {
					  logger.error(e.getMessage());
				  }
			  }
		  } catch (IOException e) {
			  logger.error(e.getMessage());
		  }
		  return result;
	  }

	    /**
	     * 解密
	     * @param filename
	     * @param new_filename
	     * @return
	     */
	    public static boolean encrypt(String filename,String new_fileName) {
	        byte[] keybytes = build3DesKey(Three_deskey);

	        boolean result = false;
	        File file = new File(filename);
	        File newfile = new File(new_fileName);
	        try {
	            FileOutputStream fo = new FileOutputStream(newfile);
	            FileInputStream fs = new FileInputStream(file);
	            try {
	                byte[] filedata = IOUtils.toByteArray(fs);

	                byte[] encrypted_fildata = encryptBy3DES(filedata, keybytes);

	                IOUtils.write(encrypted_fildata, fo);
	                logger.info("使用3DES加密文件："+filename+"成功！加密后的文件为："+new_fileName);
	                result = true;
	            } finally {
	                try {
	                    fo.close();
	                } catch (IOException e) {
	                    logger.error(e.getMessage());
	                }
	                try {
	                    fs.close();
	                } catch (IOException e) {
	                    logger.error(e.getMessage());
	                }
	            }
	        } catch (IOException e) {
	            logger.error(e.getMessage());
	        }
	        return result;
	    }
	  
	    /*
	     * 根据字符串生成密钥字节数组 
	     * @param keyStr 密钥字符串
	     * @return 
	     * @throws UnsupportedEncodingException
	     */
	    public static byte[] build3DesKey(String keyStr) {
	        byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
	        byte[] temp = null;
			try {
				temp = keyStr.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				keyStr.getBytes();
			}    //将字符串转成字节数组
	        
	        /*
	         * 执行数组拷贝
	         * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
	         */
	        if(key.length > temp.length){
	            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
	            System.arraycopy(temp, 0, key, 0, temp.length);
	        }else{
	            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
	            System.arraycopy(temp, 0, key, 0, key.length);
	        }
	        return key;
	    } 
	    
	    
	    public static byte[] encryptBy3DES(byte[] plainText, byte[] byteKey) {
	        try {
	            SecretKeySpec key = new SecretKeySpec(byteKey, "DESede");

	            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
	            cipher.init(Cipher.ENCRYPT_MODE, key);

	            return cipher.doFinal(plainText);
	        } catch (Exception ex) {
	            logger.error(ex.getMessage());
	        }
	        return null;
	    }

	    public static byte[] decryptBy3DES(byte[] plainText, byte[] byteKey) {
	        try {
	            SecretKeySpec key = new SecretKeySpec(byteKey, "DESede");

	            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
	            cipher.init(Cipher.DECRYPT_MODE, key);

	            return cipher.doFinal(plainText);
	        } catch (Exception ex) {
	            logger.error(ex.getMessage());
	        }
	        return null;
	    }
}