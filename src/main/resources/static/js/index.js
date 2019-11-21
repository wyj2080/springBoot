/**
 * @author wyj2080
 * @date 2019/11/21
 */
var index = function (){

    //vue
    var app = new Vue({
        el: '#main',
        data: {
            vueMsg: 'vue数据双向绑定!'
        }
    })

    return{
        init:function () {
            //区域滚动初始化
            mui('.mui-scroll-wrapper').scroll({
                deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
            });
        }
    }
}();