package com.example.hungyuwei.my_project.Sphere3Dpakage;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class Spheroid {
	private IntBuffer   mVertexBuffer;//���I�y�и�ƽw�R
    private IntBuffer   mColorBuffer;//���I�ۦ��ƽw�R
    private ByteBuffer  mIndexBuffer;//���I�غc���޸�ƽw�R
    public float mAngleX;//�ux�b���ਤ��
    public float mAngleY;//�uy�b���ਤ�� 
    public float mAngleZ;//�uz�b���ਤ�� 
    int vCount=0;
    int iCount=0;
    
    public Spheroid(int scale)
    {
    	//���I�y�и�ƪ��_�l��================begin============================
    	final double a=2;
    	final double b=2;
    	final double c=2;
    	final int UNIT_SIZE=10000;
    	ArrayList<Integer> alVertix=new ArrayList<Integer>();//�s���I�y�Ъ�ArrayList
    	final int angleSpan=20;//�N�y�i�������������
        for(int vAngle=-90;vAngle<=90;vAngle=vAngle+angleSpan)//������VangleSpan�פ@��
        {
        	for(int hAngle=0;hAngle<360;hAngle=hAngle+angleSpan)//������VangleSpan�פ@��
        	{//���L�����U��@�Ө��׫�p����������I�b�y���W���y��    		
        		int x=(int)(a*scale*UNIT_SIZE*Math.cos(Math.toRadians(vAngle))*Math.cos(Math.toRadians(hAngle)));
        		int y=(int)(b*scale*UNIT_SIZE*Math.cos(Math.toRadians(vAngle))*Math.sin(Math.toRadians(hAngle)));
        		int z=(int)(c*scale*UNIT_SIZE*Math.sin(Math.toRadians(vAngle)));
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
        //�S�O���ܡG�ѩ󤣦P���x�줸�ն��Ǥ��P��Ƴ椸���O�줸�ժ��@�w�n�g��ByteBuffer
        //�ഫ�A����O�n�z�LByteOrder�]�wnativeOrder()�A�_�h���i��|�X���D
        //���I�y�и�ƪ��_�l��================end============================
        
        //���I�ۦ��ƪ��_�l��================begin============================
        final int one = 65535;
        int colors[]=new int[vCount*4];//���I�m��Ȱ}�C�A�C�ӳ��I4�Ӧ�m��RGBA
        for(int i=0;i<vCount;i++)
        {//�H�����ͨC�ӳ��I���m��
        	colors[i*4]=(int) (one*Math.random());//����q�D
        	colors[i*4+1]=(int) (one*Math.random());//���q�D
        	colors[i*4+2]=(int) (one*Math.random());//�Ŧ�q�D
        	colors[i*4+3]=0;//alpha��m�q�D
        }
        
        //�إ߳��I�ۦ��ƽw�R
        //vertices.length*4�O�]���@��int����ƥ|�Ӧ줸��
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());//�]�w�줸�ն���
        mColorBuffer = cbb.asIntBuffer();//�ରint���w�R
        mColorBuffer.put(colors);//�V�w�R�Ϥ���J���I�ۦ���
        mColorBuffer.position(0);//�]�w�w�R�ϰ_�l��m
        //�S�O���ܡG�ѩ󤣦P���x�줸�ն��Ǥ��P��Ƴ椸���O�줸�ժ��@�w�n�g��ByteBuffer
        //�ഫ�A����O�n�z�LByteOrder�]�wnativeOrder()�A�_�h���i��|�X���D
        //���I�ۦ��ƪ��_�l��================end============================
        
        //�T���Ϋغc���޸�ư_�l��==========begin==========================
        ArrayList<Integer> alIndex=new ArrayList<Integer>();
        int row=(180/angleSpan)+1;//�y�����������
        int col=360/angleSpan;//�y���������C��
        for(int i=0;i<row;i++)//��C�@��`��
        {
        	if(i>0&&i<row-1)
        	{//������
        		for(int j=-1;j<col+1;j++)
				{//�����檺��Ӭ۾F�I�P�U�@�檺�����I�c���T����
					int k=i*col+j;
                    Log.d("Tag1","k = "+k);
                    Log.d("Tag1","k+col = "+(k+col));
					alIndex.add(k+col);
					alIndex.add(k+1);
					alIndex.add(k);

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
        gl.glRotatef(mAngleY, 0, 1, 0);//�uY�b����
        gl.glRotatef(mAngleX, 1, 0, 0);//�uX�b����
        
        
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        
		//���e�����w���I�y�и��
        gl.glVertexPointer
        (
        		3,				//�C�ӳ��I���y�мƶq��3  xyz 
        		GL10.GL_FIXED,	//���I�y�ЭȪ����A�� GL_FIXED
        		0, 				//�s���I�y�и�Ƥ��������j
        		mVertexBuffer	//���I�y�и��
        );
		
        //���e�����w���I�ۦ���
        gl.glColorPointer
        (
        		4, 				//�]�w�m�⪺�s�զ������A������4�XRGBA
        		GL10.GL_FIXED, 	//���I�m��Ȫ����A�� GL_FIXED
        		0, 				//�s���I�ۦ��Ƥ��������j
        		mColorBuffer	//���I�ۦ���
        );
		
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
