package org.corenel.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.servlet.http.HttpServletResponse;


/**

 * @author 정수원
 */
public class CommonUtils {

	public static void buildFile(HttpServletResponse response, File file, FileInputStream fileIn, String fileLocalPath, String fileName, String ext, boolean useDelete) throws Exception {
		
		OutputStream os = response.getOutputStream();
		byte b[] = new byte[(int)file.length()];
		int leng = 0;
		
		while((leng = fileIn.read(b)) > 0){
			os.write(b,0,leng);
		}
		if(fileIn != null) fileIn.close();
		if(os != null) os.close();
		
		file = new File(fileLocalPath + fileName + ext);
		
		if(useDelete){
			if(file.exists()){
				file.delete();
			}
		}
	}
	
	
	public static String makeIdPrefixOfRandom(String mxIssueDate) {
		//TID 생성
		Random random = new Random();

		char[] ch = {'0','1','2','3','4','5','6','7','8','9'};
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < 4; i++){
			sb.append(ch[random.nextInt(ch.length)]);
		}
		String ran = sb.toString();
		String ranSeq = "_"+mxIssueDate+ran;
		return ranSeq;
	}
	
	public static ArrayList<Class<?>> getClassesForPackage(Package pkg) {
		
		String pkgName = pkg.getName();
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		File directory = null;
		String fullPath;
		String relPath = pkgName.replace('.', '/');
		System.out.println("ClassDiscovery: Package: " + pkgName + " becomes Path:" + relPath);
		URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
		System.out.println("ClassDiscovery: Resource = " + resource);
		if (resource == null) {
			throw new RuntimeException("No resource for " + relPath);
		}
		fullPath = resource.getFile();
		System.out.println("ClassDiscovery: FullPath = " + resource);

		try {
			directory = new File(resource.toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException( pkgName + " (" + resource + ") does not appear to be a valid URL / URI.  Strange, since we got it from the system...", e);
		} catch (IllegalArgumentException e) {
			directory = null;
		}
		System.out.println("ClassDiscovery: Directory = " + directory);

		if (directory != null && directory.exists()) {
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				if (files[i].endsWith(".class")) {
					String className = pkgName + '.' + files[i].substring(0, files[i].length() - 6);
					System.out.println("ClassDiscovery: className = " + className);
					try {
						classes.add(Class.forName(className));
					} catch (ClassNotFoundException e) {
						throw new RuntimeException(
								"ClassNotFoundException loading " + className);
					}
				}
			}
		} else {
			try {
				String jarPath = fullPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
				JarFile jarFile = new JarFile(jarPath);
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					String entryName = entry.getName();
					if (entryName.startsWith(relPath) && entryName.length() > (relPath.length() + "/" .length())) {
						System.out.println("ClassDiscovery: JarEntry: " + entryName);
						String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
						System.out.println("ClassDiscovery: className = " + className);
						try {
							classes.add(Class.forName(className));
						} catch (ClassNotFoundException e) {
							throw new RuntimeException( "ClassNotFoundException loading " + className);
						}
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(pkgName + " (" + directory + ") does not appear to be a valid package", e);
			}
		}
		return classes;
	}
}
