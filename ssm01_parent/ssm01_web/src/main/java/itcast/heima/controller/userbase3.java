package itcast.heima.controller;

import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class userbase3 {	
	        private Matrix testratingsMatrixs;
	        private Matrix ratingsMatrix;	    
	        private Matrix ItemMatrix;
	        private Matrix useruserxsd;
 public userbase3(Matrix ratingsMatrix, Matrix testratingsMatrixs, Matrix useruserxsd ,Matrix ItemMatrix ) {
 	        this.ratingsMatrix = ratingsMatrix;
	        this.testratingsMatrixs=testratingsMatrixs;
	        this.ItemMatrix=ItemMatrix;
	        this.useruserxsd=useruserxsd;
 }
 
 
 
 //基于用户属性的推荐算法，依据评分及用户属性权重因子得到相似度
 public Matrix userbaseAverage3() {
	
	// ratingsMatrix.getRowCount()-----获取矩阵ratingsMatrix的总行数
	 //ratingsMatrix.getRowCount()-----获取矩阵ratingsMatrix的总列数
    //新建一个矩阵，行列分别为矩阵ratingsMatrix的行数与列数
	Matrix usersxsd = SparseMatrix.Factory.zeros(1, ratingsMatrix.getRowCount());
		//修正的余弦相似度
		//for(int i=0;i<testratingsMatrixs.getRowCount();i++){
			//计算用户i-特征均值
	        int i=0;
			int jun3=0;
			double zhi3=0.;
			for(int t=0;t<testratingsMatrixs.getColumnCount();t++) {
				if(testratingsMatrixs.getAsDouble(i,t)>0) {
					jun3++;
					zhi3=zhi3+testratingsMatrixs.getAsDouble(i,t);
				}
			}
			zhi3=zhi3/jun3;
	        System.out.println("zhi3"+zhi3);
			for(int j=0;j<ratingsMatrix.getRowCount();j++) {
				//计算用户j-特征均值
				int jun4=0;
				double zhi4=0.;
				for(int t=0;t<ratingsMatrix.getColumnCount();t++) {
					if(ratingsMatrix.getAsDouble(j,t)>0) {
						jun4++;
						zhi4=zhi4+ratingsMatrix.getAsDouble(j,t);
					}
				}
				zhi4=zhi4/jun4;
				
				double a=0.,b=0.,c=0.,d=0.;
				for(int t=0;t<ratingsMatrix.getColumnCount();t++) {
					
					if(testratingsMatrixs.getAsDouble(i,t)>0) {
						a=a+(testratingsMatrixs.getAsDouble(i,t) - zhi3) * (testratingsMatrixs.getAsDouble(i,t) - zhi3);
					}
					if(ratingsMatrix.getAsDouble(j,t)>0) { 
						b=b+(ratingsMatrix.getAsDouble(j,t) - zhi4) * (ratingsMatrix.getAsDouble(j,t) - zhi4);
					}
					if(testratingsMatrixs.getAsDouble(i,t)>0 && ratingsMatrix.getAsDouble(j,t)>0) {
						c=c+(ratingsMatrix.getAsDouble(j,t) - zhi4) * (testratingsMatrixs.getAsDouble(i,t) - zhi3);
					}
				}
				System.out.println(j+","+a);
				System.out.println("zhi4 "+zhi4);
				System.out.println(j+","+b);
				a=Math.sqrt(a);
				b=Math.sqrt(b);
				d=c / (a * b);
				usersxsd.setAsDouble(d*(1+useruserxsd.getAsDouble(i,j)),i,j);//引入用户属性			
			}
		//}
			
 	System.out.println("余弦相似度"+usersxsd);

 	//计算相似度的大小,放入map中，得到推荐结果
	 //	   //混合用户属性相似度结果与项目特征相似度结果，得到最终的相似度结果0.25 - 0.75
	   Matrix UserItemMatrix = SparseMatrix.Factory.zeros(1, ratingsMatrix.getRowCount());
	   int u=0;
       for(int k=0;k<ItemMatrix.getColumnCount();k++){
		   Double temp = usersxsd.getAsDouble(u,k) * 0.25 + ItemMatrix.getAsDouble(u,k) * 0.75;
       	   UserItemMatrix.setAsDouble(temp,u,k);
	   }
	   System.out.println("UserItemMatrix" +UserItemMatrix);

 	return UserItemMatrix;
 	 	
 }

}
