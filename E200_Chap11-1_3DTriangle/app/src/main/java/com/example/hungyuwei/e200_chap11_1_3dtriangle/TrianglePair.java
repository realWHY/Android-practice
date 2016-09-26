package com.example.hungyuwei.e200_chap11_1_3dtriangle;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;

public class TrianglePair {
	private IntBuffer   mVertexBuffer;//���I�y�и�ƽw�R
    private IntBuffer   mColorBuffer;//���I�ۦ��ƽw�R
    private ByteBuffer  mIndexBuffer;//���I�غc���޸�ƽw�R
    int vCount=0;
    int iCount=0;
    
    float yAngle=0;//¶y�b���઺����
    float zAngle=0;//¶z�b���઺����
    
    public TrianglePair()
    {
    	//���I�y�и�ƪ��_�l��================begin============================
        vCount=6;
        final int UNIT_SIZE=10000;
        int vertices[]=new int[]
        {
        	-8*UNIT_SIZE,10*UNIT_SIZE,0,
        	-2*UNIT_SIZE,2*UNIT_SIZE,0,
        	-8*UNIT_SIZE,2*UNIT_SIZE,0,

        	8*UNIT_SIZE,2*UNIT_SIZE,0,
        	8*UNIT_SIZE,10*UNIT_SIZE,0,
        	2*UNIT_SIZE,10*UNIT_SIZE,0
        };
		
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
        int colors[]=new int[]//���I�m��Ȱ}�C�A�C�ӳ��I4�Ӧ�m��RGBA
        {
        		one,one,one,0,
        		0,0,one,0,
        		0,0,one,0,
        		
        		one,one,one,0,
        		one,0,0,0,
        		one,0,0,0        		
        };

        
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
        iCount=6;
        byte indices[]=new byte[]
        {
        	0,1,2,
        	3,4,5
        };
        
        //�إߤT���Ϋغc���޸�ƽw�R
        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);//�V�w�R�Ϥ���J�T���Ϋغc���޸��
        mIndexBuffer.position(0);//�]�w�w�R�ϰ_�l��m
      //�T���Ϋغc���޸�ư_�l��==========end==============================
    }

    public void drawSelf(GL10 gl)
    {        
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//�ҥγ��I�y�а}�C
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);//�ҥγ��I�m��}�C
        
        gl.glRotatef(yAngle, 0, 1, 0);
        gl.glRotatef(zAngle, 0, 0, 1);
        yAngle++;
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
