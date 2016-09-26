package com.example.hungyuwei.e200_chap11_7_3dglassscene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class MySurfaceView extends GLSurfaceView {

	private SceneRenderer mRenderer;//�����ۦ⾹	
	
	public MySurfaceView(Context context) {
        super(context);
        mRenderer = new SceneRenderer();	//�إ߳����ۦ⾹
        setRenderer(mRenderer);				//�]�w�ۦ⾹		
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//�]�w�ۦ�Ҧ����D�ʵۦ�   
    }

	private class SceneRenderer implements Renderer
    {
		int baseTextureId;//�̩��h�x�Ϊ����z�����z�����zID
		int topTextureId;//���h�z�����z�����zId

		TextureRect t1;//���z�x��1
		TextureRect t2;//���z�x��2
		
        public void onDrawFrame(GL10 gl) {            
        	//���Υ����ۦ�
            gl.glShadeModel(GL10.GL_SMOOTH);
            
        	//�M���m��֨���`�ק֨�
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        	//�]�w�ثe�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();    
            
            //ø��h���z�x��
            gl.glPushMatrix();
            gl.glTranslatef(0, 0f, -2f);  
            t1.drawSelf(gl);
            gl.glPopMatrix();
            
            //ø��W�h���z�x��
            gl.glPushMatrix();
            gl.glTranslatef(0, 0f, -1.9f);  
            t2.drawSelf(gl);
            gl.glPopMatrix();
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //�]�w�����j�p�Φ�m 
        	gl.glViewport(0, 0, width, height);
        	//�]�w�ثe�x�}����v�x�}
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();
            //�p��z����v�����
            float ratio = (float) width / height;
            //�I�s����k�p�ⲣ�ͳz����v�x�}
            gl.glFrustumf(-ratio, ratio, -1, 1, 1, 100); 
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //�����ܧݰ� 
        	gl.glDisable(GL10.GL_DITHER);
        	//�]�w�S�wHint�M�ת��Ҧ��A�o�̬��]�w���ϥΧֳt�Ҧ�
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
            //�]�w�ù��I����¦�RGBA
            gl.glClearColor(0,0,0,0);            
            //�ҥβ`�״���
            gl.glEnable(GL10.GL_DEPTH_TEST);            
            //�}�ҲV�X
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
            
            //�_�l�Ư��z
            baseTextureId=initTexture(gl,R.drawable.fj1);
            topTextureId=initTexture(gl,R.drawable.top);
            
            //�إ߯x��
            t1=new TextureRect(baseTextureId);
            t2=new TextureRect(topTextureId);
        }
    }
	
	//�_�l�Ư��z
	public int initTexture(GL10 gl,int drawableId)//textureId
	{
		//���ͯ��zID
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);    
		int currTextureId=textures[0];    
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE);
        
        InputStream is = this.getResources().openRawResource(drawableId);
        Bitmap bitmapTmp; 
        try 
        {
        	bitmapTmp = BitmapFactory.decodeStream(is);
        } 
        finally 
        {
            try 
            {
                is.close();
            } 
            catch(IOException e) 
            {
                e.printStackTrace();
            }
        }
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
        bitmapTmp.recycle(); 
        
        return currTextureId;
	}
}
