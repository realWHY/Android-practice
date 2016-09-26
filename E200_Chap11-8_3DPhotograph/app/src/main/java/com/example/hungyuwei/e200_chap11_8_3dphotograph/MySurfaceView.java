package com.example.hungyuwei.e200_chap11_8_3dphotograph;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.example.hungyuwei.e200_chap11_8_3dphotograph.Constant.CENTER_Z;
import static com.example.hungyuwei.e200_chap11_8_3dphotograph.Constant.NEAR;
import static com.example.hungyuwei.e200_chap11_8_3dphotograph.Constant.PHOTO_ANGLE_SPAN;
import static com.example.hungyuwei.e200_chap11_8_3dphotograph.Constant.PHOTO_COUNT;
import static com.example.hungyuwei.e200_chap11_8_3dphotograph.Constant.threadWork;

class MySurfaceView extends GLSurfaceView 
{
	public float screenWidth;//�ù��e�a
	public float screenHeight;//�ù�����
	public float ratio;//�ù����e��
	private final float TOUCH_SCALE_FACTOR = 180.0f/320;//�����Y����
    private SceneRenderer mRenderer;//�����ۦ⾹
	
    float mPreviousX;//�W�����U��mx�y��
    float mPreviousY;//�W�����U��my�y��
    long previousTime;//�W�����U���ɶ�
    boolean isCheck=false;//�O�_�I���˵��Ϥ�
    boolean isMove=false;
    
    int[] textureIds=new int[PHOTO_COUNT];//n�i�Ӥ����zid�}�C
    float yAngle=0;//�`�������ਤ��
    int currIndex=0;//�ثe�Ŀ諸����
    float yAngleV=0;//�`���������ܤƳt��
	
	public MySurfaceView(Context context) { 
        super(context);
        mRenderer = new SceneRenderer();	//�إ߳����ۦ⾹
        setRenderer(mRenderer);				//�]�w�ۦ⾹		
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//�]�w�ۦ�Ҧ����D�ʵۦ�   
        
        //�Ұʤ@�Ӱ�����ھڥثe�����t�ױ������
        threadWork=true;
        new Thread()
        {
        	public void run()
        	{
        		while(threadWork)
        		{ 
        			if(Float.isNaN(yAngle)||Float.isNaN(yAngleV))
        			{
        				throw new RuntimeException("yangle "+yAngle+"yAngleV="+yAngleV);
        			}
            		
        			//�ھڨ��t�׭p��s���������ਤ��
        			yAngle+=yAngleV;
        			if(Float.isNaN(yAngle))
        			{
        				throw new RuntimeException("yangle"+yAngle);
        			}
        			//�N���׳W��ƨ�0��360����
        			yAngle=(yAngle+360)%360;
        			if(Float.isNaN(yAngle)||Float.isNaN(yAngleV))
        			{
        				throw new RuntimeException("yangle "+yAngle+"yAngleV="+yAngleV);
        			}
        			//�Y�ثe����w�g��_�h���t�װI��
        			if(!isMove)
        			{
        				yAngleV=yAngleV*0.7f;
        			}
        			//�Y ���t�פp��]�w�ȫh�k0
        			if(Math.abs(yAngleV)<0.05)
        			{
        				yAngleV=0;
        			}
        			
        			try 
        			{
						Thread.sleep(50);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
        		}
        	}
        }.start();
    }
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent e)
    { 
    	if(keyCode==4)
    	{//�Y���U���O�Ǧ^��
    		if(isCheck)
    		{//�Y�bdetail�������h�^�D����
    			isCheck=false;
    		}
    		else
    		{//�Y�b�D�������h���}�{��
    			System.exit(0);
    		}
    	}
    	return true;
    }
	
