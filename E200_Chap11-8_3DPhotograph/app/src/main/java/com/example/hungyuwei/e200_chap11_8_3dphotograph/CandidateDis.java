package com.example.hungyuwei.e200_chap11_8_3dphotograph;

import static com.example.hungyuwei.e200_chap11_8_3dphotograph.Constant.*;

//�i��Q�B���Ŀ�Ӥ����y�z�T���x�s����������O
public class CandidateDis implements Comparable<CandidateDis>
{
   float currAngleSpan;//���T�Ӥ��P270�ר�������
   float currAngle;//���T�Ӥ�������
   int index;//���T�Ӥ�������

   public CandidateDis(float currAngleSpan,float currAngle,int index)
   {
	   this.currAngleSpan=currAngleSpan;
	   this.currAngle=currAngle;
	   this.index=index;
   }

   public int compareTo(CandidateDis another) 
   {
	    //�����T�Ӥ��ֻP270�ת������p
		if(this.currAngleSpan<another.currAngleSpan)
	    {
	    	return -1;
	    }
	    if(this.currAngleSpan==another.currAngleSpan)
	    {
	    	return 0;
	    }
	    if(this.currAngleSpan>another.currAngleSpan)
	    {
	    	return 1;
	    }
		return 0;
   }
   
   
   public boolean isInXRange(float x,float y)//xy��NEAR���W��Ĳ���y��
   {
	   //�p��Ӥ������I�]���񤤤ߡ^��X�y��
	   double xn=Board.length*Math.cos(Math.toRadians(currAngle));
	   //�p��Ӥ��~���I�]�������ߡ^��X�y��
	   double xw=(Board.length+Board.width)*Math.cos(Math.toRadians(currAngle));
	   //�p��Ӥ������I�]���񤤤ߡ^��Z�y��
	   double zn=-Board.length*Math.sin(Math.toRadians(currAngle))+CENTER_Z;
	   //�p��Ӥ��~���I�]�������ߡ^��Z�y��
	   double zw=-(Board.length+Board.width)*Math.sin(Math.toRadians(currAngle))+CENTER_Z;
	   
	   //�ھڵ���T���έ�z���O�p��X����I�bNEAR���W��X�y�Ч�v
	   double proj_xn=-NEAR*xn/zn;
	   double proj_xw=-NEAR*xw/zw;  
	   
	   //���O�D�X��ӧ�vX�y�Ф��j���M�p���A���Q��i��d���޿�P�_
	   double xmax=Math.max(proj_xn, proj_xw);
	   double xmin=Math.min(proj_xn, proj_xw);
	   
	   //Ĳ���B��Y��v�d��
	   double k=x/NEAR;
	   double p=xn/(zn-CENTER_Z);
	   double zq=CENTER_Z*p/(p+k);
	   double xq=-k*zq;
	   double oa=Math.sqrt(x*x+NEAR*NEAR);
	   double ob=Math.sqrt(xq*xq+zq*zq);
	   double yq=oa*Board.height/ob;
	   
	   if(x<xmax&&x>xmin)
	   {//�Y�b�d�򤺶Ǧ^true
		   if(y>-yq&&y<yq)
		   {
			   return true;
		   }
		   else
		   {
			   return false;
		   }
	   }
	   else
	   {//�Y���b�d�򤺶Ǧ^false
		   return false;
	   }
   }
}
