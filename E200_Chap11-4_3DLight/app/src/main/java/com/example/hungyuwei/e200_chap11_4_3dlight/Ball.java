package com.example.hungyuwei.e200_chap11_4_3dlight;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class Ball {
	private IntBuffer   mVertexBuffer;//���I�y�и�ƽw�R
	private IntBuffer   mNormalBuffer;//���I�k�V�q��ƽw�R
    private ByteBuffer  mIndexBuffer;//���I�غc���޸�ƽw�R
    public float mAngleX;//�ux�b���ਤ��
    public float mAngleY;//�uy�b���ਤ�� 
    public float mAngleZ;//�uz�b���ਤ�� 
    int vCount=0;
    int iCount=0;
    
    public Ball(int scale)
    {
    	//���I�y�и�ƪ��_�l��================begin============================
    	final int UNIT_SIZE=10000;
    	ArrayList<Integer> alVertix=new ArrayList<Integer>();//�s���I�y�Ъ�ArrayList
    	final int angleSpan=18;//�N�y�i�������������
        for(int vAngle=-90;vAngle<=90;vAngle=vAngle+angleSpan)//������VangleSpan�פ@��
        {
        	for(int hAngle=0;hAngle<360;hAngle=hAngle+angleSpan)//������VangleSpan�פ@��
        	{//���L�����U��@�Ө��׫�p����������I�b�y���W���y��
        		double xozLength=scale*UNIT_SIZE*Math.cos(Math.toRadians(vAngle));
        		int x=(int)(xozLength*Math.cos(Math.toRadians(hAngle)));
        		int z=(int)(xozLength*Math.sin(Math.toRadians(hAngle)));
        		int y=(int)(scale*UNIT_SIZE*Math.sin(Math.toRadians(vAngle)));
        		//�N�p��X�Ӫ�XYZ�y�Х[�J�s���I�y�Ъ�ArrayList
        		alVertix.add(x);alVertix.add(y);alVertix.add(z);
        	}
        } 	
        vCount=alVertix.size()/3;//���I���ƶq���y�Эȼƶq��1/3�A�]���@�ӳ��I��3�Ӯy��
    	
        //�NalVertix�����y�Э���s��@��int�}�C��
        int vertices[]=new int[vCount*3];
    	for(int i=0;i<alVertix.size();i++)
    	{
    		vertices[i]=alVertix.get(i);
    	}
		
        //�إ߳��I�y�и�ƽw�R
        //vertices.length*4�O�]���@�Ӿ�ƥ|�Ӧ줸��
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�]�w�줸�ն���
        mVertexBuffer = vbb.asIntBuffer();//�ରint���w�R
        mVertexBuffer.put(vertices);//�V�w�R�Ϥ���J���I�y�и��
        mVertexBuffer.position(0);//�]�w�w�R�ϰ_�l��m     
        
        
        //�إ߳��I�k�V�q��ƽw�R
        //vertices.length*4�O�]���@��float�|�Ӧ줸��
        ByteBuffer nbb = ByteBuffer.allocateDirect(vertices.length*4);
        nbb.order(ByteOrder.nativeOrder());//�]�w�줸�ն���
        mNormalBuffer = vbb.asIntBuffer();//�ରint���w�R
        mNormalBuffer.put(vertices);//�V�w�R�Ϥ���J���I�y�и��
        mNormalBuffer.position(0);//�]�w�w�R�ϰ_�l��m
        
        
        //�S�O���ܡG�ѩ󤣦P���x�줸�ն��Ǥ��P��Ƴ椸���O�줸�ժ��@�w�n�g��ByteBuffer
        //�ഫ�A����O�n�z�LByteOrder�]�wnativeOrder()�A�_�h���i��|�X���D
        //���I�y�и�ƪ��_�l��================end============================
        
               
        //�T���Ϋغc���޸�ư_�l��==========begin==========================
        ArrayList<Integer> alIndex=new ArrayList<Integer>();
        int row=(180/angleSpan)+1;//�y�����������
        int col=360/angleSpan;//�y���������C��
        for(int i=0;i<row;i++)//��C�@��`��
        {
        	if(i>0&&i<row-1)
        	{//������
        		for(int j=-1;j<col;j++)
				{//�����檺��Ӭ۾F�I�P�U�@�檺�����I�c���T����
					int k=i*col+j;
					alIndex.add(k+col);
					alIndex.add(k+1);
					alIndex.add(k);		
				}
        		for(int j=0;j<col+1;j++)
				{//�����檺��Ӭ۾F�I�P�W�@�檺�����I�c���T����				
					int k=i*col+j;
					alIndex.add(k-col);
					alIndex.add(k-1);
					alIndex.add(k);	
				}
        	}
        }
        iCount=alIndex.size();
        byte indices[]=new byte[alIndex.size()];
        for(int i=0;i<alIndex.size();i++)
        {
        	indices[i]=alIndex.get(i).byteValue();
        } 
        //�إߤT���Ϋغc���޸�ƽw�R
        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);//�V�w�R�Ϥ���J�T���Ϋغc���޸��
        mIndexBuffer.position(0);//�]�w�w�R�ϰ_�l��m
      //�T���Ϋغc���޸�ư_�l��==========end==============================
    }

    public void drawSelf(GL10 gl)
    {
    	gl.glRotatef(mAngleZ, 0, 0, 1);//�uZ�b����
    	gl.glRotatef(mAngleX, 1, 0, 0);//�uX�b����
        gl.glRotatef(mAngleY, 0, 1, 0);//�uY�b����
        
        
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        
        
		//���e�����w���I�y�и��
        gl.glVertexPointer
        (
        		3,				//�C�ӳ��I���y�мƶq��3  xyz 
        		GL10.GL_FIXED,	//���I�y�ЭȪ����A�� GL_FIXED
        		0, 				//�s���I�y�и�Ƥ��������j
        		mVertexBuffer	//���I�y�и��
        );
        
        //���e�����w���I�k�V�q���
        gl.glNormalPointer(GL10.GL_FIXED, 0, mNormalBuffer);
		
        //ø��ϧ�
        gl.glDrawElements
        (
        		GL10.GL_TRIANGLES, 		//�H�T���μҦ���R
        		iCount, 			 	//�@�@icount/3�ӤT���ΡAiCount�ӳ��I
        		GL10.GL_UNSIGNED_BYTE, 	//���ޭȪ��ؤo
        		mIndexBuffer			//���ޭȸ��
        ); 
    }
}
