import java.awt.*
import java.awt.image.*
import javax.imageio.*

class Atari {

	def mysize = [width:100,height:100]
	boolean red

	def createImage(def mysize){
		def type = BufferedImage.TYPE_4BYTE_ABGR
		new BufferedImage((int)mysize.width,(int)mysize.height,type)
	}

	def paint(def g,def mysize){ 
		g.setColor(new Color(0x00FFFFFF,true));
		g.fill( new Rectangle((int)mysize.width,(int)mysize.height) )
	
		g.color = Color.BLACK
		if( red ){
			g.color = Color.RED
		}
		int centerX = mysize.width/2.0f as int 
		int centerY = mysize.height/2.0f as int 
		int r = Math.min( mysize.width/2.0f, mysize.height/2.0f )
		g.fillOval(0, 0, mysize.width, mysize.height);
	}


	void proc(File outfile){
		def outf   = outfile
		
		def bimg=createImage(mysize)
		def g = bimg.getGraphics()
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON)
		paint(g, mysize) //, msg)
		g.dispose()
		
		def fos=new FileOutputStream( outf )
		ImageIO.write(bimg,"PNG",fos)
		fos.close()
	}
}


// headless
System.setProperty("java.awt.headless", "true")
System.out.println(java.awt.GraphicsEnvironment.isHeadless())

def outf = new File(args[0]) //'ball.png')
boolean red = ( args[1]=='red' )
def mysize = [width:10,height:10] 

def a = new Atari( mysize:mysize, red:red )
a.proc( outf )
