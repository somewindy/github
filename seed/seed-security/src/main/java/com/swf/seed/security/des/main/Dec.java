package com.swf.seed.security.des.main;

import java.io.File;
import java.util.Scanner;

import com.swf.seed.security.des.SecurityUtil;
import com.swf.seed.util.PropUtil;

public class Dec {
	public static void main(String[] args) {
		SecurityUtil.changeKey(PropUtil.getPropValue("3deskey"));
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入需要解密文件名：");
		String fileName = scanner.next();
		File file = new File(fileName);
		if (file.exists()){
			SecurityUtil.decrypt(fileName, new File(file.getParent(),"dec_"+file.getName()).getAbsolutePath());
		}else{
			System.out.println("文件不存在："+file.getAbsolutePath());
		}
	}
}
