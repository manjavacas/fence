
document.getElementById('btn-update-recommendations').addEventListener('click', function () {

    var project = document.getElementById('select-project-recommendations').options[document.getElementById('select-project-recommendations').selectedIndex].text;

    const resRecommendations = mainResource + 'Recommendations/calculate/' + project;
    getRecommendations(resRecommendations);
});

// Calculate and get latest recommendations
function getRecommendations(resource) {

    $.ajax({
        url: resource,
        type: 'GET',
        beforeSend: function () {
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {

        const list = document.getElementById('recommendationsText');

        // Clear table
        while (list.firstChild) {
            list.removeChild(list.firstChild);
        }

        // Fill table
        text = '';
        for (let i = 0; i < data.length; i++) {
            text += `<li class="list-group-item"><i class="fas fa-exclamation-triangle"></i> ` + data[i] + `</li>`;
        }

        $(list).append(text);
        document.body.style.cursor = 'default';
    });
}