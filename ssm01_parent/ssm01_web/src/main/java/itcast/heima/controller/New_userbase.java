package itcast.heima.controller;

import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;

import java.util.HashMap;
import java.util.Map;

public class New_userbase {
	        private Matrix testratingsMatrixs;
	        private Matrix ratingsMatrix;
	        private Matrix yonghuMatri;
	        private Matrix User_Matrixs;
 public New_userbase(Matrix ratingsMatrix, Matrix testratingsMatrixs, Matrix yonghuMatri, Matrix User_Matrixs) {
 	        this.ratingsMatrix = ratingsMatrix;
	        this.testratingsMatrixs=testratingsMatrixs;
	        this.yonghuMatri=yonghuMatri;
	        this.yonghuMatri=User_Matrixs;
 }
 
 
 
 //依据新用户输入的属性信息useruserxsd，匹配与其最相似的用户，将该用户评分最高的项目推荐给新用户
 public Map<String,Integer> New_UserbaseAverage() {
	
	// ratingsMatrix.getRowCount()-----获取矩阵ratingsMatrix的总行数
	 //ratingsMatrix.getRowCount()-----获取矩阵ratingsMatrix的总列数
    //新建一个矩阵，行列分别为矩阵ratingsMatrix的行数与列数
	Matrix usersxsd = SparseMatrix.Factory.zeros(1, ratingsMatrix.getRowCount());
    
	//用户属性权重
	Matrix useruserxsd = SparseMatrix.Factory.zeros(ratingsMatrix.getRowCount(), ratingsMatrix.getRowCount());
     for(int i=0;i<testratingsMatrixs.getRowCount();i++) {
    	 for(int j=0;j<yonghuMatri.getRowCount();j++) {
    		 double a=0.;
    		 for(int t=0;t<yonghuMatri.getColumnCount();t++) {
    			 if(User_Matrixs.getAsDouble(i,t)==yonghuMatri.getAsDouble(j,t)) {
    				 a=a+1;
    			 }   			 
    		 }
    		 useruserxsd.setAsDouble(a/3, i,j);
    	 }
     }
		//修正的余弦相似度
		for(int i=0;i<testratingsMatrixs.getRowCount();i++){
			//计算用户i-特征均值
			int jun3=0;
			double zhi3=0.;
			for(int t=0;t<testratingsMatrixs.getColumnCount();t++) {
				if(testratingsMatrixs.getAsDouble(i,t)>0) {
					jun3++;
					zhi3=zhi3+testratingsMatrixs.getAsDouble(i,t);
				}
			}
			zhi3=zhi3/jun3;
			
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
					if(ratingsMatrix.getAsDouble(i,t)>0 && testratingsMatrixs.getAsDouble(j,t)>0) {
						c=c+(ratingsMatrix.getAsDouble(j,t) - zhi4) * (testratingsMatrixs.getAsDouble(i,t) - zhi3);
					}
				}
				a=Math.sqrt(a);
				b=Math.sqrt(b);
				d=c / (a * b);
				usersxsd.setAsDouble(d*(1+useruserxsd.getAsDouble(i,j)),i,j);//引入用户属性			
			}
		}
			
 	 System.out.println("余弦相似度"+usersxsd);

 	//计算相似度的大小,放入map中，得到推荐结果
	 HashMap<String,Integer> hashMap = new HashMap<>();



 	return hashMap;
 	 	
 }

}
