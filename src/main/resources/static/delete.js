function deleteModal(id) {
    fetch(`admin/users/` + id)
        .then(res => res.json())
        .then(user => {
            $('#deleteId').attr('value', user.id)
            $('#deleteName').attr('value', user.name)
            $('#deleteLastname').attr('value', user.lastname)
            $('#deleteEmail').attr('value', user.email)
            $('#deleteUsername').attr('value', user.username)
            $('#deletePassword').attr('value', user.password)
            $('#deleteRoles').attr('value', user.roles)
        })
}

function deleteUser() {
    let id = document.getElementById("deleteId").value;
    let name = document.getElementById("deleteName").value;
    let lastname = document.getElementById("deleteLastname").value;
    let email = document.getElementById("deleteEmail").value;
    let username = document.getElementById("deleteUsername").value;
    let password = document.getElementById("deletePassword").value;

    let user = {
        id: id,
        name: name,
        lastname: lastname,
        email: email,
        username: username,
        password: password
    }

    console.log(user)

    fetch('admin/delete/' + id, {
        method:"DELETE",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }}).then(() =>
    {
        $('#deleteModal').modal("hide")
        showUsersTable()
    })

}