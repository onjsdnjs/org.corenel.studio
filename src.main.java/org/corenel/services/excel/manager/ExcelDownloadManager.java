package org.corenel.services.excel.manager;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.corenel.services.excel.handler.ExcelDownloadHandler;

public class ExcelDownloadManager {
	
	private static ExcelDownloadManager instance;
	
	
	private ExcelDownloadManager(){
	}
	
	public static ExcelDownloadManager getInstance(){ 
		if(instance  == null){ 
			instance  = new ExcelDownloadManager();
		}
		return instance  ;
	}

	public void makeTargetExcel(File tempXmlFile, String sheetRef, String tempPath, String targetPath) throws IOException {
		
        FileOutputStream targetExcelOS = null;
        File tmpExcelFile = new File(tempPath);
        try{
        	targetExcelOS = new FileOutputStream(targetPath);
            substitute(tmpExcelFile, tempXmlFile, sheetRef.substring(1), targetExcelOS);
        }finally{
            targetExcelOS.close();
            if(tmpExcelFile.exists()) tmpExcelFile.delete();
            if(tempXmlFile.exists()) tempXmlFile.delete();
        }       
	}
	
	private void substitute(File zipfile, File tmpfile, String entry, OutputStream out) throws IOException{
		ZipFile zip = new ZipFile(zipfile);
		ZipOutputStream zos = new ZipOutputStream(out);
		InputStream is2 = new FileInputStream(tmpfile);
		try{
			@SuppressWarnings("unchecked")
			Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
			while(en.hasMoreElements()){
				ZipEntry ze = en.nextElement();
				if(!ze.getName().equals(entry)){
					zos.putNextEntry(new ZipEntry(ze.getName()));
					InputStream is = zip.getInputStream(ze);
					copyStream(is, zos);
					is.close();
				}
			}
			
			zos.putNextEntry(new ZipEntry(entry));
			copyStream(is2, zos);
		}finally{
			is2.close();
			zos.close();
			zip.close();
		}
	}
	
	private void copyStream(InputStream in, OutputStream out) throws IOException{
		byte[] chunk = new byte[1024];
		int count;
		while((count = in.read(chunk)) >=0 ){
			out.write(chunk,0,count);
		}
	}

}
