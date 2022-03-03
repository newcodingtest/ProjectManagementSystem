// :: global Chart Color
window.chartColors = {
    primary: '#577df6',
    secondary: '#63c89b',
    third: '#00cec9',
    fourth: '#8173ed',
    fifth: '#ff7675',
    sixth: '#ec8959',
    seventh: '#f5c643'
};


// bar / line / pie 랜덤데이터
(function(global) {
    var Samples = global.Samples || (global.Samples = {});
    var Color = global.Color;
    Samples.utils = {
        srand: function(seed) {
            this._seed = seed;
        },
        rand: function(min, max) {
            var seed = this._seed;
            min = min === undefined ? 0 : min;
            max = max === undefined ? 1 : max;
            this._seed = (seed * 9301 + 49297) % 233280;
            return min + (this._seed / 233280) * (max - min);
        },
    };
    window.randomScalingFactor = function() {
        return Math.round(Samples.utils.rand(0, 125));
    };
    Samples.utils.srand(Date.now());
}(this));
// boxplot 랜덤 데이터
function randomValues(count, min, max) {
  const delta = max - min;
  const r = [];
  for (var i = 0; i < count; ++i) {
    r.push(Math.random() * delta + min);
  }
  return r;
}

/* DEMO DATA OPTION */
// 막대그래프 랜덤 다중 데이터(메인) 변수
var barChartData = {
    labels: ['03/01', '03/02', '03/03', '03/04', '03/05', '03/06', '03/07', '03/08', '03/09', '03/10', '03/11'],
    datasets: [{
        label: 'Test Model 01',
        backgroundColor: window.chartColors.primary,
        data: [
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor()
        ]
    }, {
        label: 'Test Model 02',
        backgroundColor: window.chartColors.secondary,
        data: [
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor()
        ]
    }, {
        label: 'Test Model 03',
        backgroundColor: window.chartColors.seventh,
        data: [
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor()
        ]
    }]

};
// 막대그래프 랜덤데이터 변수 data:[]
var barSubChartData = {
    labels: ['variety_Setosa', 'variety_Virginica', 'petal.width', 'petal.length', 'variety_Virginica', 'variety_Versicolor'],
    datasets: [{
        label: 'Test Model 03',
        backgroundColor: window.chartColors.primary,
        data: [
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor()
        ]
    }]
};
// 파이그래프 랜덤데이터 변수
var pieChartData = {
    datasets: [{
        data: [
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor()
        ],
        backgroundColor: [
             window.chartColors.primary,
             window.chartColors.secondary,
             window.chartColors.seventh
        ]
    }],
    labels: [
        'Regression',
        'Classification',
        'Clustering'
    ]
};
// 라인그래프 랜덤데이터 변수
var lineChartData = {
    labels: [1, 1.5, 2, 2.5, 3, 3.5, 4],
    datasets: [{
        label: 'This Week',
        borderColor:window.chartColors.seventh,
        backgroundColor: "rgba(239,244,254,0.2)",
        pointBackgroundColor: window.chartColors.seventh,
        pointHoverBorderColor: window.chartColors.seventh,
        pointHoverBackgroundColor: "#fff",
        pointBorderColor: window.chartColors.seventh,
        showLine: true,
        lineTension : 0.5,
        pointHoverBorderWidth: 4,
        borderDash: [2,4],

        data: [
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor()
        ]
    }, {
        label: 'Last Week',
        borderColor: window.chartColors.primary,
        backgroundColor: "rgba(239,244,254,0.2)",
        pointBackgroundColor: window.chartColors.primary,
        pointHoverBorderColor: window.chartColors.primary,
        pointHoverBackgroundColor: "#fff",
        pointBorderColor: window.chartColors.primary,
        lineTension : 0.5,
        pointHoverBorderWidth: 4,
        data: [
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor()
        ]
    }]

};
// 박스플로 랜덤데이터 변수
var boxPlotData = {
    labels: ['A', 'B'],
    datasets: [{
      label: 'Dataset 1',
      borderColor: window.chartColors.primary,
      borderWidth: 1,
      outlierRadius: 3,
      itemRadius: 3,
      outlierColor: window.chartColors.primary,
      data: [
        randomValues(100, 0, 10),
        randomValues(100, 0, 10)
      ],
    }],
};
// var boxplotData = {
//   // define label tree
//   labels: ['variety_Setosa', 'variety_Virginica', 'petal.width', 'petal.length', 'variety_Virginica', 'variety_Versicolor'],
//   datasets: [
//     {
//       label: "Dataset 1",
//       backgroundColor: window.chartColors.secondary,
//       borderColor:window.chartColors.primary,
//       borderWidth: 1,
//       outlierColor:window.chartColors.primary,
//       itemRadius: 0,
//
//       data: [
//         randomValues(100, 0, 100),
//         randomValues(100, 0, 20),
//         randomValues(100, 20, 70),
//         randomValues(100, 60, 100),
//         randomValues(40, 50, 100),
//         randomValues(100, 60, 120)
//       ]
//     },
//     {
//       label: "Dataset 2",
//       backgroundColor: window.chartColors.primary,
//       borderColor:window.chartColors.secondary,
//       borderWidth: 1,
//       outlierColor: window.chartColors.secondary,
//       itemRadius: 0,
//       data: [
//         randomValues(100, 60, 100),
//         randomValues(100, 0, 100),
//         randomValues(100, 0, 20),
//         randomValues(100, 20, 70),
//         randomValues(40, 60, 120),
//         randomValues(100, 20, 100)
//       ]
//     }
//   ]
// };
