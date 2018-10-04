var data = document.getElementById("script-teamunit").getAttribute("data-values").split("|");
var labels = document.getElementById("script-teamunit").getAttribute("data-labels").split("|");
var colors = document.getElementById("script-teamunit").getAttribute("data-colors").split("|");


    var config = {
        type: 'bar',
        data: {
            datasets: [{
                data: data,
                backgroundColor: colors,
                label: 'Position Salary Totals'
            }],
            labels: labels
        },
        options: {
            responsive: true
        }
    };

    window.onload = function() {
        var ctx = document.getElementById('chart-teamunit').getContext('2d');
        window.myPie = new Chart(ctx, config);
    };