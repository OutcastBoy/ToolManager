function getOption(data,date) {
    option = {
//		title: {
//			text: '动态数据 + 时间坐标轴'
//		},
        backgroundColor: '#fff',
        tooltip: {
            trigger: 'axis',
            formatter: function(params) {
                params = params[0];
                var tipDate = params.name;
                var tipData = params.data;
                return tipDate + ' : ' + tipData + 'MB';
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: true
            }
        },
        series: [
            {
                name:'成交',
                type:'line',
                smooth:true,
                symbol: 'none',
                showSymbol: false,
                hoverAnimation: false,
                stack: 'a',
                areaStyle: {
                    normal: {}
                },
                data: data
            }
        ]
    };

    return option;
}