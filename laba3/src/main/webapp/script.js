let booksTable = $('.table');

function Add() {
    let rawName = $('#inputName').val();
    let rawPublisher = $('#inputPublisher').val();
    let rawAge = $('#inputAge').val();
    let rawAuthor = $('#inputAuthor').val();
    let rawPageCount = $('#inputPageCount').val();

    let book = {
        name: rawName,
        publisher: rawPublisher,
        age : rawAge,
        author: rawAuthor,
        pageCount: rawPageCount
    }

    $.ajax({
        type: 'POST',
        url: 'MainServlet',
        data: JSON.stringify(book),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {},
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });

    $('#inputName').val('');
    $('#inputPublisher').val('');
    $('#inputAge').val('');
    $('#inputAuthor').val('');
    $('#inputPageCount').val('');
}

function Update() {
    $.ajax({
        url: 'MainServlet',
        type: "GET",
        dataType: "json",
        success: function(data) {
            $.each(data, function(i, book) {
                let row = $("<tr>");

                let tdName = document.createElement("td");
                tdName.innerText = book.name;
                row.append(tdName);

                let tdPublisher = document.createElement("td");
                tdPublisher.innerText = book.publisher;
                row.append(tdPublisher);

                let tdAge = document.createElement("td");
                tdAge.innerText = book.age;
                row.append(tdAge);

                let tdAuthor = document.createElement("td");
                tdAuthor.innerText = book.author;
                row.append(tdAuthor);

                let tdPageCount = document.createElement("td");
                tdPageCount.innerText = book.pageCount;
                row.append(tdPageCount);

                booksTable.find('tbody').append(row);
            });

        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}