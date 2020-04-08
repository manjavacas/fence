
function setPage() {

    document.getElementById('loading-label').innerHTML = '';
    document.getElementById('loading-label').style.color = 'black';

    loadProjects();
}

// Load projects in selector
function loadProjects() {

    const resProjects = mainResource + 'Projects/';
    var sel = document.getElementById('select-project');

    $.ajax({
        url: resProjects,
        type: 'GET',
    }).done(function (data, textStatus, jqXHR) {

        var options = [];

        for (let i = 0; i < data.length; i++) {
            options[i] = data[i]['name'];
        }

        options.forEach(function (element, key) {
            sel[key] = new Option(element, key);
        });
    });
}