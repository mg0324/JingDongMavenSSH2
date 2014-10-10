


var allowMove = false;
var beforeX = 0;
var beforeY = 0;
$(".mgbox-drog").bind("mousedown",function(evt){
    allowMove = true;
    beforeX =evt.pageX - this.offsetLeft;
    beforeY =evt.pageY - this.offsetTop;
    ;            }).bind("mousemove",function(evt){
        if(allowMove){
            var moveX = evt.pageX - beforeX;
            var moveY = evt.pageY - beforeY;
            $(this).css({
                left : moveX,
                top : moveY
            });
            $("#evt_msg").text("x:"+this.offsetLeft + " -- y:" +this.offsetTop);
        }
    }).bind("mouseup mouseout",function(evt){
        allowMove = false;
    });