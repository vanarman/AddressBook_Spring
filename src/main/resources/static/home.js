let activePage = new _buddyInfo();

function init() {
    buildNavidation($('body'));
    $('body').append($('<main>', {class: "container"}))

    activePage.create($('main'));

    $('body').on('click', '.mainNavigationBar a', function (link) {
        const page = $(this).attr('data-page');
        activatePage(page)
    });
}

function buildNavidation(_parent) {
    const nav = $('<nav>', {class: "navbar navbar-expand-lg navbar-light bg-light"})
                    .append($('<a>', {class: "navbar-brand"}).text("ABuddy Book"))
                    .append($('<div>', {class: "collapse navbar-collapse", id: "navbarNav"})
                        .append($('<ul>', {class: "navbar-nav mainNavigationBar"})
                            .append($('<li>', {class: "nav-item"}).append($('<a>', {class: "nav-link active", "data-page": "buddiesInfo"}).text("Buddies")))
                            .append($('<li>', {class: "nav-item"}).append($('<a>', {class: "nav-link", "data-page": "addNewBuddy"}).text("Add Buddy")))
                        )
                    )
    _parent.append(nav);
}

function activatePage(_pageId) {
    const currentlyActive = $('.mainNavigationBar a.active').attr('data-page')
    if(_pageId == currentlyActive) {
        return;
    }

    if(currentlyActive == "buddiesInfo" && _pageId == "addNewBuddy") {
        const modal = activePage.buildModal("createBuddy", {})
        modal.modal('show');
        const submitBtn = modal.find('#submitChanges')
        submitBtn.click(() => {
            $.ajax({
                type: "POST",
                url: "api/buddyInfo",
                contentType: 'application/json',
                data: JSON.stringify({
                    "name": modal.find('#bName').val(),
                    "phoneNumber": modal.find('#bPhone').val(),
                    "address": modal.find('#bAddress').val()
                }),
                success: (newID) => {
                    activePage.addBuddyInfoToTable("buddiesPage", {
                        "id": newID,
                        "name": modal.find('#bName').val(),
                        "phoneNumber": modal.find('#bPhone').val(),
                        "address": modal.find('#bAddress').val()
                    })
                },
                error: function (e) {
                    alert("Error occurred. Changes cannot be saved.");
                }
            })
            modal.modal('hide');
            modal.modal('dispose');
            modal.remove();
        });
    }
}

init();