	//Ĳ�N�ƥ�^�դ�k
    @Override 
    public boolean onTouchEvent(MotionEvent e) {
        
    	if(isCheck)
    	{//�Y�bdetail�����B�zĲ���ƥ�
    		return true;
    	}
    	
    	float x = e.getX();//���oĲ���IX�y��
        float y = e.getY();//���oĲ���IY�y��
        float dx = x - mPreviousX;//�p��X�VĲ���첾
        float dy = y - mPreviousY;//�p��Y�VĲ���첾
        long currTime=System.currentTimeMillis();//���o�ثe�ɶ��W
        long timeSpan=(currTime-previousTime)/10;//�p��⦸Ĳ���ƥ󤧶����ɶ��t
        
        switch (e.getAction()) {
        case MotionEvent.ACTION_DOWN:
        	isMove=false;
        break;
        case MotionEvent.ACTION_MOVE:        	
            if(Math.abs(dx)>5||Math.abs(dy)>5)
            {//Ĳ���첾�j��]�w�ȫh�i�J�h�����A
            	isMove=true;
            }            
            if(isMove)
            {//�Y�b�h�����A�h�p�⨤���ܤƳt��
            	if(timeSpan!=0)
            	{
            		yAngleV=dx * TOUCH_SCALE_FACTOR/timeSpan;    
            	}            	          	
            }            
        break;
        case MotionEvent.ACTION_UP:
        	//�Y�b�D�h�����A�B���׳t�׬�0�h�ݤĿ諸�O���T�Ӥ������
        	if(!isMove&&yAngleV==0)
        	{   
        		//���XĲ���I�bNEAR���W����m
        		float nearX=(x-screenWidth/2)*ratio/(screenWidth/2);
        		float nearY=(screenHeight/2-y)/(screenHeight/2);
        		
        		//���P�_�I�U�h���O�����٬O�k��
        		if(x>screenWidth/2)
        		{//�k��
        			ArrayList<CandidateDis> al=new ArrayList<CandidateDis>();
        			for(int i=0;i<PHOTO_COUNT;i++)
        			{
        				//�p�⦹�T�Ӥ�������
        				float tempAngle=(i*PHOTO_ANGLE_SPAN+yAngle)%360;
        				//�Y���צb270��360�d�򤺫h�b�k�䪺�e��
        				if(tempAngle>270&&tempAngle<360)
        				{
        					al.add(new CandidateDis(tempAngle-270,tempAngle,i));  
        				}
        			}    
        			//�ھڻP270�ת������ƧǡA�����p���Ʀb�e��
        		    Collections.sort(al);
        		    //�ˬd�Կ�M��֦bX�d�򤺽ֳ�W���
        		    currIndex=-1;
        		    for(CandidateDis cd:al)
        		    {
        		    	if(cd.isInXRange(nearX,nearY))
        		    	{
        		    		currIndex=cd.index;
        		    		break;
        		    	}
        		    }
        		}
        		else
        		{
        			ArrayList<CandidateDis> al=new ArrayList<CandidateDis>();
        			for(int i=0;i<PHOTO_COUNT;i++)
        			{
        				//�p�⦹�T�Ӥ�������
        				float tempAngle=(i*PHOTO_ANGLE_SPAN+yAngle)%360;
        				//�Y���צb180��270�d�򤺫h�b���䪺�e��
        				if(tempAngle>180&&tempAngle<270)
        				{
        					al.add(new CandidateDis(270-tempAngle,tempAngle,i));
        				}
        			}
        			//�ھڻP270�ת������ƧǡA�����p���Ʀb�e��
        			Collections.sort(al);
        			//�ˬd�Կ�M��֦bX�d�򤺽ֳ�W���
        			currIndex=-1;
        		    for(CandidateDis cd:al)
        		    {
        		    	if(cd.isInXRange(nearX,nearY))
        		    	{
        		    		currIndex=cd.index;
        		    		break;
        		    	}
        		    } 
        		}
        		//�Y���Ŀ�Ӥ��A�h�]�w�i�Jdetail��ܪ��A
        		if(currIndex!=-1)
        		{
        			isCheck=true;
        		}        		
        	}
        	isMove=false;
        break;
        }
        mPreviousX = x;//�O��Ĳ������m
        mPreviousY = y;//�O��Ĳ������m
        previousTime=currTime;//�O�������ɶ�
        return true;
    }

	private class SceneRenderer implements Renderer
    {   
    	Board tp=new Board(); //�Ω���ܷӤ������z�x��   	
    	
    	public void onDrawFrame(GL10 gl) 
        { 
        	//�M���m��֨���`�ק֨�
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        	//�]�w�ثe�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();    
            
            if(isCheck)
            {
            	//��ܬY�@�T�Ӥ��Adetail���A
            	gl.glPushMatrix();
                gl.glTranslatef(0, 0f, -52f); 
            	gl.glTranslatef(-Board.length-Board.width*0.5f, 0, 0);
            	tp.drawSelf(gl,textureIds[currIndex]);
            	gl.glPopMatrix();  
            }
            else
            {
            	//��ܷӤ��s�աA�iĲ��������
            	gl.glPushMatrix();
                gl.glTranslatef(0, 0f, CENTER_Z);  
                yAngle=yAngle%360;
                gl.glRotatef(yAngle, 0, 1, 0);
                for(int i=0;i<textureIds.length;i++)
                {//�ˬd���z�}�C�A��ܨC�T�Ӥ�
                	gl.glPushMatrix();
                    gl.glRotatef(i*PHOTO_ANGLE_SPAN, 0, 1, 0);
                    tp.drawSelf(gl,textureIds[i]);
                    gl.glPopMatrix();
                }
                gl.glPopMatrix();
            }
        }
    	
    	public void onSurfaceChanged(GL10 gl, int width, int height) {
            //�]�w�����j�p�Φ�m 
        	gl.glViewport(0, 0, width, height);
        	//�]�w�ثe�x�}����v�x�}
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //�]�w�ثe�x�}�����x�}
            gl.glLoadIdentity();
            //�I�s����k�p�ⲣ�ͳz����v�x�} 
            gl.glFrustumf(-ratio, ratio, -1, 1, NEAR, 100);
            //�]�w�������I���ŵ�
        	gl.glDisable(GL10.GL_CULL_FACE);
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
            //���Jn�T���z��
            //textureIds[0]=initTexture(gl,R.drawable.fj1);//�Ϥ�1
            textureIds[1]=initTexture(gl,R.drawable.fj2);//�Ϥ�2
            textureIds[2]=initTexture(gl,R.drawable.fj3);//�Ϥ�3
            textureIds[3]=initTexture(gl,R.drawable.fj4);//�Ϥ�4
            textureIds[4]=initTexture(gl,R.drawable.fj5);//�Ϥ�5
            textureIds[5]=initTexture(gl,R.drawable.fj6);//�Ϥ�6
            textureIds[6]=initTexture(gl,R.drawable.fj7);//�Ϥ�7
            textureIds[7]=initTexture(gl,R.drawable.fj8);//�Ϥ�8
            textureIds[8]=initTexture(gl,R.drawable.fj9);//�Ϥ�9
            //textureIds[9]=initTexture(gl,R.drawable.fj10);//�Ϥ�10
            //textureIds[10]=initTexture(gl,R.drawable.fj11);//�Ϥ�11
            textureIds[11]=initTexture(gl,R.drawable.fj12);//�Ϥ�12
            textureIds[12]=initTexture(gl,R.drawable.fj13);//�Ϥ�13
            textureIds[13]=initTexture(gl,R.drawable.fj14);//�Ϥ�14
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
