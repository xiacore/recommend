package itcast.heima.controller;

import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TimeCbf {

	
	//构建用户-项目属性个数矩阵
	public static Matrix getuseritemcount(Matrix ratingsMatrixs,Matrix itemsFeatureMatrixs) {
		//构建用户-项目属性个数矩阵，初始均为0
		Matrix useritemCountsMatrix = SparseMatrix.Factory.zeros(ratingsMatrixs.getRowCount(), itemsFeatureMatrixs.getColumnCount());
		for(int i=0;i<ratingsMatrixs.getRowCount();i++) {
			for(int j=0;j<itemsFeatureMatrixs.getRowCount();j++) {
				if(ratingsMatrixs.getAsDouble(i,j)>0) {
					for(int a=0;a<itemsFeatureMatrixs.getColumnCount();a++) {
						if(itemsFeatureMatrixs.getAsDouble(j,a)>0) {
							useritemCountsMatrix.setAsDouble(useritemCountsMatrix.getAsDouble(i,a)+1, i,a);
						}
					}
				}
			}
		}
		return useritemCountsMatrix;
	}
	
	
	public static Matrix getitemitemcount(Matrix ratingsMatrixs) {
		
		Matrix itemitemCountsMatrix = SparseMatrix.Factory.zeros(ratingsMatrixs.getColumnCount(), ratingsMatrixs.getColumnCount());
		 
		for(int i=0;i<ratingsMatrixs.getColumnCount();i++) {
			int count=0;
			for(int j=0;j<ratingsMatrixs.getRowCount();j++) {
				if(ratingsMatrixs.getAsDouble(j,i)>0) {
					count++;
				}		
			}
			itemitemCountsMatrix.setAsDouble(count, i,i);				
		}
	   return itemitemCountsMatrix;
   
	}
		
}
