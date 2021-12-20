function editModal(id) {
    fetch(`http://localhost:8080/admin/users/` + id).then(res => {
        res.json().then(user => {
            $('#editId').attr('value', user.id)
            $('#editName').attr('value', user.name)
            $('#editLastname').attr('value', user.lastname)
            $('#editEmail').attr('value', user.email)
            $('#editUsername').attr('value', user.username)
            $('#editPassword').attr('value', user.password)
            $('#editRoles').attr('value', user.roles)
        })
    })
}

function updateUser() {

    let id = document.getElementById("editId").value;
    let name = document.getElementById("editName").value;
    let lastname = document.getElementById("editLastname").value;
    let email = document.getElementById("editEmail").value;
    let username = document.getElementById("editUsername").value;
    let password = document.getElementById("editPassword").value;
    let roles = (Array.from(document.getElementById("editRoles").selectedOptions).map(options => options.value))

    let userJson = {
        id: id,
        name: name,
        lastname: lastname,
        email: email,
        username: username,
        password: password,
        roles: roles
    }

    fetch('admin/update/' + id, {
        method:"PUT",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type":"application/json"
        },
        body:JSON.stringify(userJson)
    }).finally(() => {
        $('#editModal').modal("hide")
        showUsersTable();
    })

}