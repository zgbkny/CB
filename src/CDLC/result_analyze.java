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
	//�ѹؼ����׷���map�У�key�ǵ���������value�Ǵ�0-1284�ı�� ��1285��
	public HashMap<String,Integer> EssProMap()   //EssProMap
	{
		try
		{
			int value=0;
				
			FileReader reader = new FileReader("Essential_protein_1285.txt");//���ļ�
		    BufferedReader br = new BufferedReader(reader);//���ļ����ݶ��뻺��
		      
		    String temp;
		   		    	
		    while((temp = br.readLine()) != null)//�����ݶ���map
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
	//�ѷǹؼ����׷���map�У�key�ǵ���������value�Ǵ�0-4393�ı�� ��4394��
	public HashMap<String,Integer> NotEssProMap()//EssProMap
	{
		try
		{
			int value=0;
				
			FileReader reader = new FileReader("NOT Essential_protein_4394.txt");//���ļ�
		    BufferedReader br = new BufferedReader(reader);//���ļ����ݶ��뻺��
		      
		    String tmp;
		   		    	
		    while((tmp = br.readLine()) != null)//�����ݶ���map
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


	//����ǰ100,200,300,400,500,600���������йؼ����׸���
	//br1:���ļ��ж�ȡ��ʵ��ؼ����׽��
	//EssMap����׼�ؼ����׽��
	public int[] getKeyProF(HashMap<String,Integer> EssMap)
	{

		int[] result = new int[6];
		String message="";
		for(int i=0;i<6;i++)
		{
			int times=0;//whileѭ������  �о�ǰ100��key����whileѭ��100��
			int count = 0;//ƥ�䵰���ʵĸ���			
			try{
			BufferedReader br1 = new BufferedReader(new FileReader(filePath));	
			while((message=br1.readLine())!=null&&(times<(i+1)*100))//5093���ݼ���*255��4746���ݼ���*237
			{				
				if(EssMap.containsKey(message))
				{
					count++;					
				}
				times++;				
			}

			
			System.out.println("ǰ"+(i+1)+"00���������йؼ����׸����ǣ�"+count);
			}catch(Exception ef){ef.printStackTrace();}
		}
	
			
		return null;
	}
	
	public static void main(String[] args)
	{
		
		result_analyze ra=new result_analyze();
		ra.EssProMap();
		ra.NotEssProMap();
		   
		//ȡ��ǰ100,200,300,400,500,600���������йؼ����׸���
		ra.getKeyProF(EssMap);
     
	}  
	
}  

