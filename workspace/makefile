
all : bar.png ball.png ball-red.png

bar.png : bar.groovy
	groovy bar $@

ball-red.png : ball.groovy
	groovy ball $@ red

ball.png : ball.groovy
	groovy ball $@ black

clean :
	rm -f *.png
