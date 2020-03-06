// Load data
function loadData(element) {

    var resource = "http://localhost:8080/" + element;

    $.ajax({
        url: resource,
        type: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (data, textStatus, jqXHR) {

        // Fill table if data is not null
        var bodyRef = "#data" + element + " > tbody";
        var tableBody = document.querySelector(bodyRef);

        // Clear table
        while (tableBody.firstChild) {
            tableBody.removeChild(tableBody.firstChild);
        }

        var rows = "";

        // Load records
        for (let i = 0; i < data.length; i++) {
            rows +=
                `<tr class="hide">
                    <td class="pt-3-half" contenteditable="true">` + data[i]["dni"] + `</td>
                    <td class="pt-3-half" contenteditable="true">` + data[i]["name"] + `</td>
                    <td class="pt-3-half" contenteditable="true">` + data[i]["email"] + `</td>
                    <td class="pt-3-half" contenteditable="true">` + data[i]["genre"] + `</td>
                    <td class="pt-3-half" contenteditable="true">` + data[i]["age"] + `</td>
                    <td class="pt-3-half" contenteditable="true">` + data[i]["role"] + `</td>
                    <td class="pt-3-half" contenteditable="true">` + data[i]["timezone"] + `</td>
                    <td class="pt-3-half" contenteditable="true">` + data[i]["country"] + `</td>
                    <td class="pt-3-half" contenteditable="true">` + data[i]["experience"] + `</td>
                    <td class="pt-3-half" contenteditable="true">` + data[i]["team"] + `</td>
                    <td class="pt-3-half">
                        <span class="table-up"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-up" aria-hidden="true"></i></a></span>
                        <span class="table-down"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-down" aria-hidden="true"></i></a></span>
                    </td>
                    <td>
                        <span class="table-remove-employee"><button type="button" class="btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light">Remove</button></span>
                    </td>
                 </tr>`;
        }
        $(tableBody).append(rows);
    });
}

// Save data
function saveData(resource) {

    var resource = "http://localhost:8080/" + resource;

    // Get data from resource
    // Check differences with table
    // If differences: add to DB
}


