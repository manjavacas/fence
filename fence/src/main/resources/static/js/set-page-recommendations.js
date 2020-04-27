
const mainResource = 'http://localhost:8080/';

window.onload = function () {
    setPage();
}

// Load projects
function setPage() {

    const resProjects = mainResource + 'Projects/';

    $.ajax({
        url: resProjects,
        type: 'GET',
        beforeSend: function () {
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {

        var options = [];

        for (let i = 0; i < data.length; i++) {
            options[i] = data[i]['name'];
        }

        var sel = document.getElementById('select-project-recommendations');

        options.forEach(function (element, key) {
            sel[key] = new Option(element, key);
        });

        var project = document.getElementById('select-project-recommendations').options[document.getElementById('select-project-recommendations').selectedIndex].text;

        loadRecommendations(project);

    });

}

// Load latests recommendations
function loadRecommendations(project) {

    const resource = mainResource + 'Recommendations/' + project;

    $.ajax({
        url: resource,
        type: 'GET',
        beforeSend: function () {
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {
        const list = document.getElementById('recommendationsText');

        text = '';
        for (let i = 0; i < data.length; i++) {
            text += `<li class="list-group-item">` + data[i] + `</li>`;
        }

        $(list).append(text);
        document.body.style.cursor = 'default';
    });

}

