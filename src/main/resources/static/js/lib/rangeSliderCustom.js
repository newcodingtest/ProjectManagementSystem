$(document).ready(function(){
    if($('.range-rate').length){
        rangeSliderCustom();
    }
});

function rangeSliderCustom(){
    $(".range-rate.percentage").ionRangeSlider({
        grid: true,
        from: 3,
        keyboard: true,
        values: [
            "2%", "98%"
        ],
        hide_min_max: false,
        hide_from_to: false,
        // min: 0,
        // max: 5000,
        // from: 1000,
        // to: 4000,

        step: 1,
        // prefix: "$",
        // type: 'double', // 양쪽조절 및 한쪽조절

    });

    var data = [2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50];



    $(".range-rate.number").ionRangeSlider({

        grid: true,
        from: 3,
        keyboard: true,
        values: data,
        hide_min_max: false,
        hide_from_to: false,
        // min: 0,
        // max: 5000,
        // from: 1000,
        // to: 4000,

        step: 1,
        // prefix: "$",
        // type: 'double', // 양쪽조절 및 한쪽조절

    });


}
