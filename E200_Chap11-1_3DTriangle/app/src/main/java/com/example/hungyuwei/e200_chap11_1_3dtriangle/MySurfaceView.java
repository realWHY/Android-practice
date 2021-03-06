package com.example.hungyuwei.e200_chap11_1_3dtriangle;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
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
        Log.d("Tag","Touch event");
        return true;
    }

	private class SceneRenderer implements Renderer
    {   
    	TrianglePair tp=new TrianglePair();
    	
    	public SceneRenderer()
    	{
 
    	}
    	
        public void onDrawFrame(GL10 gl) {   
    		//�]�w���}�ҭI���ŵ�
    		gl.glEnable(GL10.GL_CULL_FACE);
    		//�]�w�ۦ�ҫ����������ۦ�   
    		gl.glShadeModel(GL10.GL_FLAT);       	
        	//�M���m��֨���`�ק֨�
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        	//�]�w�ثe�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();     
            
            gl.glTranslatef(0, -1f, -3f);  
            tp.drawSelf(gl);
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
            //�ҥβ`�״���
            gl.glEnable(GL10.GL_DEPTH_TEST);
        }
    }
}
