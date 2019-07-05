/**
 * @Title: PropertiesDemo.java
 * @Description: 实现对properties文件的写出和读入
 * @author: bryant
 * @date: 2019年7月5日 下午4:45:06
 * @version: v1.0
 */
package com.java.core.io.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName: PropertiesDemo
 * @Description: Properties文件底层为hashTable，通过键值对赋值
 * @author: bryant
 *
 */
public class PropertiesDemo {
	
	public static void main(String[] args) {
		//先写再读
		new PropertiesDemo().writeProperties();
		//读
		new PropertiesDemo().readProperties();
		
	}
	//properties文件的写入
	public void writeProperties() {
		//声明properties文件的写出流
		OutputStream out = null;
		try {
			//创建写出流对象，File对象创建需要完整文件路径
			out = new FileOutputStream(new File("D:\\workspace_x64\\GitRepository\\JavaCore\\Test\\src\\com\\java\\core\\io\\properties\\User.properties"));
			//创建Properties对象
			Properties p = new Properties();
			//将示例数据写入内存中
			p.setProperty("name", "admin");
			p.setProperty("password", "root");
			p.setProperty("URL", "jdbc:oracle:thin:@127.0.0.1");
			p.setProperty("DRIVERNAME", "oracle.jdbc.OracleDriver");
			//将数据持久化
			p.store(out, "bryant\t"+ (new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()).toString()));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//关闭流
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	} 
	//Properties文件的读取
	public void readProperties() {
		//利用ClassLoader的getResourceAsStream()方法获得写入流，文件如果放在包里，需要写包的文件夹层，放在项目src根目录下直接写文件名就行
		InputStream in = PropertiesDemo.class.getClassLoader().getResourceAsStream("com\\java\\core\\io\\properties\\User.properties");
		//创建properties对象
		Properties p = new Properties();
		try {
			//将输入流加载到P对象里
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		//测试查询名字
		System.out.println(p.getProperty("name"));
	}
}
