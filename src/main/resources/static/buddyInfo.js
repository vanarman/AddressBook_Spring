var _buddyInfo = function () {
    this.create = function (_pageRoot) {
        _pageRoot.append($('<section>', {class: "buddyInfo"}))
        this.init($('section.buddyInfo'));
    }

    this.destroy = function() {
        $('section.buffyInfo').remove();
    }

    this.init = function(_parent) {
        _parent.append(buildBuddyInfoTable("buddiesPage"));
        requestBuddies();

        $('body').on('click', '#editBuddy', function () {
            const buddyID = $(this).attr('data-edit-buddy');
            $.ajax({
                type: "GET",
                url: "api/buddyInfo/"+ buddyID,
                contentType: 'application/json',
                success: (response) => {
                    const modal = buildModal("editBuddy"+ buddyID, response);
                    modal.modal('show');
                    const submitBtn = modal.find('#submitChanges')
                    submitBtn.click(() => {
                        $.ajax({
                            type: "PUT",
                            url: "api/buddyInfo/"+ buddyID,
                            contentType: 'application/json',
                            // dataType: 'json',
                            data: JSON.stringify({
                                "id": buddyID,
                                "name": modal.find('#bName').val(),
                                "phoneNumber": modal.find('#bPhone').val(),
                                "address": modal.find('#bAddress').val()
                            }),
                            success: () => {
                                const cell = $(this)[0].parentNode;
                                const row = $(cell)[0].parentNode;
                                const child = $(row).children()
                                $(child[1]).text(modal.find('#bName').val())
                                $(child[2]).text(modal.find('#bPhone').val())
                                $(child[3]).text(modal.find('#bAddress').val())
                            },
                            error: function (e) {
                                console.log(e);
                                alert("Error occurred. Changes cannot be saved.");
                            }
                        })
                        modal.modal('hide');
                        modal.modal('dispose');
                        modal.remove();
                    })
                },
                error: function () {
                    alert("Error occurred. Record cannot be removed.");
                }
            })
        });

        $('body').on('click', '#removeBuddy', function () {
            const buddyID = $(this).attr('data-remove-buddy');
            $.ajax({
                type: "DELETE",
                url: "api/buddyInfo/"+ buddyID,
                contentType: 'application/json',
                success: (response) => {
                    const cell = $(this)[0].parentNode;
                    const row = $(cell)[0].parentNode;
                    $(row).remove();
                },
                error: function () {
                    alert("Error occurred. Record cannot be removed.");
                }
            })
        });
    }

    function requestBuddies() {
        $.ajax({
            type: "GET",
            url: "api/buddyInfo",
            contentType: 'application/json',
            success: function(response) {
                response.forEach((buddy) => {
                    addBuddyInfoToTable("buddiesPage", buddy)
                })
            },
            error: function () {
                alert("Error occurred while retrieving Buddy Info");
            }
        });
    }

    function buildBuddyInfoTable(_tableId) {
        let table = $('<table>', {class: "buddyInfo table", id: _tableId})
            .append($('<thead><tr>')
                .append($('<th>', {"scope": "col"}).text("Id"))
                .append($('<th>', {"scope": "col"}).text("Name"))
                .append($('<th>', {"scope": "col"}).text("Phone Number"))
                .append($('<th>', {"scope": "col"}).text("Home Address"))
                .append($('<th>', {"scope": "col"}).text("Action")))
            .append($('<tbody><tr>'));
        return table;
    }

    this.addBuddyInfoToTable = function(_tableId, buddyInfo) {
        addBuddyInfoToTable(_tableId, buddyInfo);
    }

    function addBuddyInfoToTable(_tableId, buddyInfo) {
        $('#'+ _tableId +' tbody').append(
            $('<tr>')
                .append($('<td>').text(buddyInfo.id))
                .append($('<td>').text(buddyInfo.name))
                .append($('<td>').text(buddyInfo.phoneNumber))
                .append($('<td>').text(buddyInfo.address))
                .append($('<td>')
                    .append($('<button>', {
                                            class: "btn btn-info mr-3",
                                            id: "editBuddy",
                                            'data-edit-buddy': buddyInfo.id,
                                          }).text("Edit"))
                    .append($('<button>', {
                                            class: "btn btn-danger",
                                            id: "removeBuddy",
                                            'data-remove-buddy': buddyInfo.id
                                          }).text("Remove"))));
    }

    this.buildModal = function(_modalId, info) {
        return buildModal(_modalId, info);
    }

    function buildModal(_modalId, info) {
        const modal = $('<div>', {class: "modal", id: _modalId +" editBuddyModal", tabIndex: "-1"})
                    .append($('<div>', {class: "modal-dialog"})
                        .append($('<div>', {class: "modal-content"})
                            .append($('<div>', {class: "modal-header"})
                                .append($('<h5>', {class: "modal-title"}).text("Edit Buddy ID:"+ info.id)))
                            .append($('<div>', {class: "modal-body"})
                                .append($('<form>')
                                    .append($('<div>', {class: "form-group"})
                                        .append($('<label>', {class: "col-form-label", "for": "bName"}).text("Name"))
                                        .append($('<input>', {class: "form-control", id: "bName", type: "text"}).val(info.name)))
                                    .append($('<div>', {class: "form-group"})
                                        .append($('<label>', {class: "col-form-label", "for": "bPhone"}).text("Phone Number"))
                                        .append($('<input>', {class: "form-control", id: "bPhone", type: "text"}).val(info.phoneNumber)))
                                    .append($('<div>', {class: "form-group"})
                                        .append($('<label>', {class: "col-form-label", "for": "bAddress"}).text("Home Address"))
                                        .append($('<input>', {class: "form-control", id: "bAddress", type: "text"}).val(info.address)))))
                            .append($('<div>', {class: "modal-footer"})
                                .append($('<button>', {class: "btn btn-secondary", "data-dismiss": "modal"}).text("Cancel"))
                                .append($('<button>', {class: "btn btn-primary", id: "submitChanges"}).text("Save Changes")))));
        $('body').append(modal);
        return modal;
    }
}