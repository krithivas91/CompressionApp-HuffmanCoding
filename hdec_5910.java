//SWAMINATHAN  KRITHIVAS 		PP 		CS610	5910

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class hdec_5910 {
	static HashMap<String, Byte> CodetoSymb2 = new HashMap<String,Byte>();

	public static void main(String[] args) throws IOException
	{
		try
		{
		String sourcepath = args[0];
		File file = new File(sourcepath);
		recreateMap_5910(file);	
		System.out.println("File created..");
		file.delete();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Please give proper .huf file..");
			
		}
		catch (ArrayIndexOutOfBoundsException a) 
		{
			System.out.println("Please enter file name");
		}
	
		
	}
	public static void fileEmpty_5910(File f) throws IOException
	{
		
		String str = f.toString();
		str = str.substring(0, str.length()-4);
		BufferedOutputStream ewrite = new BufferedOutputStream(new FileOutputStream(str));
		ewrite.close();
		f.delete();
		System.exit(0);
	}

	public static void recreateMap_5910(File f) throws IOException
	{
		
		BufferedReader b = new BufferedReader(new FileReader(f));
		if(f.length()==0)
		{
			fileEmpty_5910(f);	
		}
		String mapp =b.readLine();
		
		String arr[] =mapp.split(" ");
		for (int i=0; i<arr.length;i++)
		{
			String regx= arr[i];
			String a[] = regx.split("=");
			CodetoSymb2.put(a[0],Byte.parseByte(a[1]));
		}
		int freq =Integer.parseInt(b.readLine());
		b.close();
		reformTextfile_5910(CodetoSymb2, f,freq);
	}

	public static void reformTextfile_5910(HashMap<String, Byte> map, File f, int freq) throws IOException
	{
		
		int frequencyCounter=0;
		StringBuilder buildCode = new StringBuilder(); 
		String check ="";
		BufferedInputStream b1 = new BufferedInputStream(new FileInputStream(f));
		String str = f.toString();
		str = str.substring(0, str.length()-4);
		BufferedOutputStream bwrite = new BufferedOutputStream(new FileOutputStream(str));
		while(b1.read()!='\n');
		while(b1.read()!='\n');
		int x = 0;
		while((x=b1.read())!= -1)
		{

			String formStr = Integer.toBinaryString(x);
			String format = ("00000000"+formStr).substring(formStr.length());
			buildCode.append(format);
			}
			
		for (int i=0 ; i< buildCode.length();i++)
		{
			check = check + buildCode.charAt(i);
			if(frequencyCounter<freq && map.containsKey(check))
			{
				bwrite.write(map.get(check));
				check="";
				frequencyCounter++;
			}	
		}
			b1.close();
			bwrite.close();
}
}