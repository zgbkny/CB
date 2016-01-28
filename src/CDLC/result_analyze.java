package CDLC;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class result_analyze
{
	//input
	private static String filePath = "result\\final_output\\ranked_proteins.txt";
	
	
	public static HashMap<String,Integer> EssMap = new HashMap<String,Integer>();
	public static HashMap<String,Integer> NotEssMap = new HashMap<String,Integer>();
	//把关键蛋白放入map中，key是蛋白质名，value是从0-1284的编号 共1285个
	public HashMap<String,Integer> EssProMap()   //EssProMap
	{
		try
		{
			int value=0;
				
			FileReader reader = new FileReader("Essential_protein_1285.txt");//读文件
		    BufferedReader br = new BufferedReader(reader);//将文件内容读入缓存
		      
		    String temp;
		   		    	
		    while((temp = br.readLine()) != null)//将数据读入map
		      {
		    	  if(EssMap.containsKey(temp) == false)
		    	  {
		    		  EssMap.put(temp, value);
		    		  value++;
		    	  }  	  		    	  
		      }
		    return EssMap;
		 }catch(IOException e)
		 {
			 System.err.println("Something is wrong with the input file, please check it!");
		     e.getMessage();
		 }
		  return null;
	  }
	//把非关键蛋白放入map中，key是蛋白质名，value是从0-4393的编号 共4394个
	public HashMap<String,Integer> NotEssProMap()//EssProMap
	{
		try
		{
			int value=0;
				
			FileReader reader = new FileReader("NOT Essential_protein_4394.txt");//读文件
		    BufferedReader br = new BufferedReader(reader);//将文件内容读入缓存
		      
		    String tmp;
		   		    	
		    while((tmp = br.readLine()) != null)//将数据读入map
		      {
		    	  if(NotEssMap.containsKey(tmp) == false)
		    	  {
		    		  NotEssMap.put(tmp, value);
		    		  value++;
		    	  }  	  		    	  
		       }
		    return NotEssMap;
		 }catch(IOException e)
		 {
			 System.err.println("Something is wrong with the input file, please check it!");
			 e.getMessage();
		 }
		  return null;
	  }


	//计算前100,200,300,400,500,600个蛋白质中关键蛋白个数
	//br1:从文件中读取的实验关键蛋白结果
	//EssMap：标准关键蛋白结果
	public int[] getKeyProF(HashMap<String,Integer> EssMap)
	{

		int[] result = new int[6];
		String message="";
		for(int i=0;i<6;i++)
		{
			int times=0;//while循环次数  研究前100个key蛋白while循环100次
			int count = 0;//匹配蛋白质的个数			
			try{
			BufferedReader br1 = new BufferedReader(new FileReader(filePath));	
			while((message=br1.readLine())!=null&&(times<(i+1)*100))//5093数据集是*255，4746数据集是*237
			{				
				if(EssMap.containsKey(message))
				{
					count++;					
				}
				times++;				
			}

			
			System.out.println("前"+(i+1)+"00个蛋白质中关键蛋白个数是："+count);
			}catch(Exception ef){ef.printStackTrace();}
		}
	
			
		return null;
	}
	
	public static void main(String[] args)
	{
		
		result_analyze ra=new result_analyze();
		ra.EssProMap();
		ra.NotEssProMap();
		   
		//取得前100,200,300,400,500,600个蛋白质中关键蛋白个数
		ra.getKeyProF(EssMap);
     
	}  
	
}  

