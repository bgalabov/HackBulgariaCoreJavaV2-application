import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class DirectoryManager {
	private static HashMap<String, String> files=new HashMap<String, String>();
	
	
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
