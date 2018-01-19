package com.sxm.mobile.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;



public class Logfiledata {
	String filepath = "src/test/resources";
	public String FILE_DIR = null;
	public String FILE_TEXT_EXT = ".log";
	String temp = null;
	StringBuilder log = new StringBuilder();

	public void readfromMobile() {

		filepath = System.getProperty("user.dir") + "/" + filepath;
		FILE_DIR = "D:/SiriusXMAndroidAutomation/SiriusXMAndroidAutomation/src/test/resources";
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		String command = String.format("adb pull /storage/emulated/legacy/Android/data/com.sirius/files D:/SiriusXMAndroidAutomation/SiriusXMAndroidAutomation/src/test/resources");
		Process process = null;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			process = Runtime.getRuntime().exec(command);
			System.out.println("after adb command");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("before listFile");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		listFile(FILE_DIR, FILE_TEXT_EXT);
		BufferedReader reader = null;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	
		Pattern patt = Pattern.compile("Chunk time (\\d)+");
		try {
			System.out.println("temp"+temp);
			reader = new BufferedReader(new FileReader(temp));
			
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		String line;
		try {
			ArrayList<String> ar = new ArrayList<String>();
			while ((line = reader.readLine()) != null) {

				Matcher m = patt.matcher(line);
          
				while (m.find()) {
				//	System.out.println("aa" + m.group(0));
					int start = m.start(0);
					int end = m.end(0);
					System.out.println("Matched content       "
							+ line.substring(start, end));
				/*	String s = line.substring(start, end).replace(
							"BIIssue : Curr chunk time ", "");*/
					String s = line.substring(start, end).replace(
							"Chunk time ", "");
					
					ar.add(s);
					
					if (ar != null && !ar.isEmpty()) {
						String a = ar.get(ar.size() - 1);
						System.out.println("last date " + a);
						DateFormat df = new SimpleDateFormat("HH:mm:ss");
						String formatted = df.format(Long.valueOf(a));
						System.out.println("time" + formatted);

					}
				}
			}
			System.out.println("array size    " +ar.size());
			if(ar.size()>=1){				
				
			}
			else{
				
				Assert.assertTrue(false,"Audio chunk not found");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void listFile(String folder, String ext) {

		GenericExtFilter filter = new GenericExtFilter(ext);

		File dir = new File(folder);

		if (dir.isDirectory() == false) {
			System.out.println("Directory does not exists : " + FILE_DIR);
			return;
		}

		// list out all the file name and filter by the extension
		String[] list = dir.list(filter);

		if (list.length == 0) {
			System.out.println("no files end with : " + ext);
			return;
		}

		for (String file : list) {
			temp = new StringBuffer(FILE_DIR).append(File.separator)
					.append(file).toString();
			System.out.println("file : " + temp);
		}
	}

	// inner class, generic extension filter
	public class GenericExtFilter implements FilenameFilter {

		private String ext;

		public GenericExtFilter(String ext) {
			this.ext = ext;
		}

		public boolean accept(File dir, String name) {
			return (name.endsWith(ext));
		}
	}

	public void deletefilesfrommobile() {
		String command = String
				.format("adb shell rm /storage/emulated/legacy/Android/data/com.sirius/files/*.log");
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String path = filepath;
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			System.out.println("file"  + f.getAbsolutePath() );
			if (f.isFile() && f.exists()) {
				f.delete();
				System.out.println("successfully deleted");
			} else {
				System.out.println("cant delete a file due to open or error");
			}
		}

		
		
	}
	
	public void retrieveLogs(String Command,long time) {
		
		// List<String> list=new ArrayList<>();
		try {
			Process process = Runtime.getRuntime().exec(Command);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			Thread.sleep(time);
			//process.destroy();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				log.append(line);

			}

		} catch (Exception e) {
		}

		

		

	}
	
	public void readDate(String text){
		System.out.println(log.toString());
		if (log.toString().contains(text)) {

			// found.
		} else {
			Assert.assertTrue(false, text + "not displayed");
		}
		
	}
	
	public void closeStringBuilder(){
		log.setLength(0);
		System.out.println("log is set to zero" + log.toString());
		
	}
	
}
