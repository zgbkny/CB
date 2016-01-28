package CDLC;

import java.io.File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.io.FileWriter;


public class produce_subnetworks {
	//input
	private static String PPInetwork="input_data\\DIP_24743.txt";
	private static String expression_data="input_data\\expression_value_7079.xls";
	double threshold=0.8;//每个蛋白质分别对应自己的活性阈值,阈值取1时，下面与阈值比较的地方要改为大于等于
    int num=12;//设为12
    
    //output
	private static String finalpath="Result\\subnetworks\\network_";
	


	private ArrayList<BufferedReader> brList;	
	public void activethreshold()
	{
		
		File file = new File(expression_data); //总的基因表达数据
		InputStream in;//新建一个输入流
		
		//WritableSheet sheet2;
		
		Workbook workbook;//新建一个excel工作薄
		Sheet sheet;//新建一个工作表
		Cell  c0;//新建一个单元格
		Cell[] ctimes;//新建一个单元格数组（转录因子）
		Cell[] cgene;//新建一个单元格数组（基因）
		ArrayList<String> times ;//新建一个字符串链表，用于存放时间点那一行（已删）
		ArrayList<String> gene;//新建一个字符串链表，用于存放基因那一列
        double[][] expMatrix;//新建一个浮点数矩阵，用于存放表达值
        double[][] unifexpMatrix;//新建一个浮点数矩阵，用于存放归一化的表达值
        double[][] expMatrix12;//36个时间点分为3个周期，每个周期12个时间点，计算3个周期对应时间点的3个表达值的平均值作为那个时间点的表达值
        double[][] activeMatrix;//活性矩阵，其中的所有值为0或者1，如果基因i在j时刻的表达值大于阈值，则对于的activeMatrix[i][j]的值为1，反之为0
	    double[] max,min;
        
		
	    try {
				
				FileReader fos2 = new FileReader(PPInetwork);   
				BufferedReader br2 = new BufferedReader(fos2);
				
				ArrayList<String[]> list2 = new ArrayList();
				String temp2=null;
				while((temp2=br2.readLine())!=null){
				    String[] protein = temp2.split(",");
				    list2.add(protein);
				}
				br2.close();
				
				
				brList = new ArrayList<BufferedReader>();	   	    
			    FileReader fis = new FileReader(PPInetwork);
				BufferedReader fr = new BufferedReader(fis);
				brList.add(fr);		   
			    
			    //找出PPI网络中包含的蛋白质
				ArrayList<String> namelist = new ArrayList();
				for(int i=0;i<brList.size();i++)
			    {
					String temp;
					while((temp=brList.get(i).readLine())!=null)
					{
					    String[] proInfo = temp.split(",");
						//判断该蛋白质是否已存在于proList中,
						//-1表示队列中没有该蛋白质，正整数表示蛋白质在队列中的位置
						int result1 = getIndexFromList(namelist,proInfo[0]);
						int result2 = getIndexFromList(namelist,proInfo[1]);
						if(result1==-1)
						{
							namelist.add(proInfo[0]);
						}
						   
						if(result2==-1)
						{
						    namelist.add(proInfo[1]);
						}
					}
				}
//				System.out.println("该PPI网络中包含 "+namelist.size()+" 个蛋白质");   
				
				
				in = new FileInputStream(file);//将目标excel文件赋给输入流in
				workbook = Workbook.getWorkbook(in); //读excel文件对应的工作薄 
				sheet = workbook.getSheet(0); //读excel文件对应的工作薄 中的第一个sheet				
			    ctimes = sheet.getRow(0);//将sheet中的第1行赋值给ctimes，即用于存放转录因子的字符串链表
			    times = new ArrayList<String>();//用于存储读取到的转录因子名字
			    cgene = sheet.getColumn(0);//将sheet中的第一列赋值给cgene，即用于存放基因的字符串链表
			    gene = new ArrayList<String>();//用于存储读取到的基因名字

	   	   	    
	   	   	    int temp11=0,temp22=0,temp33=0;//1才用这些变量
	   	   	    int[] index1;
	   	   	    int[] index2;
			    
	          
			    for(int i = 0; i < ctimes.length;i+=1)
			    {
			    	times.add(ctimes[i].getContents());
			    }
			    for(int i = 0; i < cgene.length;i++)
			    {
			    	gene.add(cgene[i].getContents());
			    }
			    
			    
			    
			    //找出DIP和表达谱中共有的蛋白质
			    ArrayList<String> common_list = new ArrayList();
			    for(int i=0; i<namelist.size(); i++)
			    {
			    	for(int j=0; j<gene.size(); j++)
			        {
			    	    if(namelist.get(i).equals(gene.get(j)))
			    	    {
			    	    	common_list.add(namelist.get(i));
			    	    	break;
			    	    }
			        }
			    }
			    
			    int[] index3;
				index3 = new int[common_list.size()];
				
				for(int i=0; i<common_list.size(); i++)
			    {
			    	for(int j=0; j<gene.size(); j++)
			        {
			    	    if(common_list.get(i).equals(gene.get(j)))
			    	    {
			    	    	index3[i]=j;
			    	    	break;
			    	    }
			        }
			    }

			    
			    
			    //将绑定数据存入数组中
			    expMatrix = new double[common_list.size()][36];//创建存放表达值的矩阵，行数为基因个数，列数为时间点个数
			    unifexpMatrix = new double[common_list.size()][num];
			    expMatrix12 = new double[common_list.size()][num];//1才用
			    activeMatrix = new double[common_list.size()][num];//创建存放pcc的矩阵，行数为基因个数，列数为时间点个数
			    max=new double[common_list.size()];
			    min=new double[common_list.size()];
			    index1 = new int[list2.size()];
			    index2 = new int[list2.size()];
			    
			    		
			    
			    for(int i = 0; i< common_list.size();i++)
		    	{
			         for(int j = 1; j < times.size();j++)
				     {			    	
			    		    Cell c = sheet.getCell(j,index3[i]);//这里要注意Excel中查询的方式，第一个为列，第二个参数为行.先定位单元格位置
			    		    expMatrix[i][j-1] = Double.parseDouble(c.getContents());//再读取该单元格中的内容
			    	 }
		    	}
			    
			    //初始化expMatrix12数组和活性矩阵4981*12，以及归一化表达矩阵
			    for(int i=0; i<common_list.size(); i++)
			    	for(int j=0; j<num; j++)
			        {
			    		expMatrix12[i][j]=0;
			    		activeMatrix[i][j]=0;
			    		unifexpMatrix[i][j]=0;
			        }
			      
			    
			    //初始化每行的最大表达值max
			    for(int i=0; i<common_list.size(); i++)
			    {
		    		max[i]=0.0;
		    		min[i]=1.0;
                }
		    	
 
			    
			    //计算表达值均值，取均值后只有12个时刻的表达值了（36个时间点包含3个周期，每个周期12个时刻）

			    for(int i=0; i<common_list.size(); i++)
			    	for(int j=0; j<num; j++)
			        {
			    	     temp11=j;
			    	     temp22=j+12;
			    	     temp33=j+24;
			    		 expMatrix12[i][j]=(expMatrix[i][temp11]+expMatrix[i][temp22]+expMatrix[i][temp33])/3;
			        }
			    
			    
			    
			    
			    
			    //初始化计算pcc的两行的位置
			    for(int i = 0; i < list2.size();i++)
			    {
			    	index1[i]=-1;
			    	index2[i]=-1;
			    }
			    			    
			    
			    
			    //将PPI中的相互作用的两个蛋白质在gene链表中的位置信息用index1和index2记录下来。
			    for(int i=0; i<list2.size(); i++)
			    {
			    	for(int j=0; j<common_list.size(); j++)
			        {
			    	    if(list2.get(i)[0].equals(common_list.get(j)))
			    	    {
			    	    	index1[i]=j;		    	    	
			    	    }
			        }
			    		
			        for(int k=0; k<common_list.size(); k++)
		            {
		    	        if(list2.get(i)[1].equals(common_list.get(k)))
		    	        {
		    	        	index2[i]=k; 
		    	        }
		            }
			    }
			  
			    
			  //归一化每个基因的表达值 (12个时间点的情况)
			    for(int i=0; i<common_list.size(); i++)
			    {
			    	//找出每行的最大值
			    	for(int j=0; j<num; j++)
			        {
			    	    if(expMatrix12[i][j]>max[i])
			    	    	max[i]=expMatrix12[i][j];
			        }
			    	
			    	//找出每行的最小值
			    	for(int k=0; k<num; k++)
			        {
			    	    if(expMatrix12[i][k]<min[i])
			    	    	min[i]=expMatrix12[i][k];
			    	   
			        }
			    	
			    	//归一化
			    	for(int j=0; j<num; j++)
			        {
			    		if(max[i]!=0)
			    		    unifexpMatrix[i][j]=expMatrix12[i][j]/max[i];
			    		else
			    			unifexpMatrix[i][j]=expMatrix12[i][j];		
			        }

			    }

		    	    	
		    	//表达值与活性阈值比较
			    int[] count;
			    //boolean b=true;
			    count=new int[num];
			    
			    for(int i =0; i<num; i++)
			    	count[i]=0;

			    for(int i = 0; i < common_list.size(); i++)
			    	for(int j = 0; j < num; j++)
			        {	
				        if(unifexpMatrix[i][j]>threshold)//1就用expMatrix12,2就用expMatrix
				            activeMatrix[i][j]=1;
				        else
				        	activeMatrix[i][j]=0;
				        	
			        }
			    
			    
			    
		        //一次输出num个子网			    
			    for(int j=0;j<num;j++){
			    
			    String filepathC = finalpath+j+".txt";	
			    	
			    //输出每个时间点对应的子网
			    FileWriter out = new FileWriter(filepathC);  //文件写入流	
 				BufferedWriter bw = new BufferedWriter(out);

 				
 				
			    for(int i = 0; i < list2.size();i++)//将PPI网络中的所有相互作用蛋白质对一对一对分别判断是否存在于当前时刻的动态网络中
			    {
		    	   //输出相互作用蛋白质对的条件：这两个蛋白质都属于基因表达与DIP共有的蛋白质集合（4981个），且这两个蛋白质在该时刻（活性矩阵的列）都是活性的
			    	if((index1[i]!=-1)&&(index2[i]!=-1)&&(activeMatrix[index1[i]][j]==1)&&(activeMatrix[index2[i]][j]==1))
		    	   {//修改上面if中活性矩阵的纵坐标（即0-11个时间点），即可得到对应时间点的动态子网
			    		
			 			    bw.write(common_list.get(index1[i])+","+common_list.get(index2[i]));
			 				bw.write("\r\n");

                                //System.out.println(gene.get(index1[i])+","+gene.get(index2[i]));
		    	   }
		    	   
			    } 	        
			        bw.flush();//清空管道
	 			    bw.close();
			    }
			    
			    
				
	    } catch (Exception e) {
			e.printStackTrace();
		} 

	}
	
	
	public int getIndexFromList(ArrayList<String> proList,String proName){
		for(int i=0;i<proList.size();i++){
			if(proList.get(i).equals(proName)){
				return i;
			}
		}	
		return -1 ;
	}
	
	
	public static void main(String[] args){
		produce_subnetworks mf = new produce_subnetworks();
		mf.activethreshold();		
		 System.out.println("produce subnetworks(temporal networks) complete!");
	}

}
