package itcast.heima.controller;

import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class lqtj {	
	/*
	 * 用户-项目-评分文件数据转换成用户-项目的评分矩阵
	 */
	 public static Matrix readFile(String filepath) {
	        /**
	         * @Method_name: readFileAsRatingsMatrix
	         * @Description: 读取评分数据，构建评分矩阵
	         * @Date: 2017/9/30
	         * @Time: 14:26
	         * @param: [filepath]文件路径
	         * @return: org.ujmp.core.Matrix
	         **/
	        Set<Double> userSet = new HashSet();
	        Set<Double> itemSet = new HashSet();
	        try {
	            Scanner in = new Scanner(new File(filepath));
	            while (in.hasNext()) {
	                String str = in.nextLine();
	                Double user = Double.parseDouble(str.split("\t")[0])-1;
	               // System.out.println("user:"+user);
	                Double item = Double.parseDouble(str.split("\t")[1])-1;
	               // System.out.println(user+"user");
	                userSet.add(user);
	                itemSet.add(item);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        Integer userCounts = userSet.size();//矩阵的行大小
	        Integer itemCounts = itemSet.size();//矩阵的列大小
	        System.out.println(userCounts+",,"+itemCounts);
	       
	        Matrix ratingsMatrix = SparseMatrix.Factory.zeros(userCounts, itemCounts);//新建一个元素均为0的矩阵（行*列）
	        try {
	            Scanner in = new Scanner(new File(filepath));
	            while (in.hasNext()) {
	                String str = in.nextLine();
	                Long user = Long.parseLong(str.split("\t")[0]);//文件的第一列元素--用户ID
	                Long item = Long.parseLong(str.split("\t")[1]);//文件的第二列元素--项目ID
	                Double rating = Double.parseDouble(str.split("\t")[2]);//文件的第三列元素--用户对项目的评分            
	              //矩阵中0行0列的值表示第一个用户对第一个项目的评分，往后递推。往矩阵中填充数据
	                ratingsMatrix.setAsDouble(rating, user-1, item-1);
	               // System.out.println("输出评分矩阵"+(user-1)+"3333333"+ratingsMatrix);                  
	            }   
	              System.out.println("输出评分矩阵"+ratingsMatrix);               
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return ratingsMatrix;
	    }
	 
	 public static Matrix readFileAs(String filepath) {
	        /**
	         * @Method_name: readFileAsItemsFeatureMatrix
	         * @Description: 读取商品的特征文件，构建特征举证
	         * @Date: 2017/9/30
	         * @Time: 14:28
	         * @param: [filepath] 商品特征路径
	         * @return: org.ujmp.core.Matrix
	         **/
	        ArrayList<String> itemsFeatures = new ArrayList(Arrays.asList("GuDian", "MYao", "JShi", "XCun", "YGun", "LXin", "LDiao"));
	        Integer featuresCount = itemsFeatures.size();
	        Integer itemsCount = 0;
	        try {
	            Scanner in = new Scanner(new File(filepath));
	            while (in.hasNext()) {
	                String str = in.nextLine();
	                itemsCount++;
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	      //构建矩阵
	        Matrix itemsFeatureMatrix = SparseMatrix.Factory.zeros(itemsCount, featuresCount);
	        try {
	            Scanner in = new Scanner(new FileInputStream(filepath));
	            Integer startIndx = 5;
	            Integer count = 0;
	            while (in.hasNext()) {
	                count++;
	                String str = in.nextLine();
	                String[] content = str.split("\\|");
	    //  System.out.println(content+"content");
	                Long itemId = Long.parseLong(content[0]) - 1;
	                for (int i = startIndx; i < content.length; i++) {
	                	//原始数据0 0 0 1 0 1 0 0 0 0 ....是这样存在的，
	                    Integer feature = Integer.parseInt(content[i]);
	                   // System.out.println("第"+startIndx);
	                    itemsFeatureMatrix.setAsInt(feature, itemId, i - startIndx);
	                }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return itemsFeatureMatrix;
	    }
	 
	 public static Matrix readFileAsyonghu(String filepath) {
	        /**
	         * @Method_name: readFileAsItemsFeatureMatrix
	         * @Description: 读取商品的特征文件，构建特征举证
	         * @Date: 2017/9/30
	         * @Time: 14:28
	         * @param: [filepath] 商品特征路径
	         * @return: org.ujmp.core.Matrix
	         **/
	        ArrayList<String> itemsFeatures = new ArrayList(Arrays.asList( "age", "xingbie", "job"));
	        Integer featuresCount = itemsFeatures.size();
	        
	        String strs[]= {"administrator","artist","doctor","educator","engineer","entertainment","executive","healthcare","homemaker","lawyer","librarian","marketing","none",
	        		"other","programmer","retired","salesman","scientist","student","technician","writer"};
	        Integer usersCount = 0;
	        try {
	            Scanner in = new Scanner(new File(filepath));
	            while (in.hasNext()) {
	                String str = in.nextLine();
	                usersCount++;
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	      //构建矩阵
	        Matrix userFeatureMatrix = SparseMatrix.Factory.zeros(usersCount, featuresCount);
	        try {
	            Scanner in = new Scanner(new FileInputStream(filepath));
	            Integer startIndx = 0;    
	            while (in.hasNext()) {
	                String str = in.nextLine();
	                String[] content = str.split("\\|");
	                //读取数据，F-0,M-1，职业从0-21（对应数组下标）
	                Long userid = Long.parseLong(content[0])-1;
	               // userFeatureMatrix.setAsDouble(userid, userid,1);
	                Long age = Long.parseLong(content[1]);
	                if(age<13) {
	                	userFeatureMatrix.setAsDouble(0, userid,0);
	                }else if(age<19 && age>12){
	                	userFeatureMatrix.setAsDouble(1, userid,0);
	                }else if(age<25 && age>18) {
	                	userFeatureMatrix.setAsDouble(2, userid,0);
	                }else if(age<35 && age>24) {
	                	userFeatureMatrix.setAsDouble(3, userid,0);
	                }else if(age<45 && age>34) {
	                	userFeatureMatrix.setAsDouble(4, userid,0);
	                }else if(age<60 && age>44) {
	                	userFeatureMatrix.setAsDouble(5, userid,0);
	                }else if( age>59) {
	                	userFeatureMatrix.setAsDouble(6, userid,0);
	                }
             
	                String xinb =content[2];
	                int xingbie;
	                if(xinb.equals("M")) {
	                	xingbie=1;	
	                }else {
	                	xingbie=0;             	
	                }
	                userFeatureMatrix.setAsDouble(xingbie, userid,1);
             
	                String jobs =content[3];
	                int job=0;
	                for(int i=0;i<strs.length;i++) {
	                	if(strs[i].equals(jobs)) {
	                		job=i;	                		
	                		continue;
	                	}
	                }
	                userFeatureMatrix.setAsDouble(job, userid,2);	                
	                
	            }
	            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }

	        return userFeatureMatrix;
	    }



	public static Matrix newreadFileAsyonghu(String filepath) {
		/**
		 * @Method_name: readFileAsItemsFeatureMatrix
		 * @Description: 读取商品的特征文件，构建特征举证
		 * @Date: 2017/9/30
		 * @Time: 14:28
		 * @param: [filepath] 商品特征路径
		 * @return: org.ujmp.core.Matrix
		 **/
		ArrayList<String> itemsFeatures = new ArrayList(Arrays.asList( "age", "xingbie", "job","address","music_type"));
		Integer featuresCount = itemsFeatures.size();

		String strs[]= {"administrator","artist","doctor","educator","engineer","entertainment","executive","healthcare","homemaker","lawyer","librarian","marketing","none",
				"other","programmer","retired","salesman","scientist","student","technician","writer"};
		Integer usersCount = 0;
		try {
			Scanner in = new Scanner(new File(filepath));
			while (in.hasNext()) {
				String str = in.nextLine();
				usersCount++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//构建矩阵
		Matrix userFeatureMatrix = SparseMatrix.Factory.zeros(usersCount, featuresCount);
		try {
			Scanner in = new Scanner(new FileInputStream(filepath));
			Integer startIndx = 0;
			while (in.hasNext()) {
				String str = in.nextLine();
				String[] content = str.split("\\|");
				//读取数据，F-0,M-1，职业从0-21（对应数组下标）
				Long userid = Long.parseLong(content[0])-1;
				// userFeatureMatrix.setAsDouble(userid, userid,1);
				Long age = Long.parseLong(content[1]);
				if(age<13) {
					userFeatureMatrix.setAsDouble(0, userid,0);
				}else if(age<19 && age>12){
					userFeatureMatrix.setAsDouble(1, userid,0);
				}else if(age<25 && age>18) {
					userFeatureMatrix.setAsDouble(2, userid,0);
				}else if(age<35 && age>24) {
					userFeatureMatrix.setAsDouble(3, userid,0);
				}else if(age<45 && age>34) {
					userFeatureMatrix.setAsDouble(4, userid,0);
				}else if(age<60 && age>44) {
					userFeatureMatrix.setAsDouble(5, userid,0);
				}else if( age>59) {
					userFeatureMatrix.setAsDouble(6, userid,0);
				}

				String xinb =content[2];
				int xingbie;
				if(xinb.equals("M")) {
					xingbie=1;
				}else {
					xingbie=0;
				}
				userFeatureMatrix.setAsDouble(xingbie, userid,1);

				String jobs =content[3];
				int job=0;
				for(int i=0;i<strs.length;i++) {
					if(strs[i].equals(jobs)) {
						job=i;
						continue;
					}
				}
				userFeatureMatrix.setAsDouble(job, userid,2);

				//地址
				int address = Integer.parseInt(content[4]);
				userFeatureMatrix.setAsDouble(address, userid,3);

				//爱好
				int music_type = Integer.parseInt(content[5]);
				userFeatureMatrix.setAsDouble(music_type, userid,4);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return userFeatureMatrix;
	}
}
