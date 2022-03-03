$(document).ready(function(){
    chartJsCustom();
});



/* CREATE CHART FUNC */
function chartJsCustom(){
    /* DESIGN */
    // pie % text
    var moneyFormat = wNumb({
        decimals: 0,
        thousand: ',',
        prefix: '$',
        negativeBefore: '-'
    });
    var percentFormat = wNumb({
        decimals: 0,
        suffix: '%',
        negativeBefore: '-'
    });
    Chart.plugins.unregister(ChartDataLabels);
    Chart.pluginService.register({
            afterUpdate: function (chart) {
                if (chart.config.options.elements.center) {
                    var helpers = Chart.helpers;
                    var centerConfig = chart.config.options.elements.center;
                    var globalConfig = Chart.defaults.global;
                    var ctx = chart.chart.ctx;

                    var fontStyle = helpers.getValueOrDefault(centerConfig.fontStyle, globalConfig.defaultFontStyle);
                    var fontFamily = helpers.getValueOrDefault(centerConfig.fontFamily, globalConfig.defaultFontFamily);

                    if (centerConfig.fontSize)
                        var fontSize = centerConfig.fontSize;
                        // figure out the best font size, if one is not specified
                    else {
                        ctx.save();
                        var fontSize = helpers.getValueOrDefault(centerConfig.minFontSize, 1);
                        var maxFontSize = helpers.getValueOrDefault(centerConfig.maxFontSize, 256);
                        var maxText = helpers.getValueOrDefault(centerConfig.maxText, centerConfig.text);

                        do {
                            ctx.font = helpers.fontString(fontSize, fontStyle, fontFamily);
                            var textWidth = ctx.measureText(maxText).width;

                            // check if it fits, is within configured limits and that we are not simply toggling back and forth
                            if (textWidth < chart.innerRadius * 2 && fontSize < maxFontSize)
                                fontSize += 1;
                            else {
                                // reverse last step
                                fontSize -= 1;
                                break;
                            }
                        } while (true)
                        ctx.restore();
                    }

                    // save properties
                    chart.center = {
                        font: helpers.fontString(fontSize, fontStyle, fontFamily),
                        fillStyle: helpers.getValueOrDefault(centerConfig.fontColor, globalConfig.defaultFontColor)
                    };
                }
            },
            afterDraw: function (chart) {
                if (chart.center) {
                    var centerConfig = chart.config.options.elements.center;
                    var ctx = chart.chart.ctx;

                    ctx.save();
                    ctx.font = chart.center.font;
                    ctx.fillStyle = chart.center.fillStyle;
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    var centerX = (chart.chartArea.left + chart.chartArea.right) / 2;
                    var centerY = (chart.chartArea.top + chart.chartArea.bottom) / 2;
                    ctx.fillText(centerConfig.text, centerX, centerY);
                    ctx.restore();
                }
            },
        });
    // bar rounded
    // Chart.helpers.drawRoundedTopRectangle = function(ctx, x, y, width, height, radius) {
    //   ctx.beginPath();
    //   ctx.moveTo(x + radius, y);
    //   // top right corner
    //   ctx.lineTo(x + width - radius, y);
    //   ctx.quadraticCurveTo(x + width, y, x + width, y + radius);
    //   // bottom right	corner
    //   ctx.lineTo(x + width, y + height);
    //   // bottom left corner
    //   ctx.lineTo(x, y + height);
    //   // top left
    //   ctx.lineTo(x, y + radius);
    //   ctx.quadraticCurveTo(x, y, x + radius, y);
    //   ctx.closePath();
    // };
    // Chart.elements.RoundedTopRectangle = Chart.elements.Rectangle.extend({
    //   draw: function() {
    //     var ctx = this._chart.ctx;
    //     var vm = this._view;
    //     var left, right, top, bottom, signX, signY, borderSkipped;
    //     var borderWidth = vm.borderWidth;
    //
    //     if (!vm.horizontal) {
    //       // bar
    //       left = vm.x - vm.width / 2;
    //       right = vm.x + vm.width / 2;
    //       top = vm.y;
    //       bottom = vm.base;
    //       signX = 1;
    //       signY = bottom > top? 1: -1;
    //       borderSkipped = vm.borderSkipped || 'bottom';
    //     } else {
    //       // horizontal bar
    //       left = vm.base;
    //       right = vm.x;
    //       top = vm.y - vm.height / 2;
    //       bottom = vm.y + vm.height / 2;
    //       signX = right > left? 1: -1;
    //       signY = 1;
    //       borderSkipped = vm.borderSkipped || 'left';
    //     }
    //
    //     // Canvas doesn't allow us to stroke inside the width so we can
    //     // adjust the sizes to fit if we're setting a stroke on the line
    //     if (borderWidth) {
    //       // borderWidth shold be less than bar width and bar height.
    //       var barSize = Math.min(Math.abs(left - right), Math.abs(top - bottom));
    //       borderWidth = borderWidth > barSize? barSize: borderWidth;
    //       var halfStroke = borderWidth / 2;
    //       // Adjust borderWidth when bar top position is near vm.base(zero).
    //       var borderLeft = left + (borderSkipped !== 'left'? halfStroke * signX: 0);
    //       var borderRight = right + (borderSkipped !== 'right'? -halfStroke * signX: 0);
    //       var borderTop = top + (borderSkipped !== 'top'? halfStroke * signY: 0);
    //       var borderBottom = bottom + (borderSkipped !== 'bottom'? -halfStroke * signY: 0);
    //       // not become a vertical line?
    //       if (borderLeft !== borderRight) {
    //         top = borderTop;
    //         bottom = borderBottom;
    //       }
    //       // not become a horizontal line?
    //       if (borderTop !== borderBottom) {
    //         left = borderLeft;
    //         right = borderRight;
    //       }
    //     }
    //
    //     // calculate the bar width and roundess
    //     var barWidth = Math.abs(left - right);
    //     var roundness = this._chart.config.options.barRoundness || 0.5;
    //     var radius = barWidth * roundness * 0.5;
    //
    //     // keep track of the original top of the bar
    //     var prevTop = top;
    //
    //     // move the top down so there is room to draw the rounded top
    //     top = prevTop + radius;
    //     var barRadius = top - prevTop;
    //
    //     ctx.beginPath();
    //     ctx.fillStyle = vm.backgroundColor;
    //     ctx.strokeStyle = vm.borderColor;
    //     ctx.lineWidth = borderWidth;
    //
    //     // draw the rounded top rectangle
    //     Chart.helpers.drawRoundedTopRectangle(ctx, left, (top - barRadius + 1), barWidth, bottom - prevTop, barRadius);
    //
    //     ctx.fill();
    //     if (borderWidth) {
    //       ctx.stroke();
    //     }
    //
    //     // restore the original top value so tooltips and scales still work
    //     top = prevTop;
    //   },
    // });
    // Chart.defaults.roundedBar = Chart.helpers.clone(Chart.defaults.bar);
    // Chart.controllers.roundedBar = Chart.controllers.bar.extend({
    //   dataElementType: Chart.elements.RoundedTopRectangle
    // });
    // :: global Chart Option
    Chart.defaults.global.legend.position="bottom";
    Chart.defaults.global.defaultFontColor = '#7f8fa4';
    Chart.defaults.global.defaultFontSize = 12;
    Chart.defaults.global.defaultFontFamily = 'Noto Sans KR';
    Chart.defaults.global.title.display=false;
    Chart.defaults.global.responsive=true;
    // :: chart Option
    var tooltipStyle = {
        mode: 'index',
        intersect: false,
        backgroundColor: '#57638f',
        titleFontStyle: 'light',
        xPadding: 20,
        yPadding: 20,
    };
    var barChartOption = {
        maintainAspectRatio: false,
        barRoundness: 0.5,
        tooltips: tooltipStyle,
        scales: {
            // maxBarThickness: 5,
            xAxes: [{
                stacked: true,
                barPercentage: 0.5,
                gridLines: {
                    display: false
                },
            }]
        },
        legend:{
            display: false,
        }
    };
    var pieChartOption = {
        elements: {
        arc: {
            roundedCornersFor: 0,
        },
        center: {
                maxText: '100%',
                text: ' ',
                fontColor: '#59628c',
            }
        },
        layout: {
            padding: {
                left: 20,
                right: 20,
                top: 20,
                bottom: 20

            }
        },
        cutoutPercentage: 60,
        tooltips: {
            mode: 'index',
            intersect: false,
            backgroundColor: '#57638f',
            titleFontStyle: 'light',
            xPadding: 20,
            yPadding: 20,
            callbacks: {
                label: function (tooltipItem, data) {
                    var index = tooltipItem.index;
                    return data.labels[index] + ': ' + moneyFormat.to(data.datasets[0].data[index]) + '';
                }
            }
        },
        plugins: {
            datalabels: {
                anchor: 'end',
                backgroundColor: function (context) {
                    return context.dataset.backgroundColor;
                },
                borderColor: 'white',
                borderRadius: 25,
                borderWidth: 1,
                color: 'white',
                formatter: function (value, pieID) {
                    var sum = 0;
                    var dataArr = pieID.chart.data.datasets[0].data;
                    dataArr.map(function (data) {
                        sum += data;
                    });
                    var percentage = percentFormat.to((value * 100 / sum));
                    return percentage;
                }
            }
        },
        legend: {
            display: false,
        },
        animation: {
            animateScale: true,
            animateRotate: true
        }
    };
    var lineMainChartOption = {
        maintainAspectRatio: true,
        tooltips: tooltipStyle,
        scales: {

            xAxes: [{
                stacked: true,
                barPercentage: 0.3,
                step: 100,
                gridLines: {
                    display: false,
                }
            }],
            yAxes: [{
                stacked: true,

                ticks: {
                    stepSize: 100,
                    min: 0
                }

            }]
        },
        legend:{
            display: false,
        }
    };
    var lineChartOption = {
        maintainAspectRatio: false,
        tooltips: tooltipStyle,
        scales: {

            xAxes: [{
                stacked: true,
                barPercentage: 0.3,
                step: 100,
                gridLines: {
                    display: false,
                }
            }],
            yAxes: [{
                stacked: true,

                ticks: {
                    stepSize: 100,
                    min: 0
                }

            }]
        },
        legend:{
            display: false,
        }
    };
    var boxplotOption = {
        tooltips: tooltipStyle,
        scales: {
            maxBarThickness: 5,
            xAxes: [{
                categoryPercentage: 0.9,
                barPercentage: 0.3,
                step: 100,
                gridLines: {
                    display: false,
                },
                ticks: {
                }
            }],
            yAxes: [{
                ticks: {
                }

            }]
        },
        legend:{
            display: false,
        }
    };





    // A :: main Chart
    if($('.mainBarChart').length){
        var mainBarChart = document.getElementById('mainBarChart').getContext('2d');
        window.mainBarChart = new Chart(mainBarChart, {
            type: 'bar',
            data: barChartData,
            options: barChartOption,
        });
    }

	if($('.mainBarChart01').length){
        var mainBarChart01 = document.getElementById('mainBarChart01').getContext('2d');
        window.mainBarChart01 = new Chart(mainBarChart01, {
            type: 'bar',
            data: barChartData,
            options: barChartOption,
        });
    }

	if($('.mainBarChart02').length){
        var mainBarChart02 = document.getElementById('mainBarChart02').getContext('2d');
        window.mainBarChart02 = new Chart(mainBarChart02, {
            type: 'bar',
            data: barChartData,
            options: barChartOption,
        });
    }

	if($('.mainBarChart03').length){
        var mainBarChart03 = document.getElementById('mainBarChart03').getContext('2d');
        window.mainBarChart03 = new Chart(mainBarChart03, {
            type: 'bar',
            data: barChartData,
            options: barChartOption,
        });
    }


    if($('.mainPieChart').length){
        var mainPieChart = document.getElementById('mainPieChart').getContext('2d');
        window.mainPieChart = new Chart(mainPieChart, {
            //plugins: [ChartDataLabels],
            type: 'doughnut',
            data: pieChartData,
            options: pieChartOption
        });
    }
    if($('.mainLineChart').length){
        var mainLineChart = document.getElementById('mainLineChart').getContext('2d');
        window.mainLineChart = new Chart(mainLineChart, {
            type: 'line',
            data: lineChartData,
            options: lineMainChartOption
        });
    }

    // B :: analytic Page Chart
    if($('.analyticBarPageChart').length){
        var analyticBarPageChart = document.getElementById('analyticBarPageChart').getContext('2d');
        window.analyticBarPageChart = new Chart(analyticBarPageChart, {
            type: 'bar',
            data: barSubChartData,
            options: barChartOption,
        });
    }
    if($('.analyticLinePageChart').length){
        var analyticLinePageChart = document.getElementById('analyticLinePageChart').getContext('2d');
        window.analyticLinePageChart = new Chart(analyticLinePageChart, {
            type: 'line',
            data: lineChartData,
            options: lineChartOption,
        });
    }

    // Modal Chart
    if($('.analyticBarModalChart').length){
        var analyticBarModalChart = document.getElementById('analyticBarModalChart').getContext('2d');
        window.analyticBarModalChart = new Chart(analyticBarModalChart, {
            type: 'bar',
            data: barSubChartData,
            options: barChartOption,
        });
    }
    if($('.analyticBoxplotModalChart').length){
        var analyticBoxplotModalChart = document.getElementById("analyticBoxplotModalChart").getContext("2d");
        window.analyticBoxplotModalChart = new Chart(analyticBoxplotModalChart, {
          type: 'boxplot',
          data: boxPlotData,
          options: boxplotOption,
        });
    }

    // C :: data view chart
    if($('.pieChartC').length){
        var pieChartC = document.getElementById('pieChartC').getContext('2d');
        window.pieChartC = new Chart(pieChartC, {
            plugins: [ChartDataLabels],
            type: 'doughnut',
            data: pieChartData,
            options: pieChartOption
        });
    }


    // if($('.analyticBoxplotModalChart').length){
    //     var analyticBoxplotModalChart = document.getElementById('analyticBoxplotModalChart').getContext('2d');
    //     window.analyticBoxplotModalChart = new Chart(analyticBoxplotModalChart, {
    //         type: 'boxplot',
    //         data: boxplotData,
    //         options: boxplotOption
    //     });
    // }

}
