
document.getElementById('sldr-default-threshold').addEventListener('change', function () {

    const putResource = mainResource + '/MinGap/default'

    var obj = {}

    obj['user1'] = 'fenceapp';
    obj['user2'] = 'fenceapp';
    obj['project'] = 'fence';
    obj['customWeight'] = document.getElementById('sldr-default-threshold').value;

    // Update threshold
    const putData = JSON.stringify(obj);
    $.ajax({
        url: putResource,
        type: 'PUT',
        data: putData,
        headers: {
            'Content-Type': 'application/json'
        }
    });
});


document.getElementById('sldr-users-threshold').addEventListener('change', function () {

    var obj = {}

    obj['user1'] = document.getElementById('select-user1').options[document.getElementById('select-user1').selectedIndex].text;
    obj['user2'] = document.getElementById('select-user2').options[document.getElementById('select-user2').selectedIndex].text;
    obj['project'] = document.getElementById('select-project').options[document.getElementById('select-project').selectedIndex].text;
    obj['customWeight'] = document.getElementById('sldr-users-threshold').value;

    if (obj['user1'] != obj['user2']) {
        // Update threshold
        const putResource = mainResource + 'MinGap/' + obj['user1'] + '/' + obj['user2'] + '/' + obj['project']
        const putData = JSON.stringify(obj);
        $.ajax({
            url: putResource,
            type: 'PUT',
            data: putData,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    } else {
        alert('Error: please, select two different users.')
        document.getElementById('sldr-users-threshold').value = document.getElementById('sldr-default-threshold').value;
        document.getElementById('users-threshold').innerHTML = document.getElementById('sldr-users-threshold').value;
    }

});


function getUsersMinGap() {

    var user1 = document.getElementById('select-user1').options[document.getElementById('select-user1').selectedIndex].text;
    var user2 = document.getElementById('select-user2').options[document.getElementById('select-user2').selectedIndex].text;
    var project = document.getElementById('select-project').options[document.getElementById('select-project').selectedIndex].text;

    const resMinGapUsers = mainResource + 'MinGap/' + user1 + '/' + user2 + '/' + project;

    // Show users threshold
    if (user1 != user2) {
        $.ajax({
            url: resMinGapUsers,
            type: 'GET',
            beforeSend: function () {
                document.body.style.cursor = 'wait';
            }
        }).done(function (data, textStatus, jqXHR) {
            document.getElementById('sldr-users-threshold').value = data['customWeight'];
            document.getElementById('users-threshold').innerHTML = data['customWeight'];
            document.body.style.cursor = 'default';
        });
    }

}

document.getElementById('select-user1').addEventListener('change', getUsersMinGap);
document.getElementById('select-user2').addEventListener('change', getUsersMinGap);
document.getElementById('select-project').addEventListener('change', getUsersMinGap);

document.getElementById('btn-reset-mingaps').addEventListener('click', function () {

    var project = document.getElementById('select-project-reset').options[document.getElementById('select-project-reset').selectedIndex].text;
    const resResetMinGaps = mainResource + 'MinGap/reset/' + project;

    $.ajax({
        url: resResetMinGaps,
        type: 'GET',
        beforeSend: function () {
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {
        alert("Thresholds in " + project + " has been set to default.");
        document.body.style.cursor = 'default';
        location.reload();
    });

});