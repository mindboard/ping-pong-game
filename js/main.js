enchant();
window.onload = function(){
    var game = new Game(320, 320);
    game.fps = 100;
    game.preload("imgs/ball.png","imgs/ball-red.png","imgs/bar.png");

    // ボール1 が進む方向とスピード設定(初期値は右下)
    var dX1 = 2;
    var dY1 = 2;

    // ボール2 が進む方向とスピード設定(初期値は左下)
    var dX2 = -3;
    var dY2 = 3;

    game.onload = function(){

        // ------------
        // ball
        // ------------
        var ball1 = new Sprite(10, 10);
        ball1.image = game.assets["imgs/ball.png"];
        ball1.x = 150;
        ball1.y = 100;
        game.rootScene.addChild(ball1);

        var ball2 = new Sprite(10, 10);
        ball2.image = game.assets["imgs/ball-red.png"];
        ball2.x = 150;
        ball2.y = 50;
        game.rootScene.addChild(ball2);


        // ------------
        // bar
        // ------------
        var bar = new Sprite(60, 10);
        bar.image = game.assets["imgs/bar.png"];
        bar.x = 150;
        bar.y = 300;
        game.rootScene.addChild(bar);


        game.addEventListener("enterframe", function(){
            // -------------------
            // Hit test : bar and ball
            // -------------------
            if(bar.intersect(ball1)) { dY1 = Math.abs(dY1)*-1; }
            if(bar.intersect(ball2)) { dY2 = Math.abs(dY2)*-1; }

            // -------------------
            // ball controll
            // -------------------
            // 壁に当たったら反転
            if( ball1.x<0 || ball1.x>(320-10) ){ dX1 = dX1*-1; }
            if( ball1.y<0 || ball1.y>(320-10) ){ dY1 = dY1*-1; }
            ball1.x = ball1.x +dX1;
            ball1.y = ball1.y +dY1;

            if( ball2.x<0 || ball2.x>(320-10) ){ dX2 = dX2*-1; }
            if( ball2.y<0 || ball2.y>(320-10) ){ dY2 = dY2*-1; }
            ball2.x = ball2.x +dX2;
            ball2.y = ball2.y +dY2;

            // -------------------
            // bar controll
            // -------------------
            if (game.input.right) {
                bar.x = bar.x+4;
                if( bar.x > (320-60) ){
                    bar.x = 320-60;
                }
            }
            if (game.input.left) {
                bar.x = bar.x-4;
                if( bar.x < 0 ){
                    bar.x = 0;
                }
            }
        });
    };

    game.start();
}
