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
	double threshold=0.8;//ÿ�������ʷֱ��Ӧ�Լ��Ļ�����ֵ,��ֵȡ1ʱ����������ֵ�Ƚϵĵط�Ҫ��Ϊ���ڵ���
    int num=12;//��Ϊ12
    
    //output
	private static String finalpath="Result\\subnetworks\\network_";
	


	private ArrayList<BufferedReader> brList;	
	public void activethreshold()
	{
		
		File file = new File(expression_data); //�ܵĻ���������
		InputStream in;//�½�һ��������
		
		//WritableSheet sheet2;
		
		Workbook workbook;//�½�һ��excel������
		Sheet sheet;//�½�һ��������
		Cell  c0;//�½�һ����Ԫ��
		Cell[] ctimes;//�½�һ����Ԫ�����飨ת¼���ӣ�
		Cell[] cgene;//�½�һ����Ԫ�����飨����
		ArrayList<String> times ;//�½�һ���ַ����������ڴ��ʱ�����һ�У���ɾ��
		ArrayList<String> gene;//�½�һ���ַ����������ڴ�Ż�����һ��
        double[][] expMatrix;//�½�һ���������������ڴ�ű��ֵ
        double[][] unifexpMatrix;//�½�һ���������������ڴ�Ź�һ���ı��ֵ
        double[][] expMatrix12;//36��ʱ����Ϊ3�����ڣ�ÿ������12��ʱ��㣬����3�����ڶ�Ӧʱ����3�����ֵ��ƽ��ֵ��Ϊ�Ǹ�ʱ���ı��ֵ
        double[][] activeMatrix;//���Ծ������е�����ֵΪ0����1���������i��jʱ�̵ı��ֵ������ֵ������ڵ�activeMatrix[i][j]��ֵΪ1����֮Ϊ0
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
			    
			    //�ҳ�PPI�����а����ĵ�����
				ArrayList<String> namelist = new ArrayList();
				for(int i=0;i<brList.size();i++)
			    {
					String temp;
					while((temp=brList.get(i).readLine())!=null)
					{
					    String[] proInfo = temp.split(",");
						//�жϸõ������Ƿ��Ѵ�����proList��,
						//-1��ʾ������û�иõ����ʣ���������ʾ�������ڶ����е�λ��
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
//				System.out.println("��PPI�����а��� "+namelist.size()+" ��������");   
				
				
				in = new FileInputStream(file);//��Ŀ��excel�ļ�����������in
				workbook = Workbook.getWorkbook(in); //��excel�ļ���Ӧ�Ĺ����� 
				sheet = workbook.getSheet(0); //��excel�ļ���Ӧ�Ĺ����� �еĵ�һ��sheet				
			    ctimes = sheet.getRow(0);//��sheet�еĵ�1�и�ֵ��ctimes�������ڴ��ת¼���ӵ��ַ�������
			    times = new ArrayList<String>();//���ڴ洢��ȡ����ת¼��������
			    cgene = sheet.getColumn(0);//��sheet�еĵ�һ�и�ֵ��cgene�������ڴ�Ż�����ַ�������
			    gene = new ArrayList<String>();//���ڴ洢��ȡ���Ļ�������

	   	   	    
	   	   	    int temp11=0,temp22=0,temp33=0;//1������Щ����
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
			    
			    
			    
			    //�ҳ�DIP�ͱ�����й��еĵ�����
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

			    
			    
			    //�������ݴ���������
			    expMatrix = new double[common_list.size()][36];//������ű��ֵ�ľ�������Ϊ�������������Ϊʱ������
			    unifexpMatrix = new double[common_list.size()][num];
			    expMatrix12 = new double[common_list.size()][num];//1����
			    activeMatrix = new double[common_list.size()][num];//�������pcc�ľ�������Ϊ�������������Ϊʱ������
			    max=new double[common_list.size()];
			    min=new double[common_list.size()];
			    index1 = new int[list2.size()];
			    index2 = new int[list2.size()];
			    
			    		
			    
			    for(int i = 0; i< common_list.size();i++)
		    	{
			         for(int j = 1; j < times.size();j++)
				     {			    	
			    		    Cell c = sheet.getCell(j,index3[i]);//����Ҫע��Excel�в�ѯ�ķ�ʽ����һ��Ϊ�У��ڶ�������Ϊ��.�ȶ�λ��Ԫ��λ��
			    		    expMatrix[i][j-1] = Double.parseDouble(c.getContents());//�ٶ�ȡ�õ�Ԫ���е�����
			    	 }
		    	}
			    
			    //��ʼ��expMatrix12����ͻ��Ծ���4981*12���Լ���һ��������
			    for(int i=0; i<common_list.size(); i++)
			    	for(int j=0; j<num; j++)
			        {
			    		expMatrix12[i][j]=0;
			    		activeMatrix[i][j]=0;
			    		unifexpMatrix[i][j]=0;
			        }
			      
			    
			    //��ʼ��ÿ�е������ֵmax
			    for(int i=0; i<common_list.size(); i++)
			    {
		    		max[i]=0.0;
		    		min[i]=1.0;
                }
		    	
 
			    
			    //������ֵ��ֵ��ȡ��ֵ��ֻ��12��ʱ�̵ı��ֵ�ˣ�36��ʱ������3�����ڣ�ÿ������12��ʱ�̣�

			    for(int i=0; i<common_list.size(); i++)
			    	for(int j=0; j<num; j++)
			        {
			    	     temp11=j;
			    	     temp22=j+12;
			    	     temp33=j+24;
			    		 expMatrix12[i][j]=(expMatrix[i][temp11]+expMatrix[i][temp22]+expMatrix[i][temp33])/3;
			        }
			    
			    
			    
			    
			    
			    //��ʼ������pcc�����е�λ��
			    for(int i = 0; i < list2.size();i++)
			    {
			    	index1[i]=-1;
			    	index2[i]=-1;
			    }
			    			    
			    
			    
			    //��PPI�е��໥���õ�������������gene�����е�λ����Ϣ��index1��index2��¼������
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
			  
			    
			  //��һ��ÿ������ı��ֵ (12��ʱ�������)
			    for(int i=0; i<common_list.size(); i++)
			    {
			    	//�ҳ�ÿ�е����ֵ
			    	for(int j=0; j<num; j++)
			        {
			    	    if(expMatrix12[i][j]>max[i])
			    	    	max[i]=expMatrix12[i][j];
			        }
			    	
			    	//�ҳ�ÿ�е���Сֵ
			    	for(int k=0; k<num; k++)
			        {
			    	    if(expMatrix12[i][k]<min[i])
			    	    	min[i]=expMatrix12[i][k];
			    	   
			        }
			    	
			    	//��һ��
			    	for(int j=0; j<num; j++)
			        {
			    		if(max[i]!=0)
			    		    unifexpMatrix[i][j]=expMatrix12[i][j]/max[i];
			    		else
			    			unifexpMatrix[i][j]=expMatrix12[i][j];		
			        }

			    }

		    	    	
		    	//���ֵ�������ֵ�Ƚ�
			    int[] count;
			    //boolean b=true;
			    count=new int[num];
			    
			    for(int i =0; i<num; i++)
			    	count[i]=0;

			    for(int i = 0; i < common_list.size(); i++)
			    	for(int j = 0; j < num; j++)
			        {	
				        if(unifexpMatrix[i][j]>threshold)//1����expMatrix12,2����expMatrix
				            activeMatrix[i][j]=1;
				        else
				        	activeMatrix[i][j]=0;
				        	
			        }
			    
			    
			    
		        //һ�����num������			    
			    for(int j=0;j<num;j++){
			    
			    String filepathC = finalpath+j+".txt";	
			    	
			    //���ÿ��ʱ����Ӧ������
			    FileWriter out = new FileWriter(filepathC);  //�ļ�д����	
 				BufferedWriter bw = new BufferedWriter(out);

 				
 				
			    for(int i = 0; i < list2.size();i++)//��PPI�����е������໥���õ����ʶ�һ��һ�Էֱ��ж��Ƿ�����ڵ�ǰʱ�̵Ķ�̬������
			    {
		    	   //����໥���õ����ʶԵ������������������ʶ����ڻ�������DIP���еĵ����ʼ��ϣ�4981���������������������ڸ�ʱ�̣����Ծ�����У����ǻ��Ե�
			    	if((index1[i]!=-1)&&(index2[i]!=-1)&&(activeMatrix[index1[i]][j]==1)&&(activeMatrix[index2[i]][j]==1))
		    	   {//�޸�����if�л��Ծ���������꣨��0-11��ʱ��㣩�����ɵõ���Ӧʱ���Ķ�̬����
			    		
			 			    bw.write(common_list.get(index1[i])+","+common_list.get(index2[i]));
			 				bw.write("\r\n");

                                //System.out.println(gene.get(index1[i])+","+gene.get(index2[i]));
		    	   }
		    	   
			    } 	        
			        bw.flush();//��չܵ�
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
