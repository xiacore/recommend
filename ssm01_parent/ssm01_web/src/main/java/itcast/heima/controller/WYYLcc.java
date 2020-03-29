package itcast.heima.controller;

import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;

import java.util.ArrayList;
import java.util.List;

public class WYYLcc {
	
	    private Matrix ratingsMatrix;
	    private Matrix itemsFeatureMatrix;
	    private Matrix testratingsMatrixs;

	    private Long userCounts;
	    private Long itemCounts;
	    private Long itemFeaturesCounts;
	    public WYYLcc(Matrix ratingsMatrix, Matrix itemsFeatureMatrix, Matrix testratingsMatrixs) {
	        /**
	         * @Method_name: CBF
	         * @Description: 从商品的内容抽取特征，初始化变量
	         * @Date: 2017/10/10
	         * @Time: 14:58
	         * @param: [ratingsMatrix, itemsFeatureMatrix, inp]
	         * @return:
	         **/
	    	 this.ratingsMatrix= ratingsMatrix;

	         long startTime = System.currentTimeMillis();
	        
	        System.out.println(ratingsMatrix+"ratingsMatrix");
	        this.itemsFeatureMatrix = itemsFeatureMatrix;
	        this.testratingsMatrixs=testratingsMatrixs;//测试数据
	        this.userCounts = ratingsMatrix.getRowCount();//选行
	        this.itemCounts = ratingsMatrix.getColumnCount();
	        this.itemFeaturesCounts = itemsFeatureMatrix.getColumnCount();
	        Double runningTime = (System.currentTimeMillis() - startTime) / 1000.0;
	      
	    }
public Matrix WYYLAverage() {
	        /**
	         * @Method_name: CBFAverage
	         * @Description: 将传入的项目进行特征分解后计算特征的相似度，然后与传入的用户属性因子相似度进行
			 * 混合得到新的相似度
	         * @Date: 2017/10/10
	         * @Time: 14:59
	         * @param: []
	         * @return: org.ujmp.core.Matrix
	         **/
	     long startTime = System.currentTimeMillis(); 	     

   Matrix userFeaturesMatrix = SparseMatrix.Factory.zeros(userCounts, itemFeaturesCounts);//创建用户*项目特征矩阵    
	//数据集中特征分解
				for(int i =0;i<userCounts;i++) {
					for(int t=0;t<itemFeaturesCounts;t++) {
						List<Double> rat=new ArrayList<Double>();//评分
						for(int j=0;j<itemCounts;j++) {
							if(itemsFeatureMatrix.getAsDouble(j,t)>0) {
								if(ratingsMatrix.getAsDouble(i,j)>0) {
									rat.add(ratingsMatrix.getAsDouble(i,j));
								}
							}
						}
						int avg=0;
						Double h=0.;
						for(int d=0;d<rat.size();d++) {
							h=h+rat.get(d);
							avg++;
						}
						userFeaturesMatrix.setAsDouble(h/avg,i,t);
					}
				}

	//测试数据的特征分解
	Matrix test_FeaturesMatrix = SparseMatrix.Factory.zeros(1, itemFeaturesCounts);
				for(int i =0;i<1;i++) {
					for(int t=0;t<itemFeaturesCounts;t++) {
						List<Double> rat=new ArrayList<Double>();//评分
						for(int j=0;j<testratingsMatrixs.getColumnCount();j++) {
							if(itemsFeatureMatrix.getAsDouble(j,t)>0) {
								if(testratingsMatrixs.getAsDouble(i,j)>0) {
									rat.add(testratingsMatrixs.getAsDouble(i,j));
								}
							}
						}
						int avg=0;
						Double h=0.;
						for(int d=0;d<rat.size();d++) {
							h=h+rat.get(d);
							avg++;
						}
						test_FeaturesMatrix.setAsDouble(h/avg,i,t);
					}
				}
			Matrix ItemMatrix = SparseMatrix.Factory.zeros(1, userCounts);

					
			//通过修正的余弦相似度来计算相似度
			//for(int i=0;i<1;i++){
				int i=0;
				//计算用户i-特征均值
				int jun3=0;
				double zhi3=0.;
				for(int t=0;t<test_FeaturesMatrix.getColumnCount();t++) {
					if(test_FeaturesMatrix.getAsDouble(i,t)>0) {
						jun3++;
						zhi3=zhi3+test_FeaturesMatrix.getAsDouble(i,t);
					}
				}
				zhi3=zhi3/jun3;
				
				for(int j=0;j<userFeaturesMatrix.getRowCount();j++) {
					//计算用户j-特征均值
					int jun4=0;
					double zhi4=0.;
					for(int t=0;t<userFeaturesMatrix.getColumnCount();t++) {
						if(userFeaturesMatrix.getAsDouble(j,t)>0) {
							jun4++;
							zhi4=zhi4+userFeaturesMatrix.getAsDouble(j,t);
						}
					}
					zhi4=zhi4/jun4;
					
					double a=0.,b=0.,c=0.;
					for(int t=0;t<userFeaturesMatrix.getColumnCount();t++) {
						
						if(test_FeaturesMatrix.getAsDouble(i,t)>0) {
							a=a+(test_FeaturesMatrix.getAsDouble(i,t) - zhi3) * (test_FeaturesMatrix.getAsDouble(i,t) - zhi3);
						}
						if(userFeaturesMatrix.getAsDouble(j,t)>0) { 
							b=b+(userFeaturesMatrix.getAsDouble(j,t) - zhi4) * (userFeaturesMatrix.getAsDouble(j,t) - zhi4);
						}
						if(test_FeaturesMatrix.getAsDouble(i,t)>0 && userFeaturesMatrix.getAsDouble(j,t)>0) {
							c=c+(userFeaturesMatrix.getAsDouble(j,t) - zhi4) * (test_FeaturesMatrix.getAsDouble(i,t) - zhi3);
						}
					}
					a=Math.sqrt(a);
					b=Math.sqrt(b);
					ItemMatrix.setAsDouble(c / (a * b),i,j);
				}
			//}
			 System.out.println("项目特征Matrix"+ItemMatrix);
			//按照一定的比例混合相似度大小useryucez与useruserMatrix，放入map中比较结果，得到推荐结果

//	   //混合用户属性相似度结果与项目特征相似度结果，得到最终的相似度结果0.25 - 0.75
//	   Matrix UserItemMatrix = SparseMatrix.Factory.zeros(1, userCounts);
//	   int u=0;
//       for(int k=0;k<useruserMatrix.getColumnCount();k++){
//		   Double temp = useryucez.getAsDouble(u,k) * 0.25 + useruserMatrix.getAsDouble(u,k) * 0.75;
//       	   UserItemMatrix.setAsDouble(temp,u,k);
//	   }
//	   System.out.println("UserItemMatrix" +UserItemMatrix);

       return  ItemMatrix;

	   }	   	    
}

	   
