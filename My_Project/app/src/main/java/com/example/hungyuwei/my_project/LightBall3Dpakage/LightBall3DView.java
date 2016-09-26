package com.example.hungyuwei.my_project.LightBall3Dpakage;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class LightBall3DView extends GLSurfaceView {

    private final float TOUCH_SCALE_FACTOR = 180.0f/320;//�����Y����
    private SceneRenderer mRenderer;//�����ۦ⾹
    private float mPreviousY;//�W����Ĳ����mY�y��
    private float mPreviousX;//�W����Ĳ����mY�y��
	private int lightAngleGreen=0;
	private int lightAngleRed=90;
	
	public LightBall3DView(Context context) {
        super(context);
        mRenderer = new SceneRenderer();	//�إ߳����ۦ⾹
        setRenderer(mRenderer);				//�]�w�ۦ⾹		
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//�]�w�ۦ�Ҧ����D�ʵۦ�   
    }

    //Ĳ�N�ƥ�^�դ�k
    @Override public boolean onTouchEvent(MotionEvent e) {
        float y = e.getY();
        float x = e.getX();
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:
            float dy = y - mPreviousY;//�p��Ĳ����Y�첾
            float dx = x - mPreviousX;//�p��Ĳ����Y�첾
            mRenderer.ball.mAngleX += dy * TOUCH_SCALE_FACTOR;//�]�w�ux�b���ਤ��
            mRenderer.ball.mAngleZ += dx * TOUCH_SCALE_FACTOR;//�]�w�uz�b���ਤ��
            requestRender();//��ø�e��
        }
        mPreviousY = y;//�O��Ĳ������m
        mPreviousX = x;//�O��Ĳ������m
        return true;
    }

	private class SceneRenderer implements Renderer
    {   
    	Ball ball=new Ball(5);
    	
    	public SceneRenderer()
    	{
            //�}�Ҥ@�Ӱ�����۰ʱ���y��
            new Thread()
            {
          	  public void run()
          	  {
          		try
                {
              	  Thread.sleep(100);//��10ms�A��ø
                }
                catch(Exception e)
                {
              	  e.printStackTrace();
                } 
          		  while(true)
          		  {
          			lightAngleGreen+=9;
          			lightAngleRed+=5;
                    requestRender();//��ø�e��
                    try
                    {
                  	  Thread.sleep(50);//��10ms�A��ø
                    }
                    catch(Exception e)
                    {
                  	  e.printStackTrace();
                    }        			  
          		  }
          	  }
            }.start();
    	}
    	
        public void onDrawFrame(GL10 gl) {  
        	gl.glShadeModel(GL10.GL_SMOOTH);
        	
        	//�]�w����������m
        	float lxGreen=(float)(17*Math.cos(Math.toRadians(lightAngleGreen)));
        	float lzGreen=(float)(17*Math.sin(Math.toRadians(lightAngleGreen)));
        	float[] positionParamsGreen={lxGreen,0,lzGreen,1};//�̫᪺1��ܬO�w���
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, positionParamsGreen,0);
            
            //�]�w�����������m
            float lyRed=(float)(7*Math.cos(Math.toRadians(lightAngleRed)));
        	float lzRed=(float)(7*Math.sin(Math.toRadians(lightAngleRed)));
            float[] positionParamsRed={0,lyRed,lzRed,1};//�̫᪺1��ܬO�w���
            gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, positionParamsRed,0);
        	
        	//�M���m��֨�
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        	//�]�w�ثe�x�}���Ҧ��x�}

            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();     
            
            gl.glTranslatef(0, 0f, -1.8f);
            ball.drawSelf(gl);
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
            gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //�����ܧݰ� 
        	gl.glDisable(GL10.GL_DITHER);
        	//�]�w�S�wHint�M�ת��Ҧ��A�o�̬��]�w���ϥΧֳt�Ҧ�
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
            //�]�w�ù��I����¦�RGBA
            gl.glClearColor(0,0,0,0);
            //�]�w�ۦ�ҫ��������ۦ�   
            gl.glShadeModel(GL10.GL_SMOOTH);//GL10.GL_SMOOTH  GL10.GL_FLAT
            //�ҥβ`�״���
            gl.glEnable(GL10.GL_DEPTH_TEST);
            
            gl.glEnable(GL10.GL_LIGHTING);//�e�\����            
            initGreenLight(gl);//�_�l�ƺ��O
            initRedLight(gl);//�_�l�Ƭ���O
            initMaterial(gl);//�_�l�Ƨ���
        }
    }
	
	private void initGreenLight(GL10 gl)
	{
        gl.glEnable(GL10.GL_LIGHT0);//�}��0���O  
        
        //���ҥ��]�w
        float[] ambientParams={0.1f,0.1f,0.1f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambientParams,0);

        //���g���]�w
        float[] diffuseParams={0f,1f,0f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffuseParams,0);

        //�Ϯg���]�w
        float[] specularParams={0.0f,1.0f,0.0f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specularParams,0);
	}
	
	private void initRedLight(GL10 gl)
	{    
        gl.glEnable(GL10.GL_LIGHT1);//�}��1���O  
        
        //���ҥ��]�w
        float[] ambientParams={0.1f,0.1f,0.1f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, ambientParams,0);            
        
        //���g���]�w
        float[] diffuseParams={1f,0f,0f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, diffuseParams,0); 
        
        //�Ϯg���]�w
        float[] specularParams={1f,0f,0f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR, specularParams,0); 
	}
	
	private void initMaterial(GL10 gl)
	{//���謰�զ�ɤ���m�⪺�����b�W���N�N��{�X����m��
        //���ҥ����զ����
        float ambientMaterial[] = {0.3f, 0.3f, 0.3f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, ambientMaterial,0);
        //���g�����զ����
        float diffuseMaterial[] = {0.3f, 0.4f, 0.3f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, diffuseMaterial,0);
        //�������謰�զ�
        float specularMaterial[] = {2.0f, 2.0f, 2.0f, 2.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, specularMaterial,0);
        //�����Ϯg�ϰ�,�ƶV�j�ϥհϰ�V�p�V�t
        float shininessMaterial[] = {5.5f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, shininessMaterial,0);
	}
}