//SWAMINATHAN  KRITHIVAS 		PP 		CS610	5910

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class henc_5910 {
	
	static HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
	static HashMap<Integer, String> symboltoCode = new HashMap<Integer,String>();
	static HashMap<String, Integer> CodetoSymb = new HashMap<String,Integer>();
	static PriorityQueue<Node_5910> priorityQ;
	static int nodeCount = 0;
	public static void main(String[] args) throws IOException
	{
			 
		try
		{
		String sourcepath = args[0];
		File file = new File(sourcepath);
		compress_5910(file);
		System.out.println("Compression done..");
		file.delete();
		System.out.println(file+".huf created..");
		}
		catch (Exception e)
		{
			System.out.println("Please enter proper filename..");
			return;
		}
	}
	public static void fileEmpty_5910(File f) throws IOException
	{
		String str = f+".huf";
		BufferedOutputStream ewrite = new BufferedOutputStream(new FileOutputStream(str));
		ewrite.close();
		f.delete();
		System.exit(0);
	}
	
	public static void compress_5910(File file) throws IOException
	{
		byte[] byteStream= displayByte_5910(file);
		buildTree_5910(map);
		int f =setPrefixcodes_5910();
		Node_5910 node = priorityQ.peek();
		genPrefixcodes_5910(node ,"");
		mapCodes_5910(node);
		realCompress_5910(byteStream,file,f);	
	}
	
	public static void realCompress_5910(byte[] byteStream,File file, int freq) throws IOException
	{	
		String st="";
		StringBuilder comp = new StringBuilder();
		StringBuilder code = new StringBuilder();
		String test = CodetoSymb.toString();
		test=test.replace(", "," ");
		test= test.substring(1, test.length()-1);
		comp.append(test);
		comp.append('\n');
		comp.append(freq);
		comp.append('\n');
		BufferedOutputStream br = null;
		for(int i =0; i<byteStream.length;i++)
		{
		 int data = byteStream[i];
		code.append(symboltoCode.get(data));
		}
		
		br = new BufferedOutputStream(new FileOutputStream(file.toString()+".huf"));
		for(int j=0;j<comp.length();j++)
		{
			char a = comp.charAt(j);
			br.write(a);
			
		}
		for(int k =0; k<code.length();k++)
		{
			 st=st+code.charAt(k);
					
			if(st.length()==8)
			{
				int c = Integer.parseInt(st,2);
				//System.out.println(st+" "+c);
				br.write(c);
				st="";	
			}
		}
		if(st!="")
		{
			String formatted = (st+"00000000").substring(0,8);
			int format= Integer.parseInt(formatted,2);
			br.write(format);

		}
		br.close();
	}
	
	public static void mapCodes_5910(Node_5910 node)
	{
		if(node.left!=null)
		{
			mapCodes_5910(node.left);
		}
		if(node.right!=null)
		{
			mapCodes_5910(node.right);
		}
		if (node.left ==null && node.right == null)
		{
			symboltoCode.put(node.symbol, node.code);
			CodetoSymb.put(node.code, node.symbol);
		}		
	}
	
	public static void genPrefixcodes_5910(Node_5910 node, String codes)
	{
		if(node.left!=null)
		{
			node.left.code=node.code+"0";
			genPrefixcodes_5910(node.left, node.left.code);
			node.right.code=node.code+"1";
			genPrefixcodes_5910(node.right, node.right.code);
		}
	}
	
	
	public static int setPrefixcodes_5910()
	{
	
		for (int w=0;w<nodeCount-1;w++)
		{
		Node_5910 parent = new Node_5910(0);
		Node_5910 leftNode = priorityQ.poll();
		Node_5910 rightNode = priorityQ.poll();
		parent.setLeft_5910(leftNode);
		parent.setRight_5910(rightNode);
		parent.frequency = leftNode.getFrequency_5910()+ rightNode.getFrequency_5910();
		parent.symbol = leftNode.getSymbol_5910() + rightNode.getSymbol_5910();
		leftNode.code="0";
		rightNode.code="1";
		priorityQ.add(parent);
		}	
		Node_5910 freq = priorityQ.peek();
		int finalfreq = freq.getFrequency_5910();
		return finalfreq;
	}
	
	public static void getFreq_5910(byte[] bye,File file) throws IOException
	{
		if(file.length()==0)
		{
			fileEmpty_5910(file);
			
			
		}
		for (int i=0; i<bye.length;i++)
		{
			int value = bye[i];

			if (!map.containsKey(value))
			map.put(value,1);
			else
			map.put(value,map.get(value)+1);
			}		
	}
	
	public static void buildTree_5910(HashMap<Integer,Integer> map)
	{
		priorityQ = new PriorityQueue<Node_5910>();
	for (int i : map.keySet())
	{
		Node_5910 e = new Node_5910(i,map.get(i));
		nodeCount++;
		priorityQ.add(e);

	}
	}
	
	public static byte[] displayByte_5910(File file)
	{
		FileInputStream fis = null;
		int i = (int) file.length();
		byte[] bye = new byte[i];
		try
		{
			fis = new FileInputStream(file);
			fis.read(bye);
			getFreq_5910(bye,file);
			
		}
			catch (Exception e) {
				System.out.println("File Not found in the path..");
			}
		return bye;		
	}
}
