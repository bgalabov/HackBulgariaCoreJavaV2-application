import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class DirectoryManager {
	private static HashMap<String, String> files=new HashMap<String, String>();
	
	/* The idea is we calculate the checksum for each file in the directory
	 and then we check if there is a value in the HashMap corresponding on the
	 calculated checksum (the checksum is the key) and if there is, we ignore this file.
	 If the checksum is unique for the hashmap, we put a record in the hashmap and the file name
	 in the ArrayList which is returned by the listDuplicatingFiles(dir) method.
	 There is a test directory - ".\\aaab" .*/
	 
	public static ArrayList<String> listDuplicatingFiles(File dir){
		File folder=new File(dir.toString());
		File[] rootFiles=folder.listFiles();
		ArrayList<String> nonDuplicatingFiles=new ArrayList<String>();
		
		for(File rootFile : rootFiles){
			if(rootFile.isDirectory()){
				nonDuplicatingFiles.addAll(listDuplicatingFiles(rootFile));
			}
			else{
				try {
					if(files.get(getMD5Checksum(rootFile.toString()))==null){
						files.put(getMD5Checksum(rootFile.toString()), rootFile.toString());
						nonDuplicatingFiles.add(rootFile.getName().toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return nonDuplicatingFiles;
	}
	
	private static byte[] createChecksum(String filename) throws Exception {
	       InputStream fis =  new FileInputStream(filename);

	       byte[] buffer = new byte[1024];
	       MessageDigest complete = MessageDigest.getInstance("MD5");
	       int numRead;

	       do {
	           numRead = fis.read(buffer);
	           if (numRead > 0) {
	               complete.update(buffer, 0, numRead);
	           }
	       } while (numRead != -1);

	       fis.close();
	       return complete.digest();
	   }
	
	public static String getMD5Checksum(String filename) throws Exception {
	       byte[] b = createChecksum(filename);
	       String result = "";

	       for (int i=0; i < b.length; i++) {
	           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
	       }
	       return result;
	   }
	
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		String path;
		
		System.out.println("Enter a full path to a directory: ");
		path=in.nextLine();
		
		ArrayList<String> files=listDuplicatingFiles(new File(path));
		for(String file : files){
			System.out.println(file);
		}
		
	}

}
