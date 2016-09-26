package com.example.hungyuwei.e200_chap11_3_3dsphere;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class MySurfaceView extends GLSurfaceView {

    private final float TOUCH_SCALE_FACTOR = 180.0f/320;//�����Y����
    private SceneRenderer mRenderer;//�����ۦ⾹
    private float mPreviousY;//�W����Ĳ����mY�y��
    private float mPreviousX;//�W����Ĳ����mY�y��
	public MySurfaceView(Context context) {
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
            mRenderer.ball.mAngleY += dy * TOUCH_SCALE_FACTOR;//�]�w�ux�b���ਤ��
            mRenderer.ball.mAngleZ += dx * TOUCH_SCALE_FACTOR;//�]�w�uz�b���ਤ��
            requestRender();//��ø�e��
        }
        mPreviousY = y;//�O��Ĳ������m
        mPreviousX = x;//�O��Ĳ������m
        return true;
    }

	private class SceneRenderer implements Renderer
    {   
    	Spheroid ball=new Spheroid(3);
    	
    	public SceneRenderer()
    	{
            //�}�Ҥ@�Ӱ�����۰ʱ���8����
            new Thread()
            {
          	  public void run()
          	  {
          		try
                {
              	  Thread.sleep(1000);//��10ms�A��ø
                }
                catch(Exception e)
                {
              	  e.printStackTrace();
                }
          		  while(true)
          		  {
          			ball.mAngleX += 2 * TOUCH_SCALE_FACTOR;//�]�w�~��p8���骺���ਤ��
                    requestRender();//��ø�e��
                    try
                    {
                  	  Thread.sleep(10);//��10ms�A��ø
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
        	
        	//�M���m��֨�
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        	//�]�w�ثe�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();     
            
            gl.glTranslatef(0, 0f, -2.0f);
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
        }
    }
}
