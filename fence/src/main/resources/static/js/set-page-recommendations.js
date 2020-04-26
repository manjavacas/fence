
const mainResource = 'http://localhost:8080/';

window.onload = function () {
    setPage();
}

// Load latest recommendations
function setPage() {

    const resource = mainResource + 'Recommendations/';

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

