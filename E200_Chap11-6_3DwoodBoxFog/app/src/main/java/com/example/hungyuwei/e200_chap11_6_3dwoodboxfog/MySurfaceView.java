package com.example.hungyuwei.e200_chap11_6_3dwoodboxfog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.view.MotionEvent;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class MySurfaceView extends GLSurfaceView {

	private final float TOUCH_SCALE_FACTOR = 180.0f/320;//�����Y����
    private SceneRenderer mRenderer;//�����ۦ⾹
	
	private float mPreviousY;//�W����Ĳ����mY�y��
    private float mPreviousX;//�W����Ĳ����mY�y��
    
	private int lightAngle=90;
    int crateTextureId;//��c���zid
	
	public MySurfaceView(Context context) {  
        super(context);
        mRenderer = new SceneRenderer();	//�إ߳����ۦ⾹
        setRenderer(mRenderer);				//�]�w�ۦ⾹		
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//�]�w�ۦ�Ҧ����D�ʵۦ�   
    }	
	//Ĳ�N�ƥ�^�դ�k
    @Override 
    public boolean onTouchEvent(MotionEvent e) {
        float y = e.getY();   
        float x = e.getX();
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:
            float dy = y - mPreviousY;//�p��Ĳ����Y�첾
            float dx = x - mPreviousX;//�p��Ĳ����Y�첾
            mRenderer.tp.yAngle += dx * TOUCH_SCALE_FACTOR;//�]�w�ux�b���ਤ��
            mRenderer.tp.zAngle+= dy * TOUCH_SCALE_FACTOR;//�]�w�uz�b���ਤ��
            requestRender();//��ø�e��
        }
        mPreviousY = y;//�O��Ĳ������m
        mPreviousX = x;//�O��Ĳ������m
        return true;
    }

	private class SceneRenderer implements Renderer
    {   
    	Crate tp=new Crate();
    	
    	public SceneRenderer()
    	{
 
    	}
    	
        public void onDrawFrame(GL10 gl) {       
        	//�]�w����������m
        	float lx=(float)(10*Math.cos(Math.toRadians(lightAngle)));
        	float lz=(float)(10*Math.sin(Math.toRadians(lightAngle)));
        	float[] positionParamsGreen={lx,0,lz,1};//�̫᪺1��ܬO�w���
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, positionParamsGreen,0); 
            
    		//�]�w�������I���ŵ�
    		gl.glDisable(GL10.GL_CULL_FACE);   	
        	//�M���m��֨���`�ק֨�
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        	//�]�w�ثe�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();     
            
            gl.glTranslatef(0, 0f, -5f);  
            tp.drawSelf(gl,crateTextureId);
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

        public void onSurfaceCreated(GL10 gl, EGLConfig config) 
        {        	        	
            //�����ܧݰ� 
        	gl.glDisable(GL10.GL_DITHER);
        	//�]�w�S�wHint�M�ת��Ҧ��A�o�̬��]�w���ϥΧֳt�Ҧ�
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
            //�]�w�ù��I����¦�RGBA
            gl.glClearColor(0,0,0,0);            
            //�ҥβ`�״���
            gl.glEnable(GL10.GL_DEPTH_TEST);
            
        	crateTextureId=initTexture(gl,R.drawable.crate);
            
            gl.glEnable(GL10.GL_FOG);//�e�\��
            initFog(gl);//�_�l����
            
            gl.glEnable(GL10.GL_LIGHTING);//�e�\����  
            initLight(gl);
            initMaterial(gl);
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
	
	//�_�l����
	public void initFog(GL10 gl)
	{
		float[] fogColor={20/255,20/255,20/255,0};//�����m��
		gl.glFogfv(GL10.GL_FOG_COLOR, fogColor, 0);//�]�w�����m��
		gl.glFogx(GL10.GL_FOG_MODE, GL10.GL_EXP2);//�]�w�����Ҧ�
		gl.glFogf(GL10.GL_FOG_DENSITY, 0.19f);//�]�w�����@��
		gl.glFogf(GL10.GL_FOG_START, 0.3f);//�]�w�����}�l�Z��
		gl.glFogf(GL10.GL_FOG_END, 50.0f);//�]�w���������Z��
	}
	
	private void initLight(GL10 gl)
	{
        gl.glEnable(GL10.GL_LIGHT0);//�}��0���O  
        
        //���ҥ��]�w
        float[] ambientParams={0.5f,0.5f,0f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambientParams,0);            

        //���g���]�w
        float[] diffuseParams={1f,1f,0f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffuseParams,0); 
        
        //�Ϯg���]�w
        float[] specularParams={0.5f,0.5f,0f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specularParams,0);     
	}
	
	private void initMaterial(GL10 gl)
	{//���謰�զ�ɤ���m�⪺�����b�W���N�N��{�X����m��
        //���ҥ����զ����
        float ambientMaterial[] = {0.6f, 0.6f, 0.6f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, ambientMaterial,0);
        //���g�����զ����
        float diffuseMaterial[] = {0.8f, 0.8f, 0.8f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, diffuseMaterial,0);
        //�������謰�զ�
        float specularMaterial[] = {1.5f, 1.5f, 1.5f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, specularMaterial,0);
        //�����Ϯg�ϰ�,�ƶV�j�ϥհϰ�V�p�V�t
        float shininessMaterial[] = {1.5f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, shininessMaterial,0);
	}
}
