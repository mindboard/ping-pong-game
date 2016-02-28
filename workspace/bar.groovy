import java.awt.*
import java.awt.image.*
import javax.imageio.*

class Atari {

    def mysize = [width:100,height:100]

    def createImage(def mysize){
        def type = BufferedImage.TYPE_4BYTE_ABGR
        new BufferedImage((int)mysize.width,(int)mysize.height,type)
    }

    def paint(def g,def mysize){ 
        g.color = Color.BLACK
        g.fill( new Rectangle((int)mysize.width,(int)mysize.height) )
    }

    void proc(File outfile){
        def outf   = outfile
        
        def bimg=createImage(mysize)
        def g = bimg.getGraphics()
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON)
        paint(g, mysize)
        g.dispose()
        
        def fos=new FileOutputStream( outf )
        ImageIO.write(bimg,"PNG",fos)
        fos.close()
    }
}


// headless
System.setProperty("java.awt.headless", "true")
System.out.println(java.awt.GraphicsEnvironment.isHeadless())

def outf = new File(args[0])
def mysize = [width:60,height:10] 

def a = new Atari( mysize:mysize )
a.proc( outf )
