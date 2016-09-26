package com.example.hungyuwei.e200_chap11_8_3dphotograph;
 
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
//��ܷӤ������z�x��
public class Board {
	private FloatBuffer   mVertexBuffer;//���I�y�и�ƽw�R
    private FloatBuffer   mTextureBuffer;//���I���z��ƽw�R
    int vCount=0;//���I��
    
    public static final float length=3f;//0�צ�m�Ϥ������Z�����I�������Z��
    public static final float width=20f;//�Ϥ����e
    public static final float height=15f*0.5f;//�Ϥ����b����

    
    public Board()
    {    	
    	//���I�y�и�ƪ��_�l��================begin============================
        vCount=6;
        float vertices[]=new float[]
        {
        	length,height,0,
        	length,-height,0,
        	length+width,height,0,
        	
        	length+width,height,0,
        	length,-height,0,
        	length+width,-height,0
        };
		
        //�إ߳��I�y�и�ƽw�R
        //vertices.length*4�O�]���@�Ӿ�ƥ|�Ӧ줸��
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�]�w�줸�ն���
        mVertexBuffer = vbb.asFloatBuffer();//�ରint���w�R
        mVertexBuffer.put(vertices);//�V�w�R�Ϥ���J���I�y�и��
        mVertexBuffer.position(0);//�]�w�w�R�ϰ_�l��m
        //�S�O���ܡG�ѩ󤣦P���x�줸�ն��Ǥ��P��Ƴ椸���O�줸�ժ��@�w�n�g��ByteBuffer
        //�ഫ�A����O�n�z�LByteOrder�]�wnativeOrder()�A�_�h���i��|�X���D
        //���I�y�и�ƪ��_�l��================end============================
        
        //���I�ۦ��ƪ��_�l��================begin============================
        float textures[]=new float[]//���I�m��Ȱ}�C�A�C�ӳ��I4�Ӧ�m��RGBA
        {
        		0,0,
        		0,1,
        		1,0,
        		
        		1,0,
        		0,1,
        		1,1
        };        
        //�إ߳��I���z��ƽw�R
        ByteBuffer cbb = ByteBuffer.allocateDirect(textures.length*4);
        cbb.order(ByteOrder.nativeOrder());//�]�w�줸�ն���
        mTextureBuffer = cbb.asFloatBuffer();//�ରint���w�R
        mTextureBuffer.put(textures);//�V�w�R�Ϥ���J���I�ۦ���
        mTextureBuffer.position(0);//�]�w�w�R�ϰ_�l��m
        //�S�O���ܡG�ѩ󤣦P���x�줸�ն��Ǥ��P��Ƴ椸���O�줸�ժ��@�w�n�g��ByteBuffer
        //�ഫ�A����O�n�z�LByteOrder�]�wnativeOrder()�A�_�h���i��|�X���D
        //���I�ۦ��ƪ��_�l��================end============================
    }

    public void drawSelf(GL10 gl,int texId)
    {            
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//�ҥγ��I�y�а}�C
		//���e�����w���I�y�и��
        gl.glVertexPointer
        (
        		3,				//�C�ӳ��I���y�мƶq��3  xyz 
        		GL10.GL_FLOAT,	//���I�y�ЭȪ����A�� GL_FLOAT
        		0, 				//�s���I�y�и�Ƥ��������j
        		mVertexBuffer	//���I�y�и��
        );
		
        //�}�ү��z
        gl.glEnable(GL10.GL_TEXTURE_2D);   
        //�e�\�ϥί��zST�y�нw�R
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //���e�����w���zST�y�нw�R
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        //�j�w�ثe���z
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);
		
        //ø��ϧ�
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES, 		//�H�T���μҦ���R
        		0, 			 			//�}�l�I�s��
        		vCount					//���I�ƶq
        );  
        
        //�������z
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
