package com.example.hungyuwei.e200_chap11_5_3dwoodbox;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Crate {
	private FloatBuffer   mVertexBuffer;//頂點座標資料緩沖
    private FloatBuffer   mTextureBuffer;//頂點紋理資料緩沖
    int vCount=0;
    
    float yAngle=0;//繞y軸旋轉的角度
    float zAngle=0;//繞z軸旋轉的角度
    
    public Crate()
    {
    	//頂點座標資料的起始化================begin============================
        vCount=36;
        float size=1.0f;
        final int UNIT_SIZE=1;
        float vertices[]=new float[]
        {
        	-size*UNIT_SIZE,-size*UNIT_SIZE,-size*UNIT_SIZE,
        	-size*UNIT_SIZE,-size*UNIT_SIZE,size*UNIT_SIZE,
        	-size*UNIT_SIZE,size*UNIT_SIZE,-size*UNIT_SIZE,
        	
        	-size*UNIT_SIZE,size*UNIT_SIZE,-size*UNIT_SIZE,
        	-size*UNIT_SIZE,-size*UNIT_SIZE,size*UNIT_SIZE,
        	-size*UNIT_SIZE,size*UNIT_SIZE,size*UNIT_SIZE,
        	
        	-size*UNIT_SIZE,size*UNIT_SIZE,-size*UNIT_SIZE,
        	-size*UNIT_SIZE,size*UNIT_SIZE,size*UNIT_SIZE,
        	size*UNIT_SIZE,size*UNIT_SIZE,-size*UNIT_SIZE,
        	
        	size*UNIT_SIZE,size*UNIT_SIZE,-size*UNIT_SIZE,
        	-size*UNIT_SIZE,size*UNIT_SIZE,size*UNIT_SIZE,
        	size*UNIT_SIZE,size*UNIT_SIZE,size*UNIT_SIZE,
        	
        	size*UNIT_SIZE,size*UNIT_SIZE,-size*UNIT_SIZE,
        	size*UNIT_SIZE,size*UNIT_SIZE,size*UNIT_SIZE,
        	size*UNIT_SIZE,-size*UNIT_SIZE,-size*UNIT_SIZE,
        	
        	size*UNIT_SIZE,-size*UNIT_SIZE,-size*UNIT_SIZE,
        	size*UNIT_SIZE,size*UNIT_SIZE,size*UNIT_SIZE,
        	size*UNIT_SIZE,-size*UNIT_SIZE,size*UNIT_SIZE,
        	
        	-size*UNIT_SIZE,size*UNIT_SIZE,size*UNIT_SIZE,
        	-size*UNIT_SIZE,-size*UNIT_SIZE,size*UNIT_SIZE,
        	size*UNIT_SIZE,size*UNIT_SIZE,size*UNIT_SIZE,
        	
        	size*UNIT_SIZE,size*UNIT_SIZE,size*UNIT_SIZE,
        	-size*UNIT_SIZE,-size*UNIT_SIZE,size*UNIT_SIZE,
        	size*UNIT_SIZE,-size*UNIT_SIZE,size*UNIT_SIZE,
        	
        	-size*UNIT_SIZE,-size*UNIT_SIZE,size*UNIT_SIZE,
        	-size*UNIT_SIZE,-size*UNIT_SIZE,-size*UNIT_SIZE,
        	size*UNIT_SIZE,-size*UNIT_SIZE,size*UNIT_SIZE,
        	
        	size*UNIT_SIZE,-size*UNIT_SIZE,size*UNIT_SIZE,
        	-size*UNIT_SIZE,-size*UNIT_SIZE,-size*UNIT_SIZE,
        	size*UNIT_SIZE,-size*UNIT_SIZE,-size*UNIT_SIZE,
        	
        	-size*UNIT_SIZE,-size*UNIT_SIZE,-size*UNIT_SIZE,
        	-size*UNIT_SIZE,size*UNIT_SIZE,-size*UNIT_SIZE,
        	size*UNIT_SIZE,-size*UNIT_SIZE,-size*UNIT_SIZE,
        	
        	size*UNIT_SIZE,-size*UNIT_SIZE,-size*UNIT_SIZE,
        	-size*UNIT_SIZE,size*UNIT_SIZE,-size*UNIT_SIZE,
        	size*UNIT_SIZE,size*UNIT_SIZE,-size*UNIT_SIZE
        };
		
        //建立頂點座標資料緩沖
        //vertices.length*4是因為一個整數四個位元組
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//設定位元組順序
        mVertexBuffer = vbb.asFloatBuffer();//轉為int型緩沖
        mVertexBuffer.put(vertices);//向緩沖區中放入頂點座標資料
        mVertexBuffer.position(0);//設定緩沖區起始位置
        //特別提示：由於不同平台位元組順序不同資料單元不是位元組的一定要經由ByteBuffer
        //轉換，關鍵是要透過ByteOrder設定nativeOrder()，否則有可能會出問題
        //頂點座標資料的起始化================end============================
        
        //頂點著色資料的起始化================begin============================
        float textures[]=new float[]//頂點彩色值陣列，每個頂點4個色彩值RGBA
        {
        		0,0,
        		0,1,
        		1,0,

        		1,0,
        		0,1,
        		1,1,
        		
        		0,0,
        		0,1,
        		1,0,

        		1,0,
        		0,1,
        		1,1,
        		
        		0,0,
        		0,1,
        		1,0,

        		1,0,
        		0,1,
        		1,1,
        		
        		0,0,
        		0,1,
        		1,0,

        		1,0,
        		0,1,
        		1,1,
        		
        		0,0,
        		0,1,
        		1,0,

        		1,0,
        		0,1,
        		1,1,
        		
        		0,0,
        		0,1,
        		1,0,
        		
        		1,0,
        		0,1,
        		1,1
        };        
        //建立頂點紋理資料緩沖
        ByteBuffer cbb = ByteBuffer.allocateDirect(textures.length*4);
        cbb.order(ByteOrder.nativeOrder());//設定位元組順序
        mTextureBuffer = cbb.asFloatBuffer();//轉為int型緩沖
        mTextureBuffer.put(textures);//向緩沖區中放入頂點著色資料
        mTextureBuffer.position(0);//設定緩沖區起始位置
        //特別提示：由於不同平台位元組順序不同資料單元不是位元組的一定要經由ByteBuffer
        //轉換，關鍵是要透過ByteOrder設定nativeOrder()，否則有可能會出問題
        //頂點著色資料的起始化================end============================
    }

    public void drawSelf(GL10 gl,int texId)
    {                
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//啟用頂點座標陣列
        
        gl.glRotatef(yAngle, 0, 1, 0);
        gl.glRotatef(zAngle, 0, 0, 1);
        
		//為畫筆指定頂點座標資料
        gl.glVertexPointer
        (
        		3,				//每個頂點的座標數量為3  xyz 
        		GL10.GL_FLOAT,	//頂點座標值的型態為 GL_FLOAT
        		0, 				//連續頂點座標資料之間的間隔
        		mVertexBuffer	//頂點座標資料
        );
		
        //開啟紋理
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //容許使用紋理ST座標緩沖
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //為畫筆指定紋理ST座標緩沖
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        //綁定目前紋理
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);
		
        //繪制圖形
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES, 		//以三角形模式填充
        		0, 			 			//開始點編號
        		vCount					//頂點數量
        );  
        
        //關閉紋理
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
