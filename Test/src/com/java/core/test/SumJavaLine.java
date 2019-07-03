/**
 * @Title: Test1.java
 * @Description: 统计所有Java代码行数，需要手动添加代码库文件夹
 * @author: bryant
 * @date: 2019年7月2日 下午7:12:20
 * @version: v1.0
 */
package com.java.core.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

/**
 * @ClassName: Test1
 * @Description: 
 * @author: bryant
 *
 */
public class SumJavaLine {
	
	public static void main(String[] args) {
		//新建ArrayList，用于存放遍历出的Java文件
		ArrayList<File> list = new ArrayList<File>();
		//新建ArrayList, 用于存放需要遍历的，存放Java文件的文件夹
		ArrayList<File> files = new ArrayList<File>();
		File code = new File("D:\\code");
		File javaCore = new File("D:\\workspace\\JavaCore");
		File workSpace64 = new File("D:\\workspace_x64");
		files.add(code);
		files.add(javaCore);
		files.add(workSpace64);
		int total = 0;
		for(File f : files) {
			for(File jf : new SumJavaLine().getJava(f, list)) {
				System.out.println(jf);		// 遍历每个文件的绝对路径
			//	System.out.println(new FindJava().getJavaLine(jf));	//打印每个Java文件有多少行
				total+=new SumJavaLine().getJavaLine(jf);		
			}
		}
		System.out.println(total);
	}
	//根据文件夹名获取所有Java文件，返回一个ArrayList<File>
	public ArrayList<File> getJava(File file, ArrayList<File> list) {
		File[] files = file.listFiles();
		for(File f : files) {
			if(f.isDirectory()) {
				getJava(f,list);
			}
			else if(f.isFile() && f.getAbsolutePath().endsWith(".java")) {
				list.add(f);
				//输出Java文件路径名
				//System.out.println(f.getAbsolutePath());
			}
		}
		return list;
	}

	//获取所有Java文件的行数
	public int getJavaLine(File file) {
		//声明一个跟踪行号的字符读入流
		LineNumberReader lnr = null;
		int num = 0;
		String len = "";
		try {
			//用字符缓冲读入流包装字符读入流
			lnr = new LineNumberReader(new BufferedReader(new FileReader(file)));
			//每次读一行文件
			while(lnr.readLine() != null) {
				//每读一次，将更新num的值
				num = lnr.getLineNumber();	
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(lnr != null) {
				try {
					lnr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		return num;
	}
}
