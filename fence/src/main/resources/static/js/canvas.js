
const MAX_MEDITIONS = 20;
const mainResource = 'http://localhost:8080/';

window.onload = function () {

    // Fill selectors
    getSelections('Employees', 'sel-employee', 'dni');
    getSelections('Teams', 'sel-team', 'name');
    getSelections('Projects', 'sel-project', 'name');
}

document.getElementById('sel-employee').addEventListener('change', (event) => {
    loadChart('EmployeesSTC/', 'sel-employee', 'chartEmployees');
});

document.getElementById('sel-team').addEventListener('change', (event) => {
    loadChart('TeamsSTC/', 'sel-team', 'chartTeams');
});

document.getElementById('sel-project').addEventListener('change', (event) => {
    loadChart('ProjectsSTC/', 'sel-project', 'chartProjects');
});

function getSelections(resource, selector, idObj) {

    const res = mainResource + resource + '/';
    var sel = document.getElementById(selector);

    $.ajax({
        url: res,
        type: 'GET',
        beforeSend: function () {
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {

        var options = [];

        for (let i = 0; i < data.length; i++) {
            options[i] = data[i][idObj];
        }

        options.forEach(function (element, key) {
            sel[key] = new Option(element, key);
        });

        loadChart(resource + 'STC/', selector, 'chart' + resource);

        document.body.style.cursor = 'default';
    });
}

function loadChart(resource, selector, chartId) {

    var obj = document.getElementById(selector).options[document.getElementById(selector).selectedIndex].text;
    const res = mainResource + resource + obj;

    $.ajax({
        url: res,
        type: 'GET',
        beforeSend: function () {
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {

        // Limit the number of meditions displayed
        if(data.length > MAX_MEDITIONS) {
            data = data.slice(-MAX_MEDITIONS);
        }

        var xData = [];
        var yData = [];

        for (let i = 0; i < data.length; i++) {
            xData[i] = data[i]['date'].substring(0, 10);
            yData[i] = data[i]['stc'];
        }

        var chartData = {
            labels: xData,
            datasets: [{
                data: yData
            }]
        };

        //  Create chart
        var chLine = document.getElementById(chartId);
        if (chLine) {
            new Chart(chLine, {
                type: 'line',
                data: chartData,
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true,
                                steps: 10,
                                stepValue: 10,
                                max: 100
                            }
                        }]
                    },
                    legend: {
                        display: false
                    }
                }
            });
        }

        document.body.style.cursor = 'default';
    });

}


